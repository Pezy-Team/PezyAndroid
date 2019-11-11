package com.example.pezyandroid.louise.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pezyandroid.louise.date.DateUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anubi on 25/10/2560.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "limcharoen_stock_count45325.db";
    public static final String TABLE_NAME = "product_tb";
    public static final String COL_1 = "id";
    public static final String COL_2 = "product_id";
    public static final String COL_3 = "product_name";
    public static final String COL_4 = "qty";
    public static final String COL_5 = "uom_id";
    public static final String COL_6 = "uom_name";
    public static final String COL_7 = "created_date";
    public static final String COL_8 = "updated_date";
    public static final String COL_9 = "barcode";

    private static Context fContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        fContext = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.wtf("MYAPP", "SQLite onCreate");
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append(" ( ")
                .append(COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(COL_2 + " INTEGER, ")
                .append(COL_3 + " TEXT, ")
                .append(COL_4 + " INTEGER, ")
                .append(COL_5 + " INTEGER, ")
                .append(COL_6 + " TEXT, ")
                .append(COL_7 + " DATETIME, ")
                .append(COL_8 + " DATETIME, ")
                .append(COL_9 + " TEXT ")
                .append(" ) ");

        db.execSQL(sb.toString());
        sb.setLength(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.wtf("MYAPP", "SQLite onUpgrade");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    /**
     * Insert product line
     * @param val
     * @return boolean true = success, false = have an error occured.
     */
    public boolean insert(HashMap<String, String> val){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COL_2, val.get(COL_2));
            cv.put(COL_3, val.get(COL_3));
            cv.put(COL_4, val.get(COL_4));
            cv.put(COL_5, val.get(COL_5));
            cv.put(COL_6, val.get(COL_6));
            cv.put(COL_7, " " + DateUtils.getCurrentDateTime("yyyy-mm-dd HH:mm:ss") + " ");
            cv.put(COL_8, " " + DateUtils.getCurrentDateTime("yyyy-mm-dd HH:mm:ss") + " ");
            cv.put(COL_9, val.get(COL_9));
            long result = db.insert(TABLE_NAME, null, cv);
            if(result == -1){
                throw new Exception("Insert error occured!");
            }else{
                return true;
            }
        }catch (Exception e){
            Log.wtf("MYAPP DATABASE", e.getMessage());
            return false;
        }
    }

    /**
     * Chunking all record
     * @return Cursor
     */
    public Cursor chunk(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cs = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ",null);
            if(cs != null){
                return cs;
            }else{
                throw new Exception("Chunking have error occured!");
            }
        }catch (Exception e){
            Log.wtf("MYAPP DATABASE", e.getMessage());
            return null;
        }
    }

    public Cursor chunkGroupProductID(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * ").append(" FROM ").append(TABLE_NAME)
                    //.append(" GROUP BY ").append(COL_2)
                    .append(" ORDER BY ").append(COL_1).append(" DESC");
            Log.wtf("MYAPP", "chunkGroupProductID : " + sb.toString());

            Cursor cs = db.rawQuery(sb.toString(), null);
            if(cs != null){
                return cs;
            }else{
                throw new Exception("Chunking have error occured!");
            }
        }catch (Exception e){
            Log.wtf("MYAPP DATABASE", e.getMessage());
            return null;
        }
    }

    /**
     * Delete records by where clause.
     * @param where Where clause statements
     * @return int The amount of row that get affected.
     */
    public int delete(String where){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, where, null);
    }

    /**
     * Delete all row.
     * @return int, Amount of row that get affected.
     */
    public int deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

    /**
     * Find any records.
     * @param where Where clause statements.
     * @return Cursor data list.
     */
    public Cursor find(String where){
        SQLiteDatabase db = this.getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT").append(" * ")
                .append("FROM ").append(TABLE_NAME);
        if(!StringUtils.isBlank(where)){
            sb.append(" WHERE (").append(where).append(" ) ");
        }
        sb.append(" ORDER BY ").append(COL_1).append(" ASC ");
        return db.rawQuery(sb.toString(), null);
    }


    /**
     * Update item quantity.
     * @param qty | Quantity.
     * @param id | Row id
     * @return Returning amount of row that get affected.
     */
    public int updateQty(String qty, ArrayList<String> id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_4, qty);

        StringBuilder sb = new StringBuilder();
        sb.append(COL_1 + " IN ( ");
        sb.append(StringUtils.join(id, ','));
        sb.append(" ) ");
        int res = db.update(TABLE_NAME, cv, sb.toString(),null);
        sb.setLength(0);
        return res;
    }
}
