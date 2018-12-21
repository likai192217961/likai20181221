package com.example.bwie.likai20181221yuekao.presenter;

import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.bean.ShouyeBean;
import com.example.bwie.likai20181221yuekao.callBack.ListCallBack;
import com.example.bwie.likai20181221yuekao.callBack.ShouyeCallBack;
import com.example.bwie.likai20181221yuekao.model.ListModelImpl;
import com.example.bwie.likai20181221yuekao.view.ListView;
import com.example.bwie.likai20181221yuekao.view.ShouYeView;

public class ListPresenterImpl implements ListPresenter{
    private ShouYeView view;
    private ListModelImpl listModel;


    public ListPresenterImpl(ShouYeView view) {
        this.view = view;
        listModel=new ListModelImpl();
    }

    @Override
    public void setSyResponse(String url) {
        listModel.setSyDta(url, new ShouyeCallBack() {
            @Override
            public void setSuccess(ShouyeBean data) {
                view.success(data);
            }

            @Override
            public void setError(String error) {
                view.error(error);
            }
        });
    }



    public void onTauch(){
        if (listModel!=null){
            listModel=null;
        }
        if (view!=null){
            view=null;
        }
    }

}
