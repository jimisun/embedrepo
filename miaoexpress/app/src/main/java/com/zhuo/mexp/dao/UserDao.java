package com.zhuo.mexp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuo.mexp.db.DBOpenHelper;
import com.zhuo.mexp.model.UserInfo;

public class UserDao {
    DBOpenHelper dbOpenHelper;
    SQLiteDatabase db;
    public UserDao(Context context){//context=LoginActivity.this
        dbOpenHelper=new DBOpenHelper(context);
    }

    public boolean login(String username,String password){
        db=dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_user where user_id='"+username+"' and user_password='"+password+"'",null);
        if(cursor.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public UserInfo findUserById(String userid){
        db=dbOpenHelper.getReadableDatabase();
        UserInfo userInfo=new UserInfo();
        Cursor cursor=db.rawQuery("select * from tb_user where user_id='"+userid+"'",null);
        if(cursor.moveToNext()){
            userInfo.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            userInfo.setUser_address(cursor.getString(cursor.getColumnIndex("user_address")));
            userInfo.setUser_imgpath(cursor.getString(cursor.getColumnIndex("user_imgpath")));
            userInfo.setUser_money(cursor.getDouble(cursor.getColumnIndex("user_money")));
            userInfo.setUser_status(cursor.getInt(cursor.getColumnIndex("user_status")));
            return userInfo;
        }else{
            return null;
        }
    }

    public long addUser(UserInfo user) {
        db=dbOpenHelper.getWritableDatabase();
        long insertflag=0;
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",user.getUser_id());
        contentValues.put("user_password",user.getUser_password());
        contentValues.put("user_tel",user.getUser_tel());
        contentValues.put("user_name",user.getUser_name());
        contentValues.put("user_address",user.getUser_address());
        contentValues.put("user_money",100);
        contentValues.put("user_status",0);
        contentValues.put("user_imgpath","");
        insertflag= db.insert("tb_user",null,contentValues);//返回值(long):表示插入了多少行数据

        db.close();
        return insertflag;
    }

    /**
     *修改money
     *
     * */
    public void  updateMoney(String id, Double Money){
        db=dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("update tb_user set user_money='"+Money+"'where user_id='"+id+"'",null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
        }

        cursor.close();
        db.close();
    }
}
