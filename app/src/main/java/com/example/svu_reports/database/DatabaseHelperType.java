//package com.example.svu_reports.database;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.svu_reports.database.model.TypeModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelperType extends SQLiteOpenHelper {
//
//   //Database version
//   private static final int DATABASE_VERSION = 1;
//   //Database Name
//   private static final String DATABASE_NAME = "db_majd_152673";
//   //Database Table name
//
//
//   private SQLiteDatabase sqLiteDatabase;
//
//
//
//
//   //Constructor
//   public DatabaseHelperType (Context context){
//      super(context,DATABASE_NAME,null,DATABASE_VERSION);
//   }
//
//   @Override
//   public void onCreate(SQLiteDatabase db) {
//
//      db.execSQL(CREATE_TYPE_TABLE);
//   }
//
//   @Override
//   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//      db.execSQL(" DROP TABLE IF EXISTS " + TYPE_TABLE_NAME);
//      onCreate(db);
//   }
//
//
//
//
//}