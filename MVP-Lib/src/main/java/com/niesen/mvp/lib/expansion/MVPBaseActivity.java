package com.niesen.mvp.lib.expansion;

import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.niesen.mvp.lib.MVP;
import com.niesen.mvp.lib.R;
import com.niesen.mvp.lib.bijection.MVPAppCompatActivity;
import com.niesen.mvp.lib.bijection.Presenter;
import com.niesen.mvp.lib.expansion.overlay.ViewExpansionDelegate;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:15
 */
public class MVPBaseActivity<T extends Presenter> extends MVPAppCompatActivity<T> {

    // 兼容Toolbar控件
    private Toolbar toolbar;

    /**
     *      视图结构
     *                    DecorView
     *                        |
     *                    LinearLayout
     *                    /         \
     *                   /           \
     *           FrameLayout       FrameLayout
     *            |             (mContentParent)
     *            |                   /      \
     *         TextView     FrameLayout    各种附加View
     *                      (mContent)
     *                  (在一开始就装入View树)
     *                          |
     *                      setContent()
     *                  (什么时候装入View不确定)
     *
     */

    private FrameLayout mContent;
    private FrameLayout mContentParent;

    private ViewExpansionDelegate mDelegate;

    @Override
    public void preCreatePresenter() {
        super.preCreatePresenter();
        initViewTree();
    }

    private void initViewTree() {
        mContentParent = (FrameLayout) findViewById(android.R.id.content);
        mContent = new FrameLayout(this);
        super.setContentView(mContent);
    }

    public FrameLayout getParentView() {
        return mContentParent;
    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, mContent, false));
    }

    @Override
    public void setContentView(View view) {
        this.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContent.addView(view, params);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null)
            onSetToolbar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void onSetToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ViewExpansionDelegate createViewExpansionDelegate() {
        return MVP.createViewExpansionDelegate(this);
    }

    public final ViewExpansionDelegate getExpansion() {
        if (mDelegate == null) mDelegate = createViewExpansionDelegate();
        return mDelegate;
    }

    protected final <E extends View> E $(View view, @IdRes int id) {
        return (E) view.findViewById(id);
    }

    protected final <E extends View> E $(@IdRes int id) {
        return (E) findViewById(id);
    }
}
