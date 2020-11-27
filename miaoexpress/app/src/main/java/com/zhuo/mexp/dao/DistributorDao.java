package com.zhuo.mexp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhuo.mexp.db.DBOpenHelper;
import com.zhuo.mexp.model.Distributor;

import java.util.ArrayList;
import java.util.List;

public class DistributorDao {
    DBOpenHelper dbOpenHelper;
    SQLiteDatabase db;
    public DistributorDao(Context context){
        dbOpenHelper=new DBOpenHelper(context);
    }
    public Distributor findDistributorById(String id){
        db=dbOpenHelper.getReadableDatabase();
        String sql="select * from tb_distributor where distributor_id='"+id+"'";
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToNext()){
            Distributor distributor=new Distributor();
            distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
            distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));
            distributor.setDistributor_idcar(cursor.getString(cursor.getColumnIndex("distributor_idcar")));
            distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
            distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));
            distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
            distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
            distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
            cursor.close();
            db.close();
            return distributor;
        }
        cursor.close();
        db.close();
        return  null;
    }

    public long addDistributor(Distributor distributor) {
        db=dbOpenHelper.getWritableDatabase();
        long insertflag=0;
        ContentValues contentValues=new ContentValues();
        contentValues.put("distributor_id",distributor.getDistributor_id());
        contentValues.put("distributor_password",distributor.getDistributor_password());
        contentValues.put("distributor_name",distributor.getDistributor_name());
        contentValues.put("distributor_idcar",distributor.getDistributor_idcar());
        contentValues.put("distributor_tel",distributor.getDistributor_tel());
        contentValues.put("distributor_money",100);
        contentValues.put("distributor_singularnum",0);
        contentValues.put("distributor_status",0);
        contentValues.put("distributor_picPath",distributor.getDistributor_picPath());
        insertflag= db.insert("tb_distributor",null,contentValues);//返回值(long):表示插入了多少行数据

        db.close();
        return insertflag;
    }

    public List<Distributor> distributorDesc() {
        List<Distributor> resultList=new ArrayList<>();
        db=dbOpenHelper.getReadableDatabase();
        String sql="select * from tb_distributor order by distributor_singularnum desc";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Distributor distributor=new Distributor();
            distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
            distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));
            distributor.setDistributor_idcar(cursor.getString(cursor.getColumnIndex("distributor_idcar")));
            distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
            distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));
            distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
            distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
            distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
            resultList.add(distributor);
        }
        cursor.close();
        db.close();
        return  resultList;
    }

    public boolean login(String distribname,String password){
        db=dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_distributor where distributor_id='"+distribname+"' and distributor_password='"+password+"'",null);
        if(cursor.moveToNext()){
            return true;
        }else{
            return false;
        }
    }

    public int  updateJiedanNum(String distributorId) {
        db=dbOpenHelper.getReadableDatabase();
        Distributor distributor=findDistributorById(distributorId);//db.close();
        int jiedanshu=distributor.getDistributor_singularnum()+1;
        ContentValues contentValues=new ContentValues();
        contentValues.put("distributor_singularnum",jiedanshu);
        db=dbOpenHelper.getWritableDatabase();
        return db.update("tb_distributor",contentValues,"distributor_id=?",new String[]{distributorId+""});
    }
}
