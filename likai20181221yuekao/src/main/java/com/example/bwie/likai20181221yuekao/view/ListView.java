package com.example.bwie.likai20181221yuekao.view;

import com.example.bwie.likai20181221yuekao.bean.ListBean;

public interface ListView {
    void success(ListBean data);
    void error(String error);
}
