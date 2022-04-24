package com.example.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.name);
        et2 = findViewById(R.id.pawd);
    }

    public void starMainActivity2(View view) {
        if (et1.getText().toString().length() == 0 && et2.getText().toString().length() == 0) {
            Toast.makeText(this, "账号密码不能为空，请补充完整", Toast.LENGTH_SHORT).show();
        } else if (et1.getText().toString().equals("888999")
                && et2.getText().toString().equals("123456")) {
            //跳转到管理员登陆后的页面
            startActivity(new Intent(this, MainActivity2.class));
        } else {
            Toast.makeText(this, "密码错误！请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    public void starMinActivity3(View view) {
        if (et1.getText().toString().length() == 0 && et2.getText().toString().length() == 0) {
            Toast.makeText(this, "账号密码不能为空，请补充完整", Toast.LENGTH_SHORT).show();
        } else {
            //跳转到职工登陆后的页面
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            //传递用户名给下一个页面；
            intent.putExtra("user", et1.getText().toString());
            startActivity(intent);
            //startActivity(new Intent(this, MainActivity3.class));
        }
    }
}