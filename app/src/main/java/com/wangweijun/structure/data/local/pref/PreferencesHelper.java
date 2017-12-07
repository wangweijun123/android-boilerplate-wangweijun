package com.wangweijun.structure.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangweijun on 2017/12/6.
 */

public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    public static final String KEY_SCORE = "score";

    private final SharedPreferences mPref;

    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public float getScore() {
        return mPref.getFloat(KEY_SCORE, 0);
    }

    public void setScore(float score) {
        mPref.edit().putFloat(KEY_SCORE, score).commit();
    }
}
