package com.example.bwie.likai20181221yuekao.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.bean.ShouyeBean;
import com.example.bwie.likai20181221yuekao.callBack.ListCallBack;
import com.example.bwie.likai20181221yuekao.callBack.ShouyeCallBack;
import com.example.bwie.likai20181221yuekao.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

public class ListModelImpl implements ListModel{
    private ShouyeCallBack callBack;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                String s= (String) msg.obj;
                Gson gson=new Gson();
                ShouyeBean shouyeBean = gson.fromJson(s, ShouyeBean.class);
                callBack.setSuccess(shouyeBean);
            }
        }
    };

    @Override
    public void setSyDta(final String url, final ShouyeCallBack callBack) {
        this.callBack=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = OkHttpUtils.getInstance().get(url);
                    handler.sendMessage(handler.obtainMessage(0,s));
                } catch (IOException e) {
                    Looper.prepare();
                    callBack.setError(e.getMessage()+"异常");
                    Looper.loop();
                }
            }
        }).start();
    }

}
