package com.zhuo.mexp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuo.mexp.dao.DistributorDao;
import com.zhuo.mexp.dao.UserDao;
import com.zhuo.mexp.model.Distributor;
import com.zhuo.mexp.model.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText username_et,password_et;
    Button login_bt;
    TextView regist_tv;
    SharedPreferences sp= null;//保存登录成功后的用户名
    SharedPreferences.Editor editor=null;
    UserDao userDao=new UserDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username_et=findViewById(R.id.username);
        password_et=findViewById(R.id.password);
        login_bt=findViewById(R.id.login);
        regist_tv=findViewById(R.id.register);
        Intent intent=getIntent();
        String userid=intent.getStringExtra("userId");
        username_et.setText(userid);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=username_et.getText().toString();
                String password=password_et.getText().toString();
                if(username.startsWith("u")){
                    UserDao userDao=new UserDao(LoginActivity.this);//context
                    UserInfo userInfo=userDao.findUserById(username);
                    if(userInfo==null){
                        Toast.makeText(LoginActivity.this,"用户不存在！",Toast.LENGTH_LONG).show();
                    }else if(userInfo.getUser_status()==0){
                       if(userDao.login(username,password)){
                           sp=getSharedPreferences("userdata", Context.MODE_PRIVATE);//1.创建sp
                           editor=sp.edit();//2.实例化editor
                           editor.putString("userId",username);//3.通过edit存放值
                           editor.commit();//4.确认提交值
                           Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                           Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                           finish();
                       }else{
                           Toast.makeText(LoginActivity.this,"密码错误，登录失败！",Toast.LENGTH_LONG).show();
                       }
                   }else if(userInfo.getUser_status()==1){
                       Toast.makeText(LoginActivity.this,"账号正审核中，暂不能登录！",Toast.LENGTH_LONG).show();
                   }else if(userInfo.getUser_status()==2){
                       Toast.makeText(LoginActivity.this,"账号被封，请联系管理员！",Toast.LENGTH_LONG).show();
                   }
                }else if(username.startsWith("d")){
                    DistributorDao distributorDao=new DistributorDao(LoginActivity.this);//context
                    Distributor distributor=distributorDao.findDistributorById(username);
                    if(distributor==null){
                        Toast.makeText(LoginActivity.this,"用户不存在！",Toast.LENGTH_LONG).show();
                    }else if(distributor.getDistributor_status()==0){
                        if(distributorDao.login(username,password)){
                            sp=getSharedPreferences("userdata", Context.MODE_PRIVATE);//1.创建sp userdata.xml
                            editor=sp.edit();//2.实例化editor
                            editor.putString("distributorId",username);//3.通过edit存放值
                            editor.commit();//4.确认提交值
                            Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,DistribMainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"密码错误，登录失败！",Toast.LENGTH_LONG).show();
                        }
                    }else if(distributor.getDistributor_status()==1){
                        Toast.makeText(LoginActivity.this,"账号正审核中，暂不能登录！",Toast.LENGTH_LONG).show();
                    }else if(distributor.getDistributor_status()==2){
                        Toast.makeText(LoginActivity.this,"账号被封，请联系管理员！",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        regist_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] types=new String[2];
                types[0]="普通用户";
                types[1]="配送员用户";
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("选择注册类型");
                builder.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//0,1
                        if(which==0){
                            Intent intent=new Intent();
                            intent.setClass(LoginActivity.this,UserRegisterActivity.class);
                            startActivity(intent);
                        }else if(which==1) {
                            Intent intent=new Intent();
                            intent.setClass(LoginActivity.this,DistribRegisterActivity.class);
                            startActivity(intent);
                        }
                    }
                });

                builder.show();

            }
        });
    }
}