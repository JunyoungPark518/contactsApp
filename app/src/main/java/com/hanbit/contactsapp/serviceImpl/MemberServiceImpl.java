package com.hanbit.contactsapp.serviceImpl;

import com.hanbit.contactsapp.dao.MemberDAO;
import com.hanbit.contactsapp.domain.MemberBean;
import com.hanbit.contactsapp.service.MemberService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hb2005 on 2017-03-08.
 */

public class MemberServiceImpl implements MemberService {
    private MemberDAO dao;
    private static MemberServiceImpl instance = new MemberServiceImpl();
    public static MemberServiceImpl getInstance(){ return instance; }
    private List<MemberBean> list;
    Iterator<MemberBean> it;

    public MemberServiceImpl() {
        list = new ArrayList<>();
        it = list.iterator();
        dao = MemberDAO.getInstance();
    }

    @Override
    public void create(MemberBean bean) {
    }

    @Override
    public MemberBean findOne(MemberBean bean) {
        return null;
    }

    @Override
    public List<MemberBean> findSome(MemberBean bean) {
        return null;
    }

    @Override
    public List<MemberBean> list() {
        return null;
    }

    @Override
    public void change(MemberBean bean) {
    }

    @Override
    public void remove(MemberBean bean) {
    }
}
