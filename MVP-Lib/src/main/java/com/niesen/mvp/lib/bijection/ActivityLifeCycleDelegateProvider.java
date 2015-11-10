package com.niesen.mvp.lib.bijection;

import android.app.Activity;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:52
 */
public interface ActivityLifeCycleDelegateProvider {
    ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity);
}
