package com.fcdream.app.device;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fcdream.app.device.info.BaseInfo;
import com.fcdream.app.device.info.CpuInfo;
import com.fcdream.app.device.info.DpiInfo;
import com.fcdream.app.device.info.PixelInfo;
import com.fcdream.app.device.info.RamInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private final static List<Class<? extends BaseInfo>> infoClassList = new ArrayList<>();

    {
        infoClassList.add(PixelInfo.class);
        infoClassList.add(DpiInfo.class);
        infoClassList.add(CpuInfo.class);
        infoClassList.add(RamInfo.class);
    }

    private RecyclerView contentView;

    private CountDownLatch _latch;

    private Handler handler = new Handler();

    private InfoAdapter adapter = new InfoAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = (RecyclerView) findViewById(R.id.content_view);
        contentView.setLayoutManager(new LinearLayoutManager(this));
        contentView.setAdapter(adapter);

        _latch = new CountDownLatch(infoClassList.size());

        final List<BaseInfo> dataList = new ArrayList<>();
        for (Class<? extends BaseInfo> clazz : infoClassList) {
            try {
                BaseInfo baseInfo = clazz.newInstance();
                baseInfo.init(this, _latch);
                dataList.add(baseInfo);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    _latch.await();
                } catch (InterruptedException e) {

                }
                Log.i("dsminfo", "refresh");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.getDataList().clear();
                        adapter.getDataList().addAll(dataList);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
