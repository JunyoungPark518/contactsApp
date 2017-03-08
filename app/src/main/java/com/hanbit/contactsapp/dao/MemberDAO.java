package com.hanbit.contactsapp.dao;

import com.hanbit.contactsapp.domain.MemberBean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb2005 on 2017-03-08.
 */

public class MemberDAO {
    public static MemberDAO getInstance() { return new MemberDAO(); }
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
