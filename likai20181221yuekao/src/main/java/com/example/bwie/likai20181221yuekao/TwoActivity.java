package com.example.bwie.likai20181221yuekao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bwie.likai20181221yuekao.adapter.RecyAdapter;
import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.bean.ShouyeBean;
import com.example.bwie.likai20181221yuekao.presenter.ListPresenterImpl;
import com.example.bwie.likai20181221yuekao.presenter.RecyPresentreImpl;
import com.example.bwie.likai20181221yuekao.view.ListView;
import com.example.bwie.likai20181221yuekao.view.ShouYeView;
import com.example.bwie.likai20181221yuekao.weight.Sou;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener,ShouYeView,ListView{

    private Button erweima;
    private Button qiehuan;
    private RecyclerView recy;
    private String mUrl="http://www.zhaoapi.cn/product/getCatagory";
    private String mUrllist="http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private List<ShouyeBean.DataBean> list1=new ArrayList<>();
    private List<ListBean.DataBean> list=new ArrayList<>();
    private ListPresenterImpl presenter;
    private RecyPresentreImpl presenter1;
    private RecyAdapter adapter;
    private boolean flag=true;
    private final int r=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        adapter = new RecyAdapter(list1,this);
        recy.setAdapter(adapter);
        presenter.setSyResponse(mUrl);
        presenter1.setListResponse(mUrllist);
    }
    private void initView() {
        erweima = (Button) findViewById(R.id.erweima);
        qiehuan = (Button) findViewById(R.id.qiehuan);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        presenter=new ListPresenterImpl(this);
        presenter1=new RecyPresentreImpl(this);
        erweima.setOnClickListener(this);
        qiehuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.erweima:
                Intent intent=new Intent(TwoActivity.this,CaptureActivity.class);
                startActivityForResult(intent,r);
                break;
            case R.id.qiehuan:
                    if (flag){
                        list.clear();
                        presenter1.setListResponse(mUrllist);
                        recy.setLayoutManager(new LinearLayoutManager(this));
                        adapter = new RecyAdapter(list1,this);
                        recy.setAdapter(adapter);
                        flag=false;
                    }else{
                        recy.setLayoutManager(new GridLayoutManager(this,2));
                        list.clear();
                        presenter1.setListResponse(mUrllist);
                        adapter = new RecyAdapter(list1,this);
                        recy.setAdapter(adapter);
                        flag=true;
                    }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==r&&resultCode==RESULT_OK){
            if (data!=null){
                String extra = data.getStringExtra(Constant.CODED_CONTENT);
                Toast.makeText(this, "您扫描的是"+extra, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void success(ShouyeBean data) {
        list1.addAll(data.getData());
        //Toast.makeText(this,list1.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(ListBean data) {
        list.addAll(data.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error+"异常", Toast.LENGTH_SHORT).show();
    }
}
