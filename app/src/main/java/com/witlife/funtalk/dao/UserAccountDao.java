package com.witlife.funtalk.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.witlife.funtalk.bean.UserBean;
import com.witlife.funtalk.db.UserAccountDBHelper;

/**
 * Created by bruce on 14/09/2017.
 */

public class UserAccountDao {

    private final UserAccountDBHelper helper;

    public UserAccountDao(Context context) {
        helper = new UserAccountDBHelper(context);
    }

    public void addAccount(UserBean user){
        SQLiteDatabase db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_ID, user.getId());
        values.put(UserAccountTable.COL_NAME, user.getUserName());
        values.put(UserAccountTable.COL_IMAGE, user.getImageUrl());
        values.put(UserAccountTable.COL_PWD, user.getPassword());

        db.replace(UserAccountTable.TAB_NAME, null, values);
    }

    public UserBean getAccountById(String id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "select * from " + UserAccountTable.TAB_NAME + " where "
                + UserAccountTable.COL_ID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{id});

        UserBean userBean = null;
        if(cursor.moveToNext()){
            userBean = new UserBean();
            userBean.setId(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_ID)));
            userBean.setUserName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userBean.setImageUrl(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_IMAGE)));
        }

        cursor.close();

        return userBean;
    }
}
