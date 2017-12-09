package com.wangweijun.structure.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.wangweijun.structure.R;
import com.wangweijun.structure.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun on 2017/12/10.
 */

public class LoginActivity extends BaseActivity{
    @BindView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("login sucess"));
                finish();
            }
        });

    }
}
