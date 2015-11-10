package com.niesen.mvp.lib.expansion.overlay;

import android.view.View;
import android.widget.FrameLayout;

import com.niesen.mvp.lib.expansion.MVPBaseActivity;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：视图扩展
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:23
 */
public abstract class ViewExpansionDelegate {

    private MVPBaseActivity activity;
    private FrameLayout container;

    public ViewExpansionDelegate(MVPBaseActivity activity) {
        this.activity = activity;
        this.container = activity.getParentView();
    }

    public final MVPBaseActivity getActivity() {
        return activity;
    }

    public final FrameLayout getContainer() {
        return container;
    }

    public void showProgressDialog(String title) {
    }

    public void dismissProgressDialog() {
    }

    public View showProgressPage() {
        return null;
    }

    public void dismissProgressPage() {
    }

    public View showErrorPage() {
        return null;
    }

    public void dismissErrorPage() {
    }

    public void setErrorRetryListener(OnRetryListener listener) {
    }

    public interface OnRetryListener {
        void onRetry();
    }

    public void addCustomOverlayView(View view) {
    }

    public void removeCustomOverlayView(View view) {
    }
}
