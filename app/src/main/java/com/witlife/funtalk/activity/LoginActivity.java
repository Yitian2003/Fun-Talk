package com.witlife.funtalk.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.witlife.funtalk.R;
import com.witlife.funtalk.bean.Model;
import com.witlife.funtalk.bean.UserBean;
import com.witlife.funtalk.utils.MD5Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private UserBean user;

    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.divider)
    View divider;
    private ProgressDialog dialog;

    @Override
    public void initData() {
        user = readUser();

        if (user == null) {

        } else {
            edUsername.setText(user.getUserName());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkInput() {
        if (TextUtils.isEmpty(edUsername.getText().toString().trim()) || TextUtils.isEmpty(edPwd.getText().toString().trim())) {
            Toast.makeText(this, "Please input username or password", Toast.LENGTH_SHORT).show();
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        final String username = edUsername.getText().toString().trim();
        final String pwd = MD5Utils.MD5(edPwd.getText().toString().trim());

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage("Logining...");
        dialog.show();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(username, pwd, new EMCallBack() {
                    @Override
                    public void onSuccess() {

                        user.setId(username);

                        // save to model
                        Model.getInstance().loginSuccess(user);
                        // save user info to local db
                        Model.getInstance().getUserAccountDao().addAccount(user);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                Toast.makeText(LoginActivity.this, "Login Fail: " + s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initTitle() {

        ivBack.setVisibility(View.INVISIBLE);
        ivSetting.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);
    }

}
