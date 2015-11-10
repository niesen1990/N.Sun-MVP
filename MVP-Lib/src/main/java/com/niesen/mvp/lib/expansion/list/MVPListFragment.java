package com.niesen.mvp.lib.expansion.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.niesen.mvp.lib.R;
import com.niesen.mvp.lib.bijection.MVPFragment;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午5:26
 */
public abstract class MVPListFragment<T extends MVPListFragmentPresenter, M> extends MVPFragment<T> {
    View mRootView;
    private ListConfig mListConfig;
    private EasyRecyclerView mListView;
    private MVPListFragmentPresenter.DataAdapter mAdapter;

    public EasyRecyclerView getListView() {
        return mListView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListConfig = getConfig();
        createRecycler(container);
        findRecycler();
        initList();
        mListView.setAdapterWithProgress(mAdapter = getPresenter().createDataAdapter());
        initAdapter();
        return mRootView;
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
        mListView = (EasyRecyclerView) mRootView.findViewById(R.id.recycler);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void createRecycler(ViewGroup container) {
        if (getLayout() != 0) {
            mRootView = LayoutInflater.from(getActivity()).inflate(getLayout(), container, false);
        } else if (mListConfig.mContainerLayoutRes != 0) {
            mRootView = LayoutInflater.from(getActivity()).inflate(mListConfig.mContainerLayoutRes, container, false);
        } else if (mListConfig.mContainerErrorView != null) {
            mRootView = mListConfig.mContainerLayoutView;
        } else {
            EasyRecyclerView mListView = new EasyRecyclerView(getActivity());
            mListView.setId(R.id.recycler);
            mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mListView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mRootView = mListView;
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

    public int getViewType(int type) {
        return 0;
    }

    protected abstract BaseViewHolder getViewHolder(ViewGroup parent, int viewType);


}

