package com.release.mvp.utils.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JoshuaChang on 2017/6/28.
 */

public class ConmonInfoListRes<T> implements Serializable {
    public int code;// 200
    public List<T> info;
    public String message;


    public boolean success() {
        return code == 200;
    }

    public boolean isTokenExpire() {
        return code == 406;
    }
}