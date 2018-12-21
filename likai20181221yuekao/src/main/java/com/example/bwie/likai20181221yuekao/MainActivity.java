package com.example.bwie.likai20181221yuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bwie.likai20181221yuekao.adapter.ShouyeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String mUrl = "http://www.zhaoapi.cn/ad/getAd";
    private ViewPager viewpager;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private RadioGroup rg1;
    private int tupian[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String tupians[] = {"http://www.zhaoapi.cn/images/quarter/ad1.png", "http://www.zhaoapi.cn/images/quarter/ad2.png",
            "http://www.zhaoapi.cn/images/quarter/ad3.png", "http://www.zhaoapi.cn/images/quarter/ad4.png"};
    private List<ImageView> list = new ArrayList<>();
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        for (int i = 0; i < tupians.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(tupian[i]);
            list.add(imageView);
        }
        ShouyeAdapter adapter = new ShouyeAdapter(list);
        viewpager.setAdapter(adapter);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int p = i % list.size();
                rg1.check(rg1.getChildAt(p).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                final int currentItem = viewpager.getCurrentItem();
                if (currentItem == list.size() - 1) {
                    b1.setVisibility(View.VISIBLE);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this,TwoActivity.class));
                            Toast.makeText(MainActivity.this, "很高兴老司机来购物！！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    b1.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);
        r4 = (RadioButton) findViewById(R.id.r4);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b1.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:

                break;
        }
    }
}
