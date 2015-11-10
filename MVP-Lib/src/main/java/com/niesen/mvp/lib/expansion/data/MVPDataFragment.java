package com.niesen.mvp.lib.expansion.data;

import com.niesen.mvp.lib.bijection.MVPFragment;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午5:02
 */
public class MVPDataFragment<T extends MVPDataFragmentPresenter, M> extends MVPFragment<T> {

    public void setData(M data) {
    }

    public void setError(Throwable e) {
    }

}
