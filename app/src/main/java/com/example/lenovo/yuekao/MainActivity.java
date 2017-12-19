package com.example.lenovo.yuekao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.yuekao.viewpageradapter.MyViewPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager ViewPager;
    private RadioGroup RadioGroup;
    private ArrayList<View> mList = new ArrayList<>();
    private Button mBtn;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private Context mContext = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShare = mContext.getSharedPreferences("user",MODE_PRIVATE);
        //编辑器
        mEditor = mShare.edit();
        boolean isTrue = mShare.getBoolean("isTrue", false);
        if (isTrue){
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
            finish();
        }
        initView();
    }

    private void initView() {
        ViewPager = (ViewPager) findViewById(R.id.ViewPager);
        RadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        View one = getLayoutInflater().inflate(R.layout.view_one, null);
        View two = getLayoutInflater().inflate(R.layout.view_two, null);
        View three = getLayoutInflater().inflate(R.layout.view_three, null);
        mBtn = (Button) three.findViewById(R.id.ViewThree_Btn);
        mBtn.setOnClickListener(this);
        mList.add(one);
        mList.add(two);
        mList.add(three);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(mList);
        ViewPager.setAdapter(myViewPagerAdapter);
        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Radio_one:
                        ViewPager.setCurrentItem(0);
                        break;
                    case R.id.Radio_Two:
                        ViewPager.setCurrentItem(1);
                        break;
                    case R.id.Radio_Three:
                        ViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        ViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) RadioGroup.getChildAt(position);
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ViewThree_Btn:
                mEditor.putBoolean("isTrue",true);
                mEditor.commit();
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
