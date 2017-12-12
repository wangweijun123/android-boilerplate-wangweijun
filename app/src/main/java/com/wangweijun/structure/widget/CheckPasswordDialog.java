package com.wangweijun.structure.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wangweijun.structure.R;


public class CheckPasswordDialog extends Dialog implements OnClickListener {

	private Button bt_download;
	
	private View pop_close;
	
	private Activity mActivity;

	public CheckPasswordDialog(Activity activity) {
		super(activity, 0);
		this.mActivity = activity;
		getWindow().setGravity(Gravity.BOTTOM);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_check_password);
		initViews();
	}

	private void initViews() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

}
