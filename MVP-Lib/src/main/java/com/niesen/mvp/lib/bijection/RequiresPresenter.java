package com.niesen.mvp.lib.bijection;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:45
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends Presenter> value();
}
