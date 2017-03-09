package com.hanbit.contactsapp.service;

/**
 * Created by hb2005 on 2017-03-09.
 */

public interface CountService {
    public int count();
    public int countByName(String name);
    public int countByNumber(String number);
}
