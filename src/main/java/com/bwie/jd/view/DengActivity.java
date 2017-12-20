package com.bwie.jd.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.jd.R;
import com.bwie.jd.bean.EventBean;
import com.bwie.jd.bean.LoginBean;
import com.bwie.jd.bean.LoginCgBean;
import com.bwie.jd.logincallback.LoginCallBack;
import com.bwie.jd.logincallback.LoginnewCallBack;
import com.bwie.jd.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

public class DengActivity extends AppCompatActivity{

    private LoginPresenter loginPresenter;
    private EditText mima_deng;
    private EditText zhanghao_deng;
    private CheckBox checkBox;
    private Button deng_wode;
    private Button zhu_wode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng);

        mima_deng = (EditText) findViewById(R.id.mima_deng);
        zhanghao_deng = (EditText) findViewById(R.id.zhanghao_deng);
        deng_wode = (Button) findViewById(R.id.deng_wode);
        zhu_wode = (Button) findViewById(R.id.zhu_wode);
        loginPresenter = new LoginPresenter(new LoginCallBack() {
            @Override
            public void phoneEmpty() {
                Toast.makeText(DengActivity.this, "phone is empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void passwordEmpty() {
                Toast.makeText(DengActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginSuccess(final LoginBean loginBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String msg = loginBean.getMsg();
                        LoginBean.DataBean data = loginBean.getData();
                        int uid = data.getUid();
                        System.out.println(msg+"--"+uid);

                        if(msg.equals("登录成功")){
                            EventBus.getDefault().postSticky(new EventBean(msg,uid));
                            Toast.makeText(DengActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }

            @Override
            public void loginFailed(int code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DengActivity.this, "loginFailed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }, new LoginnewCallBack() {
            @Override
            public void phoneEmpty() {
                Toast.makeText(DengActivity.this, "phone is empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void passwordEmpty() {
                Toast.makeText(DengActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginSuccess(final LoginCgBean loginBean) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String msg = loginBean.getMsg();
                        System.out.println(msg);
                        if(msg.equals("注册成功")){
                            Toast.makeText(DengActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void loginFailed(int code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DengActivity.this, "loginFailed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        deng_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.loginPanduan(zhanghao_deng.getText().toString(),mima_deng.getText().toString());
            }
        });

        zhu_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.loginnewPanduan(zhanghao_deng.getText().toString(),mima_deng.getText().toString());
            }
        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detach();
    }
}
