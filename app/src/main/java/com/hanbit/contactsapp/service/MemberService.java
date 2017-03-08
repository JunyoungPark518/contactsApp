package com.hanbit.contactsapp.service;

import com.hanbit.contactsapp.domain.MemberBean;

import java.util.List;

/**
 * Created by hb2005 on 2017-03-08.
 */

public interface MemberService {
    void create(MemberBean bean);
    MemberBean findOne(MemberBean bean);
    List<MemberBean> findSome(MemberBean bean);
    List<MemberBean> list();
    void change(MemberBean bean);
    void remove(MemberBean bean);
}
