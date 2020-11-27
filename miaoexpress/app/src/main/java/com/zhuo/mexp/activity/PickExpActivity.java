package com.zhuo.mexp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PickExpActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_exp);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.qudajian).setOnClickListener(this);
        findViewById(R.id.quxiaojian).setOnClickListener(this);
        findViewById(R.id.goback).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qudajian :
                Intent intent=new Intent();
                intent.putExtra("order_distribut_type","bigexp");
                intent.setClass(PickExpActivity.this, DeployPickActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.quxiaojian :
                Intent intent1=new Intent();
                intent1.putExtra("order_distribut_type","smallexp");
                //intent1.setClass(PickExpActivity.this, DeployPickActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.goback:
                finish();
                break;
        }
    }
}