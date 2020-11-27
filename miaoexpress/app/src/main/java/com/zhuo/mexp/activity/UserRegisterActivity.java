package com.zhuo.mexp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhuo.mexp.dao.UserDao;
import com.zhuo.mexp.model.UserInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    UserDao userDao=new UserDao(this);
    EditText userid_et,password_et,username_et,telphone_et,address_et;
    ImageView touimageview;
    Button gettoupicbt;
    Toolbar toolbar;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE ,Manifest.permission.READ_PHONE_STATE}, 0);
        }
        findViewById(R.id.reset).setOnClickListener(this);//this表示UserRegisterActivity也是一个监听器
        userid_et=findViewById(R.id.userid);
        password_et=findViewById(R.id.password);
        username_et=findViewById(R.id.username);
        telphone_et=findViewById(R.id.telphone);
        address_et=findViewById(R.id.address);
        touimageview=findViewById(R.id.touimagevw);
        gettoupicbt=findViewById(R.id.gettoupict);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(R.color.white);
        setSupportActionBar(toolbar);
        findViewById(R.id.registerbt).setOnClickListener(this);
        findViewById(R.id.goback).setOnClickListener(this);
        gettoupicbt.setOnClickListener(this);
    }
    private  void formatCheck() {
        Pattern p = Pattern.compile("^u_[a-zA-Z_0-9]{3,5}");//正则表达式
        Matcher m = p.matcher(userid_et.getText().toString());
        Pattern p_phone = Pattern.compile("^1[3,5,8,7][0-9]{9}");
        Matcher m_phone = p_phone.matcher(telphone_et.getText().toString());
        UserInfo user = userDao.findUserById(userid_et.getText().toString());
        if (userid_et.getText().toString().indexOf("u")<0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserRegisterActivity.this);
            builder.setTitle("提示");
            builder.setMessage("输入账号格式错误");
            builder.setPositiveButton("确定", null);
            builder.show();
        } else {
            handleRegister(user, m, m_phone);
        }
    }

    public  void handleRegister(Object o,Matcher m,Matcher m_phone){
        if (o != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserRegisterActivity.this);
            builder.setTitle("提示");
            builder.setMessage("该用户名已存在");
            builder.setPositiveButton("确定", null);
            builder.show();
        } else if (!m.matches()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserRegisterActivity.this);
            builder.setTitle("提示");
            builder.setMessage("输入账号格式错误");
            builder.setPositiveButton("确定", null);
            builder.show();
        } else if (!m_phone.matches()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserRegisterActivity.this);
            builder.setTitle("提示");
            builder.setMessage("输入电话格式错误");
            builder.setPositiveButton("确定", null);
            builder.show();
        }else {
            UserInfo user = new UserInfo();
            user.setUser_id(userid_et.getText().toString());
            user.setUser_password(password_et.getText().toString());
            user.setUser_name(username_et.getText().toString());
            user.setUser_tel(Long.parseLong(telphone_et.getText().toString()));
            user.setUser_address(address_et.getText().toString());
            if(userDao.addUser(user)>0){
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("userId",user.getUser_id());
                intent.setClass(UserRegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();//
            }else{
                Toast.makeText(this,"注册失败！",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goback:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.reset:
                userid_et.setText("");
                password_et.setText("");
                telphone_et.setText("");
                username_et.setText("");
                address_et.setText("");
                break;
            case R.id.registerbt:
                formatCheck();
                break;
            case R.id.gettoupict:
                formatCheck();
                break;
        }


    }
}