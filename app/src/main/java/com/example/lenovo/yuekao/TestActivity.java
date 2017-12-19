package com.example.lenovo.yuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText psw;
    private Button logen;
    private Button um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        psw = (EditText) findViewById(R.id.psw);
        logen = (Button) findViewById(R.id.logen);
        um = (Button) findViewById(R.id.um);

        logen.setOnClickListener(this);
        um.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logen:
                String name = this.name.getText().toString();
                String psw = this.psw.getText().toString();
                String names = "[1][3,5][5,2,7][0-9]{8}";
                String psws = "[a-zA-Z]{1,}";
                boolean matches = Pattern.matches(names, name);
                boolean matches1 = Pattern.matches(psws, psw);

                if(matches == true && matches1==true){
                    Toast.makeText(TestActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TestActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.um:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(TestActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Toast.makeText(TestActivity.this, data.get("gender").toString(), Toast.LENGTH_SHORT).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(TestActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(TestActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
