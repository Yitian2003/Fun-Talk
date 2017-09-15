package com.witlife.funtalk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.witlife.funtalk.bean.UserBean;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bruce on 12/09/2017.
 */

abstract class BaseActivity extends FragmentActivity{

    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);

        initTitle();

        initView();

        initData();
    }

    public abstract void initData();

    public void initView(){

    }

    protected abstract int getLayout();

    public abstract void initTitle();

    public UserBean readUser(){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);

        if(sp != null){
            UserBean user = new UserBean();
            user.setUserName(sp.getString("userName", ""));
            user.setImageUrl(sp.getString("imageUrl", ""));
            return user;
        }
        return null;
    }

    public void saveUser(UserBean user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("userName", user.getUserName());
        editor.putString("imageUrl", user.getImageUrl());

        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
