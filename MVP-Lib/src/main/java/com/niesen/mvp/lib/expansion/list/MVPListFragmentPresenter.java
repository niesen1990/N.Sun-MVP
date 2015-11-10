package com.niesen.mvp.lib.expansion.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.niesen.mvp.lib.bijection.Presenter;

import java.util.List;

import rx.Subscriber;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午5:26
 */
public class MVPListFragmentPresenter <T extends MVPListFragment,M> extends Presenter<T>
        implements RecyclerArrayAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    DataAdapter mAdapter;
    int page = 0;
    Subscriber<List<M>> mRefreshSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getView().stopRefresh();
            getView().showError();
        }

        @Override
        public void onNext(List<M> ms) {
            getAdapter().clear();
            getAdapter().addAll(ms);
            page = 1;
        }
    };
    Subscriber<List<M>> mMoreSubscriber = new Subscriber<List<M>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            getAdapter().pauseMore();
        }

        @Override
        public void onNext(List<M> ms) {
            getAdapter().addAll(ms);
            page++;
        }
    };

    public int getCurPage(){
        return page;
    }

    public void setCurPage(int page){
        this.page = page;
    }

    public Subscriber<List<M>> getRefreshSubscriber(){
        return mRefreshSubscriber;
    }

    public Subscriber<List<M>> getMoreSubscriber(){
        return mMoreSubscriber;
    }


    DataAdapter createDataAdapter(){
        return mAdapter = new DataAdapter(getView().getActivity());
    }

    public DataAdapter getAdapter(){
        return mAdapter;
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onRefresh() {
    }

    public class DataAdapter extends RecyclerArrayAdapter<M> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getViewType(int position) {
            return getView().getViewType(position);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return getView().getViewHolder(parent, viewType);
        }
    }

}

