package com.niesen.mvp.lib.model;

import android.content.Context;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:04
 */
public abstract class AbstractModel {
    public static final <T extends AbstractModel> T getInstance(Class<T> clazz) {
        AbstractModel model = ModelManager.mModelMap.get(clazz);
        if (model == null)
            throw new RuntimeException(clazz.getName() + " No Found , Have you declare MODEL in the manifests?");
        return (T) model;
    }

    protected <N> N getNetManager() {
        return null;
    }

    protected void onAppCreate(Context ctx) {
    }

    protected void onAppCreateOnBackThread(Context ctx) {
    }

    protected final void runOnBackThread(Runnable runnable) {
        ModelManager.runOnBackThread(runnable);
    }
}
