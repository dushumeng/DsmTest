package com.fcdream.app.device.info;

import android.app.Activity;

import com.fcdream.app.device.MyExecutors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Copyright (c) 2017, 北京视达科科技有限责任公司 All rights reserved.
 * author：shumeng.du
 * date：2017/9/7
 * description：
 */
public class CpuInfo extends BaseInfo {

    private static final String CPU_PATH = "/proc/cpuinfo";

    @Override
    public void init(Activity context, final CountDownLatch latch) {
        MyExecutors.getInstance().exec(new Runnable() {
            @Override
            public void run() {
                BufferedReader localBufferedReader = null;
                try {
                    String[] cpuInfo = {"", ""};
                    String[] arrayOfString;
                    FileReader fr = new FileReader(CPU_PATH);
                    localBufferedReader = new BufferedReader(fr, 8192);
                    String lineInfo = localBufferedReader.readLine();
                    arrayOfString = lineInfo.split("\\s+");
                    for (int i = 2; i < arrayOfString.length; i++) {
                        cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
                    }
                    lineInfo = localBufferedReader.readLine();
                    arrayOfString = lineInfo.split("\\s+");
                    cpuInfo[1] += arrayOfString[2];
                    info = "CPU：" + cpuInfo[0] + "\n"
                            + "CPU：" + cpuInfo[1];
                } catch (IOException e) {
                    e.printStackTrace();
                    info = "CPU：获取CPU信息失败！！！";
                } finally {
                    if (localBufferedReader != null) {
                        try {
                            localBufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    finish(latch);
                }
            }
        });
    }
}
