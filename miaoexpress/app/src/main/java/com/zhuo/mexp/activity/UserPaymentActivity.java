package com.zhuo.mexp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuo.mexp.dao.OrderDao;
import com.zhuo.mexp.dao.UserDao;
import com.zhuo.mexp.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPaymentActivity extends AppCompatActivity {
    TextView price_tv;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment);
        price_tv=findViewById(R.id.price);
        final String money=getIntent().getStringExtra("money");//5 3
        price_tv.setText("￥"+money);
        radioButton=findViewById(R.id.balance);
        radioButton.setChecked(true);
        findViewById(R.id.zhifu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()) {
                    SharedPreferences sp = getSharedPreferences("userdata", MODE_MULTI_PROCESS);//与登录时保存用户名的文件一致
                    String user_id = sp.getString("userId", "");
                    UserDao user_dao = new UserDao(UserPaymentActivity.this);
                    Double user_money = user_dao.findUserById(user_id).getUser_money();//用户表内现有的钱 2
                    user_money -= Double.parseDouble(money);//user_money=user_money- Double.parseDouble(money)
                    if (user_money < 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserPaymentActivity.this);
                        builder.setTitle("提示");
                        builder.setMessage("余额不足请充值");
                        builder.setPositiveButton("确定", null);
                        builder.show();
                    } else {
                        user_dao.updateMoney(user_id, user_money);
                        Order order = new Order();
                        order.setOrder_delivery_time(null);
                        order.setDistributor_id(null);
                        order.setUser_id(user_id);
                        order.setOrder_distribut_type(getIntent().getStringExtra("order_distribut_type"));
                        order.setOrder_receiver_name(getIntent().getStringExtra("name"));
                        order.setOrder_notes(getIntent().getStringExtra("beizhu"));
                        order.setOrder_status(0);
                        order.setOrder_picpath(getIntent().getStringExtra("picuri"));
                        order.setOrder_receiver_address(getIntent().getStringExtra("address"));
                        order.setOrder_price(Double.parseDouble(money));
                        order.setOrder_receiver_tel(Long.parseLong(getIntent().getStringExtra("phone")));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
                        Date date = new Date(System.currentTimeMillis());
                        order.setOrder_time(simpleDateFormat.format(date));
                        OrderDao order_dao = new OrderDao(UserPaymentActivity.this);
                        order_dao.newOrder(order);
                        Log.i("tag",  money+" "+getIntent().getStringExtra("order_distribut_type")+
                                ""+getIntent().getStringExtra("name")+getIntent().getStringExtra("beizhu")+getIntent().getStringExtra("address")+
                                ""+simpleDateFormat.format(date));
                        Toast.makeText(UserPaymentActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                        Log.i("datas",order.getOrder_picpath());
                        startActivity(new Intent(UserPaymentActivity.this, MainActivity.class));
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserPaymentActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("支付失败");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                }

            }
        });
    }
}