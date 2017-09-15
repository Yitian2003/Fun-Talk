package com.witlife.funtalk.dao;

/**
 * Created by bruce on 14/09/2017.
 */

public class UserAccountTable {

    public static final String TAB_NAME = "tab_account";
    public static final String COL_NAME = "name";
    public static final String COL_ID = "id";
    public static final String COL_IMAGE = "imageUrl";
    public static final String COL_PWD = "pwd";

    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_ID + " text primary key,"
            + COL_NAME + " text,"
            + COL_IMAGE + " text,"
            + COL_PWD + " text);";

}
