//package com.example.svu_reports.database;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.example.svu_reports.database.model.MaterialModel;
//import com.example.svu_reports.database.model.TypeModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelperMaterial extends SQLiteOpenHelper {
//
//   //Database version
//   private static final int DATABASE_VERSION = 1;
//   //Database Name
//   private static final String DATABASE_NAME = "db_majd_152673";
//   //Database Table namedb_majd_152673
//   private static final String TABLE_NAME = "material";
//   //Table columns
//   public static final String ID = "id";
//   public static final String NAME = "name";
//
//   public static final String TYPE_ID = "TYPE_ID";
//
//   public static final String DESCRIPTION = "description";
//
//   private SQLiteDatabase sqLiteDatabase;
//
//
//   private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
//           " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL,"+DESCRIPTION+" TEXT NOT NULL ," +"TYPE_ID "+ "INTEGER NOT NULL REFERENCES MATERIAL_TYPE (TYPE_ID));";
//
//   //Constructor
//   public DatabaseHelperMaterial (Context context){
//      super(context,DATABASE_NAME,null,DATABASE_VERSION);
//   }
//
//   @Override
//   public void onCreate(SQLiteDatabase db) {
//      db.execSQL(CREATE_TABLE);
//   }
//
//   @Override
//   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//      db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
//      onCreate(db);
//   }
//
//   //Add Employee Data
//   public void addMaterial(MaterialModel employeeModelClass){
//      ContentValues contentValues = new ContentValues();
//      contentValues.put(DatabaseHelperMaterial.NAME, employeeModelClass.getName());
//      contentValues.put(DatabaseHelperMaterial.DESCRIPTION, employeeModelClass.getDescription());
//      contentValues.put(DatabaseHelperMaterial.TYPE_ID, employeeModelClass.getType_id());
//      sqLiteDatabase = this.getWritableDatabase();
//      sqLiteDatabase.insert(DatabaseHelperMaterial.TABLE_NAME, null,contentValues);
//   }
//
//   public List<MaterialModel> getMaterialList(){
//      String sql = "select * from " + TABLE_NAME;
//      sqLiteDatabase = this.getReadableDatabase();
//      List<MaterialModel> materialList = new ArrayList<>();
//      Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
//      if (cursor.moveToFirst()){
//         do {
//            int id = Integer.parseInt(cursor.getString(0));
//            String name = cursor.getString(1);
//            int type_id = cursor.getInt(3);
//            String des = cursor.getString(2);
//            materialList.add(new MaterialModel(id,name,type_id,des));
//         }while (cursor.moveToNext());
//      }
//      cursor.close();
//      return materialList;
//   }
//
//   public void updateEmployee(TypeModel employeeModelClass){
//      ContentValues contentValues = new ContentValues();
//      contentValues.put(DatabaseHelperType.NAME,employeeModelClass.getName());
//      contentValues.put(DatabaseHelperType.DESCRIPTION,employeeModelClass.getDescription());
//      sqLiteDatabase = this.getWritableDatabase();
//      sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
//              {String.valueOf(employeeModelClass.getId())});
//   }
//
//   public void deleteEmployee(int id){
//      sqLiteDatabase = this.getWritableDatabase();
//      sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
//              {String.valueOf(id)});
//   }
//
//}