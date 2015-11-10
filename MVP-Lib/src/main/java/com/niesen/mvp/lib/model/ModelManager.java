package com.niesen.mvp.lib.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.HashMap;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:04
 */
public class ModelManager {
    final static HashMap<Class<?>, AbstractModel> mModelMap = new HashMap<>();
    final static BackThread mBackThread = new BackThread();

    public static void init(final Context ctx) {
        mBackThread.start();
        Class<?>[] models = null;
        try {
            ApplicationInfo appInfo = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData == null || appInfo.metaData.getString("MODEL") == null) {
                Log.e("MVP", "MODEL No Found!Have you declare MODEL in the manifests?");
                return;
            }
            String modelStr = appInfo.metaData.getString("MODEL").trim();
            String[] modelStrs = modelStr.split(",");
            models = new Class[modelStrs.length];
            for (int i = 0; i < modelStrs.length; i++) {
                try {
                    models[i] = Class.forName(modelStrs[i].trim());
                } catch (ClassNotFoundException e) {
                    Log.e("MVP", modelStrs[i].trim() + " Class No Found!");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }

        for (Class m : models) {
            if (m != null && AbstractModel.class.isAssignableFrom(m)) {
                try {
                    AbstractModel instance = (AbstractModel) (m.newInstance());
                    mModelMap.put(m, instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("MVP", "your model must extends AbstractModel");
            }
        }
        //后台调用
        final Class<?>[] finalModels = models;
        mBackThread.execute(new Runnable() {
            @Override
            public void run() {
                for (Class m : finalModels) {
                    if (m != null)
                        mModelMap.get(m).onAppCreateOnBackThread(ctx);
                }
            }
        });

        //前台调用
        for (Class m : models) {
            if (m != null && mModelMap.get(m) != null)
                mModelMap.get(m).onAppCreate(ctx);
        }
    }

    static void runOnBackThread(Runnable runnable) {
        mBackThread.execute(runnable);
    }
}
