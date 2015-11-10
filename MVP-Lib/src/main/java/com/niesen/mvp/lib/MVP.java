package com.niesen.mvp.lib;

import android.app.Activity;
import android.content.Context;

import com.niesen.mvp.lib.bijection.ActivityLifeCycleDelegate;
import com.niesen.mvp.lib.bijection.ActivityLifeCycleDelegateProvider;
import com.niesen.mvp.lib.expansion.MVPBaseActivity;
import com.niesen.mvp.lib.expansion.overlay.ViewExpansionDelegate;
import com.niesen.mvp.lib.expansion.overlay.ViewExpansionDelegateProvider;
import com.niesen.mvp.lib.model.ModelManager;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:52
 */
public class MVP {
    private static ActivityLifeCycleDelegateProvider mActivityLIfeCycleDelegateProvider;

    private static ViewExpansionDelegateProvider mViewExpansionDelegateProvider;

    public static ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity) {
        if (mActivityLIfeCycleDelegateProvider != null)
            return mActivityLIfeCycleDelegateProvider.createActivityLifeCycleDelegate(activity);
        else return null;
    }

    public static void setActivityLifeCycleDelegateProvider(ActivityLifeCycleDelegateProvider activityLifeCycleDelegateClass) {
        mActivityLIfeCycleDelegateProvider = activityLifeCycleDelegateClass;
    }

    public static ViewExpansionDelegate createViewExpansionDelegate(MVPBaseActivity activity) {
        if (mViewExpansionDelegateProvider == null)
            return ViewExpansionDelegateProvider.DEFAULT.createViewExpansionDelegate(activity);
        else return mViewExpansionDelegateProvider.createViewExpansionDelegate(activity);
    }

    public static void setViewExpansionDelegateProvider(ViewExpansionDelegateProvider viewExpansionDelegateProvider) {
        mViewExpansionDelegateProvider = viewExpansionDelegateProvider;
    }

    public static void init(Context ctx) {
        ModelManager.init(ctx);
    }

}
