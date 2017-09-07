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
public class DpiInfo extends BaseInfo {
    @Override
    public void init(Activity activity, CountDownLatch latch) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;
        info = "屏幕密度：" + density + "\n"
                + "屏幕密度DPI：" + densityDpi;

        finish(latch);
    }
}
