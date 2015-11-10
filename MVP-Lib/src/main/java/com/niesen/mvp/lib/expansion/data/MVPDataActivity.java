package com.niesen.mvp.lib.expansion.data;

import com.niesen.mvp.lib.bijection.RequiresPresenter;
import com.niesen.mvp.lib.expansion.MVPBaseActivity;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午4:50
 */
@RequiresPresenter(MVPDataActivityPresenter.class)
public class MVPDataActivity<T extends MVPDataActivityPresenter, M> extends MVPBaseActivity<T> {

    public void setData(M data) {
    }

    public void setError(Throwable e) {
    }
}
