package com.hanbit.contactsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hb2005 on 2017-03-10.
 */

public abstract class ListQuery extends QueryFactory{
    SQLiteOpenHelper helper;
    public ListQuery(Context context) {
        super(context);
        helper = new DatabaseHelper(context);
    }

    @Override
    public SQLiteDatabase getDatabase() {
        return helper.getReadableDatabase();
    }
    public abstract ArrayList<?> list(String sql);
}
