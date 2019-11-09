package com.example.baitaplon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class DBhelper extends SQLiteOpenHelper {

    private Context mContext;
    private SQLiteDatabase db;

    public DBhelper(Context context) {
        super(context,"QLChitieuDB", null, 1);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table QL(ma integer primary key autoincrement,ten text,ngay date,sotien string,noidung text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QL");
        onCreate(db);
    }

    public boolean insertChitieu(ChiTieu chiTieu){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ten",chiTieu.getTen());
        contentValues.put("ngay",chiTieu.getNgay().toString());
        contentValues.put("sotien",chiTieu.getSotien());
        contentValues.put("noidung",chiTieu.getNoidung());
        int result= (int)db.insert("QL",null,contentValues);
        db.close();
        return true;
    }
    public ChiTieu getChiTieu(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from chiTieus where id= "+ id,null);
        if(cursor!=null)
            cursor.moveToFirst();
        ChiTieu chiTieu= new ChiTieu(cursor.getInt(0), cursor.getString(1), new Date(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
        cursor.close();
        db.close();
        return chiTieu;
    }
    public ArrayList<ChiTieu> getAllChiTieu(){
        ArrayList<ChiTieu> list=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query= "select * from QL";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast()==false){
            int ma;
            String ten, sotien;
            Date ngay;
            String noidung;
            list.add(new ChiTieu(cursor.getInt(0),cursor.getString(1),new Date(cursor.getString(2)),cursor.getString(3),cursor.getString(4)));
            cursor.moveToNext();
        }
            cursor.close();
            db.close();
            return list;
    }
    public boolean updateChiTieu(ChiTieu chiTieu){
        SQLiteDatabase db=this.getReadableDatabase();
        if(chiTieu!=null){
            ContentValues contentValues=new ContentValues();
            contentValues.put("ma",chiTieu.getMa());
            contentValues.put("ten",chiTieu.getTen());
            contentValues.put("ngay",chiTieu.getNgay().toString());
            contentValues.put("sotien",chiTieu.getSotien());
            contentValues.put("noidung",chiTieu.getNoidung());
            int chitieu=db.update("QL",contentValues,"ma=?",new String[]{String.valueOf(chiTieu.getMa())});
            if(chitieu>0)
                return true;
        }
        return  false;
    }
    public boolean deleteChiTieu(int ma){
            SQLiteDatabase db=this.getReadableDatabase();
            int count=db.delete("QL","ma=?",new String[]{String.valueOf(ma)});
            if(count>0)
            return true;
        return false;
        }
    }
