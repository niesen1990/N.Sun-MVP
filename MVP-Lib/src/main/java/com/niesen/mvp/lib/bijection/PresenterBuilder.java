package com.niesen.mvp.lib.bijection;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:42
 */
public class PresenterBuilder {
    public static <PresenterType extends Presenter> PresenterType fromViewClass(Class<?> viewClass) {
        RequiresPresenter requiresPresenter = viewClass.getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        if (requiresPresenter == null) {
            throw new RuntimeException("You must declaration @RequiresPresenter for your Activity");
        }

        Class<PresenterType> presenterClass = (Class<PresenterType>) requiresPresenter.value();

        PresenterType presenter;
        try {
            presenter = presenterClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return presenter;
    }
}
