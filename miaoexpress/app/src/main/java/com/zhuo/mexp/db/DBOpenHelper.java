package com.zhuo.mexp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    final static String DBNAME="mexpsqlite.db";
    final static int VERSION=1;
    public DBOpenHelper(@Nullable Context context) {//创建数据库context=LoginActivity.this
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建表
        db.execSQL("create table 'tb_user'('user_id' varchar(10),'user_password' varchar(20),'user_name' varchar(20)," +
                "'user_tel' integer(11),'user_money' double(20,0),'user_address' varchar(200)," +
                "'user_status' integer,'user_imgpath' varchar(200))");
        db.execSQL("insert into 'tb_user' values('u_101','123456','张三','189008072652','100','男生三宿舍501',0,'')");
        db.execSQL("insert into 'tb_user' values('u_102','123456','李四','189008072652','100','男生三宿舍305',1,'')");
        //创建配送员表
        db.execSQL("create table `tb_distributor`("+
                "`distributor_id` varchar(10) not null,"+
                "`distributor_password` varchar(20) not null,"+
                "`distributor_name` varchar(20) not null,"+
                "`distributor_idcar` varchar(18) not null,"+
                "`distributor_tel` integer(11) not null,"+
                "`distributor_money` double(20,0),"+
                "`distributor_singularnum` int not null,"+
                "`distributor_status` int not null,"+
                "`distributor_picPath` varchar(200),"+
                "primary key(`distributor_id`));");
        db.execSQL("insert into `tb_distributor` values ('db_101','123456','杜小双','511321999800201150','17381086916','500',0,0,'')");
        //创建管理员表
        db.execSQL("create table `tb_admin`("+
                "`admin_id` varchar(20) not null,"+
                "`admin_password` varchar(20) not null,"+
                "primary key(`admin_id`));");
        db.execSQL("insert into `tb_Admin`values ('ad101','123456')");
        //创建订单表
        db.execSQL("create table`tb_order`("+
                "`order_id`INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "`user_id` varchar(10) not null,"+
                "`distributor_id` varchar(10) ,"+
                "`order_distribut_type`varchar(20),"+
                "`order_price` double(20,0) not null,"+
                "`order_receiver_name` varchar(20) not null,"+
                "`order_receiver_tel` int(11) not null,"+
                "`order_receiver_address` varchar(200) not null,"+
                "`order_time` datetime not null,"+
                "`order_delivery_time` datetime ,"+
                "`order_status` int not null,"+
                "`order_picpath` varchar(100),"+
                "`order_notes` varchar(300))"
        );
        //预存数据
        db.execSQL("insert into `tb_order` values (null,'u_101',null,'代发大件',5.0,'杜小双','17381086916','2325','2020/09/10 18:06:23',null,0,null,'取件码e86952')");
        db.execSQL("insert into `tb_order` values (null,'u_101','db_101','代发大件',5.0,'杜小双','17381086916','2325','2020/09/10 18:06:23',null,1,null,'取件码e86952')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
