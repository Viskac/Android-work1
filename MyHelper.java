package com.example.mywork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {

    //1.对外提供函数 单例模式
    private static SQLiteOpenHelper mInstance;

    public static synchronized SQLiteOpenHelper getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyHelper(context, "record.db", null, 1);
        }
        return mInstance;
    }

    //2.构造函数私有化
    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表 表名 _id ...
        String sql = "create table f1(_id integer primary key autoincrement, record text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
