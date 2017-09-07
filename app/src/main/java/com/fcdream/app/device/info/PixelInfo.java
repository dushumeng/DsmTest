package com.fcdream.app.device.info;

import android.app.Activity;
import android.util.DisplayMetrics;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright (c) 2017, 北京视达科科技有限责任公司 All rights reserved.
 * author：shumeng.du
 * date：2017/9/7
 * description：
 */
public class PixelInfo extends BaseInfo {
    @Override
    public void init(Activity activity, CountDownLatch latch) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        info = "分辨率：" + width + "*" + height;
        finish(latch);
    }
}
