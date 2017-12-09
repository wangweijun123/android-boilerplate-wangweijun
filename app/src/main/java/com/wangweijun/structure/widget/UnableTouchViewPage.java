package com.wangweijun.structure.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class UnableTouchViewPage extends ViewPager {

	public UnableTouchViewPage(Context context){
        super(context);
    }
	public UnableTouchViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
	}
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
	
}
