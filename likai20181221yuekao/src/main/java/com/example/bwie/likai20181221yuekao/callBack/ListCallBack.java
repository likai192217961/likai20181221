package com.example.bwie.likai20181221yuekao.callBack;

import com.example.bwie.likai20181221yuekao.bean.ListBean;

public interface ListCallBack {
    void setSuccess(ListBean data);
    void setError(String error);
}
