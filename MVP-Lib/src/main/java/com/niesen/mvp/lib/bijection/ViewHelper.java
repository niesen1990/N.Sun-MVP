package com.niesen.mvp.lib.bijection;

import android.content.Intent;
import android.os.Bundle;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：控制Presenter的生命周期
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:39
 */
public class ViewHelper<PresenterType extends Presenter> {

    public static final String PRESENTER_ID = "presenter_id";

    PresenterManager manager;

    public PresenterType getPresenter() {
        return presenter;
    }

    PresenterType presenter;
    Object view;

    public ViewHelper(Object view) {
        this.view = view;
    }


    void onCreate(Bundle savedInstanceState) {
        String id;
        if (savedInstanceState == null || (id = savedInstanceState.getString(PRESENTER_ID)) == null) {
            presenter = PresenterManager.getInstance().create(view);
            presenter.create(view, savedInstanceState);
        } else {
            presenter = PresenterManager.getInstance().get(id);
            if (presenter == null) {
                //throw new RuntimeException("有可能为空？在逗我？");
                presenter = PresenterManager.getInstance().create(view);
            }
        }
    }

    void onPostCreate() {
        presenter.onCreateView(view);
    }

    void onDestroyView() {
        presenter.onDestroyView();
    }

    void onDestroy() {
        PresenterManager.getInstance().destroy(presenter.id);
        presenter.onDestroy();
    }

    void onSave(Bundle state) {
        state.putString(PRESENTER_ID, presenter.id);
        presenter.onSave(state);
    }

    void onResume() {
        presenter.onResume();
    }

    void onPause() {
        presenter.onPause();
    }

    void onResult(int requestCode, int resultCode, Intent data) {
        presenter.onResult(requestCode, resultCode, data);
    }
}
