package com.wangweijun.structure.ui.blacklist;

import com.wangweijun.structure.data.local.db.Account;
import com.wangweijun.structure.ui.base.MvpView;

import java.util.List;

/**
 * Created by wangweijun1 on 2017/12/7.
 */

public interface BlacklistMvpView extends MvpView {

    void showDataLoadSuccess(List<Account> accounts);
}
