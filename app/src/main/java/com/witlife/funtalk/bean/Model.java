package com.witlife.funtalk.bean;

import android.content.Context;

import com.witlife.funtalk.dao.UserAccountDao;
import com.witlife.funtalk.db.DBManager;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bruce on 13/09/2017.
 */

public class Model {

    private Context contex;
    private static Model model;
    private ExecutorService executors = Executors.newCachedThreadPool();
    private UserAccountDao userAccountDao;
    private DBManager dbManager;

    private Model(){

    }

    public static Model getInstance(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public void init(Context context){
        this.contex = context;

        userAccountDao = new UserAccountDao(context);
    }

    public ExecutorService getGlobalThreadPool(){
        return executors;
    }

    public UserAccountDao getUserAccountDao() {
        return userAccountDao;
    }

    public void loginSuccess(UserBean user) {

        if (user != null){
            //if ()
        }
    }
}
