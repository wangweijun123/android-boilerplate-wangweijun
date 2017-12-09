package com.wangweijun.structure.ui.game;

import com.wangweijun.structure.data.model.BaseModel;
import com.wangweijun.structure.ui.base.MvpView;

import java.util.List;

/**
 * Created by wangweijun on 2017/12/9.
 */

public interface GameMvpView extends MvpView{

    void showDataLoadSuccess(List<BaseModel> list);

    void showEmptyUI();
}
