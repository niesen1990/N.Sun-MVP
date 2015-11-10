package com.niesen.mvp.lib.bijection;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.niesen.mvp.lib.MVP;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:31
 */
public class MVPAppCompatActivity<PresenterType extends Presenter> extends AppCompatActivity {

    private static final String TAG = MVPAppCompatActivity.class.getSimpleName();
    private ActivityLifeCycleDelegate activityLifeCycleDelegate;
    private ViewHelper<PresenterType> helper = new ViewHelper<>(this);


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onSaveInstanceState(outState);
        helper.onSave(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        preCreatePresenter();
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onCreate(savedInstanceState);
        helper.onCreate(savedInstanceState);
    }

    public void preCreatePresenter() {
        activityLifeCycleDelegate = MVP.createActivityLifeCycleDelegate(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onPostCreate(savedInstanceState);
        helper.onPostCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onResume();
        helper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onPause();
        helper.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onActivityResult(requestCode, resultCode, data);
        helper.onResult(requestCode, resultCode, data);
    }

    public PresenterType getPresenter() {
        return helper.getPresenter();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onContentChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onRestart();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onUserLeaveHint();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onNewIntent(intent);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onTrimMemory(level);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onAttachFragment(fragment);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onResumeFragments();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onPostResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (activityLifeCycleDelegate != null)
            activityLifeCycleDelegate.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityLifeCycleDelegate != null) activityLifeCycleDelegate.onDestroy();
        helper.onDestroyView();
        if (isFinishing())
            helper.onDestroy();
    }
}
