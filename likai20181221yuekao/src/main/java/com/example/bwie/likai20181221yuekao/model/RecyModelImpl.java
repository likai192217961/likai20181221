package com.example.bwie.likai20181221yuekao.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.callBack.ListCallBack;
import com.example.bwie.likai20181221yuekao.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class RecyModelImpl implements RecyModel {
    private ListCallBack callBack1;
    @SuppressLint("HandlerLeak")
    private Handler gd=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                String s= (String) msg.obj;
                Gson gson=new Gson();
                ListBean listBean = gson.fromJson(s, ListBean.class);
                callBack1.setSuccess(listBean);
            }
        }
    };
    @Override
    public void setListData(final String url, final ListCallBack callBack) {
        this.callBack1=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = OkHttpUtils.getInstance().get(url);
                    gd.sendMessage(gd.obtainMessage(0,s));
                } catch (IOException e) {
                    Looper.prepare();
                    callBack.setError(e.getMessage()+"异常");
                    Looper.loop();
                }
            }
        }).start();
    }
}
