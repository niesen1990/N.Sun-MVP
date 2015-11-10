package com.niesen.mvp.lib.model;

import android.os.Handler;
import android.os.Looper;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:06
 */
public class BackThread extends Thread {
    private Handler handler;
    private Queue<Runnable> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler();
        for (Runnable runnable : queue) {
            handler.post(runnable);
        }
        Looper.loop();
    }

    void execute(Runnable runnable) {
        if (handler == null) queue.offer(runnable);
        else handler.post(runnable);
    }

    void quit() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Looper.myLooper().quit();
            }
        });
    }
}
