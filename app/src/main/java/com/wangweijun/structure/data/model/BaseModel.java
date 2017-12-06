package com.wangweijun.structure.data.model;

import java.io.Serializable;

/**
 * Created by wangweijun on 2017/12/5.
 */

public class BaseModel {

    public Icon icon;
    public String name;
    public String packagename;

    public class Icon implements Serializable {
        public String url;
    }

    @Override
    public String toString() {
        return "name:" + name + ", packagename:" + packagename + ", url:" + icon.url;
    }
}
