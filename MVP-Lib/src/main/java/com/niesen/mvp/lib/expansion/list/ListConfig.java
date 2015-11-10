package com.niesen.mvp.lib.expansion.list;

import android.view.View;

import com.niesen.mvp.lib.R;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午5:19
 */
public class ListConfig implements Cloneable {

    static ListConfig Default = new ListConfig();

    public static void setDefaultListConfig(ListConfig config) {
        Default = config;
    }

    boolean mRefreshAble = false;
    boolean mLoadmoreAble = false;
    boolean mNoMoreAble = true;
    boolean mErrorAble = true;
    boolean mErrorTouchToResumeAble = true;
    boolean mContainerProgressAble = true;
    boolean mContainerEmptyAble = true;
    boolean mContainerErrorAble = true;

    View mContainerLayoutView;
    int mContainerLayoutRes = 0;
    View mContainerEmptyView;
    int mContainerEmptyRes = R.layout.mvp_view_list_con_empty;
    View mContainerProgressView;
    int mContainerProgressRes = R.layout.mvp_view_list_con_progress;
    View mContainerErrorView;
    int mContainerErrorRes = R.layout.mvp_view_list_con_error;
    View mLoadMoreView;
    int mLoadMoreRes = R.layout.mvp_view_list_more;
    View mNoMoreView;
    int mNoMoreRes = R.layout.mvp_view_list_nomore;
    View mErrorView;
    int mErrorRes = R.layout.mvp_view_list_error;


    public ListConfig setContainerLayoutView(View mContainerLayoutView) {
        this.mContainerLayoutView = mContainerLayoutView;
        return this;
    }

    public ListConfig setContainerLayoutRes(int mContainerLayoutRes) {
        this.mContainerLayoutRes = mContainerLayoutRes;
        return this;
    }

    public ListConfig setErrorAble(boolean mErrorAble) {
        this.mErrorAble = mErrorAble;
        return this;
    }

    public ListConfig setErrorView(View mErrorView) {
        this.mErrorView = mErrorView;
        return this;
    }

    public ListConfig setErrorRes(int mErrorRes) {
        this.mErrorRes = mErrorRes;
        return this;
    }

    public ListConfig setErrorTouchToResumeAble(boolean mErrorTouchToResumeAble) {
        this.mErrorTouchToResumeAble = mErrorTouchToResumeAble;
        if (mErrorTouchToResumeAble && mErrorRes == R.layout.mvp_view_list_error)
            mErrorRes = R.layout.mvp_view_list_error_retry;
        else if (!mErrorTouchToResumeAble && mErrorRes == R.layout.mvp_view_list_error_retry)
            mErrorRes = R.layout.mvp_view_list_error;
        return this;
    }

    public ListConfig setRefreshable(boolean mRefreshAble) {
        this.mRefreshAble = mRefreshAble;
        return this;
    }

    public ListConfig setLoadmoreable(boolean mLoadmoreAble) {
        this.mLoadmoreAble = mLoadmoreAble;
        return this;
    }

    public ListConfig setLoadMoreView(View mLoadMoreView) {
        this.mLoadMoreView = mLoadMoreView;
        return this;
    }

    public ListConfig setLoadMoreRes(int mLoadMoreRes) {
        this.mLoadMoreRes = mLoadMoreRes;
        return this;
    }

    public ListConfig setNoMoreView(View mNoMoreView) {
        this.mNoMoreView = mNoMoreView;
        return this;
    }

    public ListConfig setNoMoreable(boolean mNoMoreAble) {
        this.mNoMoreAble = mNoMoreAble;
        return this;
    }

    public ListConfig setNoMoreRes(int mMoMoreRes) {
        this.mNoMoreRes = mMoMoreRes;
        return this;
    }

    public ListConfig setContainerEmptyView(View mContainerEmptyView) {
        this.mContainerEmptyView = mContainerEmptyView;
        return this;
    }

    public ListConfig setContainerEmptyRes(int mContainerEmptyRes) {
        this.mContainerEmptyRes = mContainerEmptyRes;
        return this;
    }

    public ListConfig setContainerProgressView(View mContainerProgressView) {
        this.mContainerProgressView = mContainerProgressView;
        return this;
    }

    public ListConfig setContainerProgressRes(int mContainerProgressRes) {
        this.mContainerProgressRes = mContainerProgressRes;
        return this;
    }

    public ListConfig setContainerErrorView(View mContainerErrorView) {
        this.mContainerErrorView = mContainerErrorView;
        return this;
    }

    public ListConfig setContainerErrorRes(int mContainerErrorRes) {
        this.mContainerErrorRes = mContainerErrorRes;
        return this;
    }

    public ListConfig setContainerProgressAble(boolean mContainerProgressAble) {
        this.mContainerProgressAble = mContainerProgressAble;
        return this;
    }

    public ListConfig setContainerNoMoreable(boolean mContainerNoMoreAble) {
        this.mContainerEmptyAble = mContainerNoMoreAble;
        return this;
    }

    public ListConfig setContainerErrorable(boolean mContainerErrorAble) {
        this.mContainerErrorAble = mContainerErrorAble;
        return this;
    }

    @Override
    public ListConfig clone() {
        try {
            return (ListConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ListConfig();
    }
}
