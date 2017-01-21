package com.sandy.blog.greendaodemo.dao;

import android.text.TextUtils;

import com.sandy.blog.greendaodemo.MyApplication;

import java.util.List;

/**
 * Created by Sandy Luo on 2017/1/17.
 */

public class DaoHelper {

    private MyApplication myApplication = ((MyApplication) (MyApplication.getContext()));

    private static DaoHelper instance;

    public static DaoHelper getInstance() {
        if (instance == null) {
            instance = new DaoHelper();
        }
        return instance;
    }

    public SingerDao getSingerDao() {
        return myApplication.getDaoSession().getSingerDao();
    }

    /**获取歌手的列表*/
    public List<Singer> getSingerList(String country) {
        if (!TextUtils.isEmpty(country)) {
            List<Singer> singers = getSingerDao()
                    .queryBuilder()
                    .where(SingerDao.Properties.Country.eq(country))
                    .orderDesc()
                    .build()
                    .list();
            return singers;
        }

        List<Singer> singers = getSingerDao()
                .queryBuilder()
                .build()
                .list();

        return singers;
    }
}
