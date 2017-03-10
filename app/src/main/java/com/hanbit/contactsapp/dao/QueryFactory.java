package com.hanbit.contactsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hb2005 on 2017-03-10.
 */

public abstract class QueryFactory {
    Context context;

    public QueryFactory(Context context) {
        this.context = context;
    }

    public abstract SQLiteDatabase getDatabase();
}
