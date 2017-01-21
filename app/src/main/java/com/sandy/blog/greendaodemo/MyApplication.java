package com.sandy.blog.greendaodemo;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sandy.blog.greendaodemo.dao.DaoMaster;
import com.sandy.blog.greendaodemo.dao.DaoSession;

/**
 * Created by Sandy Luo on 2017/1/12.
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static Context mContext;
    public SQLiteDatabase db;
    public DaoSession daoSession;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setupDatabase();
    }


    private void setupDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, "green_dao_demo.db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static Context getContext() {
        return mContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
