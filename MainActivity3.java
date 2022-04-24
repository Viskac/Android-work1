package com.example.mywork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    private String s;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//获取从登陆界面传来的用户名
        Intent inte = getIntent();
        s = inte.getStringExtra("user");
    }

    //职工打卡按钮
    public void worker_check(View view) {
        //获取时间，拿到当前的打卡时间，作为打卡信息
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //提示在当前时间打卡成功
        Toast.makeText(this, simpleDateFormat.format(date) + "  打卡成功！", Toast.LENGTH_SHORT).show();

        //添加数据
        //调用getInstance
        SQLiteOpenHelper helper = MyHelper.getmInstance(this);
        //获取SQLiteDatabase的可读写对象
        SQLiteDatabase db = helper.getWritableDatabase();
        //创建存放数据的对象
        ContentValues values = new ContentValues();
        //将“某某用户在什么时间打卡”插入数据库
        values.put("record", s + "在" + simpleDateFormat.format(date));
        db.insert("f1", null, values);
        db.close();
    }

    //职工查看自己打卡记录的按钮
    public void worker_view(View view) {
//存放在listview中显示的数据
        ArrayList<String> list2 = new ArrayList<>();

        //调用getInstance
        SQLiteOpenHelper helper = MyHelper.getmInstance(this);
        //获取SQLiteDatabase的可读对象
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            //返回游标,模糊查找含有当前登录的用户名的数据
            Cursor cursor = db.rawQuery("SELECT  * FROM f1" + " where record like '%" + s + "%'", null);
            //迭代游标
            while (cursor.moveToNext()) {
                //拿到模糊查找后的数据
                String record2 = cursor.getString(cursor.getColumnIndex("record"));
                //放入集合，用于在listview中显示
                list2.add(record2 + "打卡一次");
            }
            cursor.close();
            db.close();
        }
        //创建adapter对象
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(MainActivity3.this,
                android.R.layout.simple_list_item_1, list2);
        ListView listView2 = findViewById(R.id.lv_worker);
        //设置adapter
        listView2.setAdapter(arrayAdapter2);
    }

    //职工删除自己的打卡记录
    public void worker_delete(View view) {

        //调用mInstance
        SQLiteOpenHelper helper = MyHelper.getmInstance(this);
        //获取读写对象
        SQLiteDatabase db = helper.getWritableDatabase();

        //返回游标,模糊查找含有当前登录的用户名的数据
        Cursor cursor = db.rawQuery("SELECT  * FROM f1" + " where record like '%" + s + "%'", null);
        //迭代游标
        while (cursor.moveToNext()) {
            //拿到模糊查找后的数据
            String record3 = cursor.getString(cursor.getColumnIndex("record"));
            //删除模糊查找后的数据
            db.delete("f1", "record=?", new String[]{record3});
        }
        db.close();
    }
}
