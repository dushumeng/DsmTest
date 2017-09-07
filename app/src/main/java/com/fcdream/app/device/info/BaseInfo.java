package com.fcdream.app.device.info;

import android.app.Activity;
import android.content.Context;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright (c) 2017, 北京视达科科技有限责任公司 All rights reserved.
 * author：shumeng.du
 * date：2017/9/7
 * description：
 */
public abstract class BaseInfo {

    public String info;

    public BaseInfo() {

    }

    public abstract void init(Activity context, CountDownLatch latch);

    final protected void finish(CountDownLatch latch) {
        synchronized (latch) {
            latch.countDown();
        }
    }
}
