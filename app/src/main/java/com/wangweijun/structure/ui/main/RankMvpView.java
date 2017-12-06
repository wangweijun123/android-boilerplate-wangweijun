package com.wangweijun.structure.ui.main;

import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.MvpView;

import java.util.List;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public interface RankMvpView extends MvpView{

    void showDataLoadSuccess(List<BaseModel> list);
}
