package com.wangweijun.structure.ui.soft;

import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.MvpView;

import java.util.List;

/**
 * Created by wangweijun on 2017/12/9.
 */

public interface SoftMvpView extends MvpView{

    void showDataLoadSuccess(List<BaseModel> list);

    void showEmptyUI();
}
