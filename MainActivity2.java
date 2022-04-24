package com.example.mywork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    //存放数据的集合
    ArrayList<String> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void admin_view(View view) {
        //调用getInstance
        SQLiteOpenHelper helper = MyHelper.getmInstance(this);
        //获取SQLiteDatabase的可读对象
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            //返回游标
            Cursor cursor = db.rawQuery("select * from f1", null);
            //迭代游标
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String record = cursor.getString(cursor.getColumnIndex("record"));

                //将考勤数据放入集合
                list.add(record + "打卡一次");

                //Log.e("TAG", "show: _id" + _id + "record:" + record);
                //Toast.makeText(this, "show: _id:" + _id + "record:" + record, Toast.LENGTH_SHORT).show();
            }
            //关闭游标和数据库
            cursor.close();
            db.close();
        }
        //在listview中显示查询的数据
        //创建arrayadapter对象
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);      //从集合list里拿数据
        ListView listView = findViewById(R.id.lv);
        //为listview设置adapter
        listView.setAdapter(arrayAdapter);
    }

    //删除数据库数据
    public void admain_delete(View view) {
        //调用getInstance
        SQLiteOpenHelper helper = MyHelper.getmInstance(this);
        //获取SQLiteDatabase的可读写对象
        SQLiteDatabase db = helper.getWritableDatabase();
        //删除
        db.execSQL("delete from f1");
        Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
