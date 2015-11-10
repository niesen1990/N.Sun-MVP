package com.niesen.mvp.lib.expansion.data;

import com.niesen.mvp.lib.bijection.Presenter;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subjects.BehaviorSubject;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:51
 */
public class MVPDataActivityPresenter<T extends MVPDataActivity, M> extends Presenter<T> implements Observer<M> {
    BehaviorSubject<M> mData = BehaviorSubject.create();
    Subscription mSubscription;

    @Override
    protected void onCreateView(T view) {
        super.onCreateView(view);
        mSubscription = mData.unsafeSubscribe(new Subscriber<M>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().setError(e);
            }

            @Override
            public void onNext(M m) {
                getView().setData(m);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    public BehaviorSubject<M> getDataSubJect() {
        return mData;
    }

    public void publishObject(M data) {
        mData.onNext(data);
    }

    public void publishError(Throwable e) {
        mData.onError(e);
    }


    @Override
    public void onCompleted() {
        mData.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mData.onError(e);
    }

    @Override
    public void onNext(M m) {
        mData.onNext(m);
    }
}
