package com.wangweijun.structure.ui.detail;


import com.wangweijun.structure.data.model.AppDetailsModel;
import com.wangweijun.structure.ui.base.MvpView;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public interface DetailMvpView extends MvpView {

    void showDetailView(AppDetailsModel appDetailsModel);

    void showScore(float score);
}
