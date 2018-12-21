package com.example.bwie.likai20181221yuekao.utils;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装工具类
 * 需要用到单例设计模式
 * 1：懒汉式
 * 2：饿汉式
 * 3：DCL
 * 4：容器模式(前面三种都不是很完善的处理单例)
 * <p>
 * 单例模式：保证这个内存空间里面只能有实例
 * 1：私有化构造器？ 保证其他类不能创建实例
 * 2：想到了这个实例在这个内存空间还要提供给别的类使用所以要封装成一个方法让人调用
 */
public class OkHttpUtils {
    private OkHttpClient mOkHttpClient=null;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        return OkHttpHolder.utils;
    }

    static class OkHttpHolder {
        private static final OkHttpUtils utils = new OkHttpUtils();
    }

    //封装同步的Get请求方式
    public String getPid(String url,int pid) throws IOException {
        Request request = new Request.Builder().url(url+pid).build();
        Response execute = mOkHttpClient.newCall(request).execute();
        return execute.body().string();
    }
    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response execute = mOkHttpClient.newCall(request).execute();
        return execute.body().string();
    }


}
