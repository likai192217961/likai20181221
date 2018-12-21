package com.example.bwie.likai20181221yuekao.presenter;

import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.callBack.ListCallBack;
import com.example.bwie.likai20181221yuekao.model.ListModelImpl;
import com.example.bwie.likai20181221yuekao.model.RecyModelImpl;
import com.example.bwie.likai20181221yuekao.view.ListView;

public class RecyPresentreImpl implements RecyPresenter{
    private ListView view1;
    private RecyModelImpl listModel1;

    public RecyPresentreImpl(ListView view1) {
        this.view1 = view1;
        listModel1=new RecyModelImpl();
    }

    @Override
    public void setListResponse(String url) {
        listModel1.setListData(url, new ListCallBack() {
            @Override
            public void setSuccess(ListBean data) {
                view1.success(data);
            }

            @Override
            public void setError(String error) {
                view1.error(error);
            }
        });
    }
    public void onListTauch(){
        if (listModel1!=null){
            listModel1=null;
        }
        if (view1!=null){
            view1=null;
        }
    }
}
