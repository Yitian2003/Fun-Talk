package com.witlife.funtalk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.witlife.funtalk.dao.UserAccountTable;

/**
 * Created by bruce on 14/09/2017.
 */

public class UserAccountDBHelper extends SQLiteOpenHelper{

    public UserAccountDBHelper(Context context) {
        super(context, "account.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserAccountTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
