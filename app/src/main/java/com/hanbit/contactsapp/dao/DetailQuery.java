package com.hanbit.contactsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hb2005 on 2017-03-10.
 */

public abstract class DetailQuery extends QueryFactory{
    SQLiteOpenHelper helper;
    public DetailQuery(Context context) {
        super(context);
        helper = new DatabaseHelper(context);
    }

    @Override
    public SQLiteDatabase getDatabase() { return helper.getReadableDatabase(); }
    public abstract Object findOne(String sql);
}
