package com.newshandler.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.newshandler.model.entities.UITalker;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABSE_NAME = "sites.db";
    private static final String TABLE_URLS = "Urls";

    private static final String COLOUMN_ID="_id";
    private static final String SOURCE_NAME = "source_name";
    private static final String SOURCE_URL = "source_url";

    public MyDBHandler(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_URLS + "(" +
                COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SOURCE_URL + " TEXT, " +
                SOURCE_NAME + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_URLS);
        onCreate(db);
    }

    public long addSite(String siteName, String siteUrl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SOURCE_NAME, siteName);
        values.put(SOURCE_URL, siteUrl);

        long id = db.insert(TABLE_URLS, null, values);

        db.close();

        return id;
    }

    public void deleteNews(int id){
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_URLS + " WHERE " + COLOUMN_ID + "=\"" + id + "\";");
    }

    public boolean CheckIfExist(String url) {

        String selectQuery = "SELECT * FROM " + TABLE_URLS+" WHERE SOURCE_URL='"+url+"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount()>0) {
            cursor.close();
            db.close();
            return true;
        }

        cursor.close();
        db.close();
        return false;
    }

    public List<UITalker> getAllSites() {

        List<UITalker> siteList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_URLS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                UITalker uiTalker = new UITalker();

                uiTalker.setId(cursor.getInt(0));
                uiTalker.setSrcName(cursor.getString(2));
                uiTalker.setSrcURL(cursor.getString(1));

                siteList.add(uiTalker);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return siteList;
    }
}