package com.hanbit.contactsapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hanbit.contactsapp.domain.MemberBean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb2005 on 2017-03-08.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "SQLite";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Member (" +
                "seq VARCHAR(3)" +
                "" +
                "" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Member");
        onCreate(db);
    }

    void insert(MemberBean bean) {
        String sql = "INSERT INTO Contact(seq, name, phone, addr) SET ('','','','')";
    }

    MemberBean selectOne(MemberBean bean) {
        MemberBean temp = new MemberBean();
        ResultSet rs = null;

        String sql = String.format("SELECT seq, name, phone, addr FROM Contact WHERE seq='%s'",bean.getSeq());
        return temp;
    }

    List<MemberBean> selectSome(MemberBean bean) {
        List<MemberBean> list = new ArrayList<>();
        String sql = String.format("SELECT seq, name, phone, addr FROM Contact WHERE name like '%s'",bean.getName());
        return list;
    }

    List<MemberBean> selectAll(MemberBean bean) {
        List<MemberBean> list = new ArrayList<>();
        String sql = String.format("SELECT seq, name, phone, addr FROM Contact");
        return list;
    }

    void update(MemberBean bean) {
        String sql = String.format("UPDATE Contact SET name='%s', phone='%s', addr='%s' WHERE seq='%s'",bean.getName(), bean.getPhone(), bean.getAddr(), bean.getSeq());
    }

    void delete(MemberBean bean) {
        String sql = String.format("DELETE FROM Contact WHERE seq='%s'",bean.getSeq());
    }
}
