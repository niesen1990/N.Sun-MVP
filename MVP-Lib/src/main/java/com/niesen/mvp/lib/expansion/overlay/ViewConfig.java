package com.niesen.mvp.lib.expansion.overlay;

import android.view.View;

import com.niesen.mvp.lib.R;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:40
 */
public class ViewConfig implements Cloneable {
    
    static ViewConfig Default = new ViewConfig();
    String mProgressTitle = "请稍等";
    View mProgressView;
    int mProgressRes = R.layout.mvp_view_progress;
    View mErrorView;
    int mErrorRes = R.layout.mvp_view_error;

    public static void setDefaultViewConfig(ViewConfig viewConfig) {
        Default = viewConfig;
    }

    public ViewConfig setProgressTitle(String mProgressTitle) {
        this.mProgressTitle = mProgressTitle;
        return this;
    }

    public ViewConfig setProgressView(View mProgressView) {
        this.mProgressView = mProgressView;
        return this;
    }

    public ViewConfig setProgressRes(int mProgressRes) {
        this.mProgressRes = mProgressRes;
        return this;
    }

    public ViewConfig setErrorView(View mErrorView) {
        this.mErrorView = mErrorView;
        return this;
    }

    public ViewConfig setErrorRes(int mErrorRes) {
        this.mErrorRes = mErrorRes;
        return this;
    }


    @Override
    public ViewConfig clone() {
        try {
            return (ViewConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ViewConfig();
    }
}
