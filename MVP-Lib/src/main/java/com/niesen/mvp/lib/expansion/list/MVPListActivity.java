package com.niesen.mvp.lib.expansion.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niesen.mvp.lib.R;
import com.niesen.mvp.lib.expansion.MVPBaseActivity;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午5:09
 */
public abstract class MVPListActivity<T extends MVPListActivityPresenter, M> extends MVPBaseActivity<T> {

    private ListConfig mListConfig;
    private EasyRecyclerView mListView;
    private MVPListActivityPresenter.DataAdapter mAdapter;

    public EasyRecyclerView getListView() {
        return mListView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListConfig = getConfig();
        createRecycler();
        findRecycler();
        initList();
        mListView.setAdapterWithProgress(mAdapter = getPresenter().createDataAdapter());
        initAdapter();
    }

    public void stopRefresh() {
        mListView.getSwipeToRefresh().setRefreshing(false);
    }

    public void showError() {
        mListView.showError();
    }

    public int getLayout() {
        return 0;
    }

    private void findRecycler() {
        mListView = (EasyRecyclerView) findViewById(R.id.recycler);
        if (mListView == null) throw new RuntimeException("No found recycler with id \"recycler\"");
        mListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createRecycler() {
        if (getLayout() != 0) {
            setContentView(getLayout());
        } else if (mListConfig.mContainerLayoutRes != 0) {
            setContentView(mListConfig.mContainerLayoutRes);
        } else if (mListConfig.mContainerErrorView != null) {
            setContentView(mListConfig.mContainerLayoutView);
        } else {
            EasyRecyclerView mListView = new EasyRecyclerView(this);
            mListView.setId(R.id.recycler);
            mListView.setLayoutManager(new LinearLayoutManager(this));
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setContentView(mListView);
        }
    }

    private void initList() {
        if (mListConfig.mRefreshAble) mListView.setRefreshListener(getPresenter());
        if (mListConfig.mContainerProgressAble) {
            if (mListConfig.mContainerProgressView != null)
                mListView.setProgressView(mListConfig.mContainerProgressView);
            else mListView.setProgressView(mListConfig.mContainerProgressRes);
        }
        if (mListConfig.mContainerErrorAble) {
            if (mListConfig.mContainerErrorView != null)
                mListView.setErrorView(mListConfig.mContainerErrorView);
            else mListView.setErrorView(mListConfig.mContainerErrorRes);
        }
        if (mListConfig.mContainerEmptyAble) {
            if (mListConfig.mContainerEmptyView != null)
                mListView.setEmptyView(mListConfig.mContainerEmptyView);
            else mListView.setEmptyView(mListConfig.mContainerEmptyRes);
        }
    }

    private void initAdapter() {
        if (mListConfig.mNoMoreAble) {
            if (mListConfig.mNoMoreView != null) mAdapter.setNoMore(mListConfig.mNoMoreView);
            else mAdapter.setNoMore(mListConfig.mNoMoreRes);
        }
        if (mListConfig.mLoadmoreAble) {
            if (mListConfig.mLoadMoreView != null)
                mAdapter.setMore(mListConfig.mLoadMoreView, getPresenter());
            else mAdapter.setMore(mListConfig.mLoadMoreRes, getPresenter());
        }
        if (mListConfig.mErrorAble) {
            View errorView;
            if (mListConfig.mErrorView != null)
                errorView = mAdapter.setMore(mListConfig.mErrorView, getPresenter());
            else errorView = mAdapter.setError(mListConfig.mErrorRes);
            if (mListConfig.mErrorTouchToResumeAble)
                errorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.resumeMore();
                    }
                });
        }
    }

    protected ListConfig getConfig() {
        return ListConfig.Default.clone();
    }

    public int getViewType(int position) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);


}
