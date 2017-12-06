package com.wangweijun.structure.data.model;

/**
 * Created by wangweijun1 on 2017/11/27.
 */

public class Contributor {
    public String login;
    public int contributions;
    public String avatar_url;

    @Override
    public String toString() {
        return "login:"+login+", contributions:"+contributions;
    }
}
