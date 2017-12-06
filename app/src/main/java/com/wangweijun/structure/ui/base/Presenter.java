package com.wangweijun.structure.ui.base;

/**
 * Created by wangweijun on 2017/12/6.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
