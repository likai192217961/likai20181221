package com.example.bwie.likai20181221yuekao.callBack;

import com.example.bwie.likai20181221yuekao.bean.ShouyeBean;

public interface ShouyeCallBack {
    void setSuccess(ShouyeBean data);
    void setError(String error);
}
