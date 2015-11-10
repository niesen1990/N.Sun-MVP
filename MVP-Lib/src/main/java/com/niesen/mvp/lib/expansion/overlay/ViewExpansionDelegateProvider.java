package com.niesen.mvp.lib.expansion.overlay;

import com.niesen.mvp.lib.expansion.MVPBaseActivity;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:39
 */
public abstract class ViewExpansionDelegateProvider {

    public abstract ViewExpansionDelegate createViewExpansionDelegate(MVPBaseActivity activity);

    public static ViewExpansionDelegateProvider DEFAULT = new ViewExpansionDelegateProvider() {
        @Override
        public ViewExpansionDelegate createViewExpansionDelegate(MVPBaseActivity activity) {
            return new DefaultViewExpansionDelegate(activity);
        }
    };
}
