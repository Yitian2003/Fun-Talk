package com.witlife.funtalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.witlife.funtalk.R;
import com.witlife.funtalk.bean.Model;
import com.witlife.funtalk.bean.UserBean;
import com.witlife.funtalk.utils.MD5Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_confirm_pwd)
    EditText edConfirmPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private UserBean user;

    @Override
    public void initData() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();


            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void doRegister() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(user.getUserName(), user.getPassword());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            saveUser(user);
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "Register Failure! " + e.getDescription(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void checkInput() {

        String username = edUsername.getText().toString().trim();
        String pwd = edPwd.getText().toString().trim();
        String pwd2 = edConfirmPwd.getText().toString().trim();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2)){
            Toast.makeText(this, "Usrname and password are required", Toast.LENGTH_SHORT).show();
        } else if(!pwd.equals(pwd2)){
            Toast.makeText(this, "Password are not the same", Toast.LENGTH_SHORT).show();
        } else {
            if (user == null){
                user = new UserBean();
            }
            user.setUserName(username);
            user.setPassword(MD5Utils.MD5(pwd));

            doRegister();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initTitle() {
        ivBack.setVisibility(View.INVISIBLE);
        tvTitle.setVisibility(View.INVISIBLE);
        ivSetting.setVisibility(View.INVISIBLE);
    }

}
