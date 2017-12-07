package com.wangweijun.structure.ui.blacklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangweijun.structure.R;
import com.wangweijun.structure.data.local.db.Account;
import com.wangweijun.structure.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun1 on 2017/12/7.
 */

public class BlackListActivity extends BaseActivity implements BlacklistMvpView{

    @Inject
    BlacklistPresenter blacklistPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    BlacklistAdapter blacklistAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_black_list);

//        List<Account> list = new ArrayList<>();
//        for(int i=0; i<5; i++) {
//            Account account = new Account();
//            account.setPlatformName("草根投资:"+i);
//            account.setOfficialWeb("http://www.baidu.com");
//            account.setUserName("刘德华");
//            account.setLoginPassword("loginpwdi");
//            account.setPayPassword("paypassworki");
//            list.add(account);
//        }

//        blacklistPresenter.insertAccount(account);
//        blacklistPresenter.insertAccounts(list);

        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(blacklistAdapter);

        blacklistPresenter.attachView(this);
        blacklistPresenter.queryAccounts();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorUI() {

    }

    @Override
    public void hideErrorUI() {

    }

    @Override
    public void showDataLoadSuccess(List<Account> accounts) {
        blacklistAdapter.addModels(accounts);
    }
}
