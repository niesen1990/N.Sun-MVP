package com.niesen.mvp.lib.bijection;

/**
 * 项目名称：N.Sun-MVP
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/9 下午3:40
 */
public abstract class PresenterManager {

    private static PresenterManager instance = new DefaultPresenterManager();

    public static PresenterManager getInstance() {
        return instance;
    }

    public abstract <T extends Presenter> T create(Object view);

    public abstract <T extends Presenter> T get(String id);

    public abstract void destroy(String id);
}
