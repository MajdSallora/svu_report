package com.example.svu_reports.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.svu_reports.database.model.AgentModel;
import com.example.svu_reports.database.model.InvoiceModel;
import com.example.svu_reports.database.model.InvoiceDetailsModel;
import com.example.svu_reports.database.model.MaterialModel;
import com.example.svu_reports.database.model.TypeModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_majd_152673";


    private static final String Invoice_Details_TABLE_NAME = "Invoice_Details";
    public static final String Invoice_Details_Invoice_ID = "invoice_id";
    public static final String Invoice_Details_ID = "id";
    public static final String Invoice_Details_Material_ID = "material_id";
    public static final String Invoice_Details_number = "number";
    public static final String Invoice_Details_price = "price";
    public static final String Invoice_Details_total = "total";


    private static final String Invoice_TABLE_NAME = "Invoice";
    public static final String Invoice_ID = "id";
    public static final String Invoice_AGENT_ID = "agent_id";
    public static final String Invoice_DATE = "date";
    public static final String Invoice_IS_BUY = "is_buy";
    public static final String Invoice_DESCRIPTION = "description";

    private static final String AGENT_TABLE_NAME = "Agent";
    public static final String AGENT_ID = "id";
    public static final String AGENT_NAME = "name";
    public static final String AGENT_DESCRIPTION = "description";

    private static final String TYPE_TABLE_NAME = "Type";
    //Table columns
    public static final String TYPE_ID = "id";
    public static final String TYPE_NAME = "name";

    public static final String TYPE_DESCRIPTION = "description";

    private static final String MATERIAL_TABLE_NAME = "material";
    //Table columns
    public static final String MATERIAL_ID = "id";
    public static final String MATERIAL_NAME = "name";

    public static final String MATERIAL_TYPE_ID = "TYPE_ID";

    public static final String MATERIAL_DESCRIPTION = "description";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_AGENT_TABLE = "create table " + AGENT_TABLE_NAME + "(" + AGENT_ID +

            " INTEGER PRIMARY KEY AUTOINCREMENT," + AGENT_NAME + " TEXT NOT NULL," + AGENT_DESCRIPTION + " TEXT NOT NULL);";
    private static final String CREATE_MATERIAL_TABLE = "create table " + MATERIAL_TABLE_NAME +"("+ MATERIAL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + MATERIAL_NAME + " TEXT NOT NULL,"+ MATERIAL_DESCRIPTION +" TEXT NOT NULL ," +"TYPE_ID "+ "INTEGER NOT NULL REFERENCES MATERIAL_TYPE (TYPE_ID));";

    private static final String CREATE_TYPE_TABLE = "create table " + TYPE_TABLE_NAME +"("+TYPE_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPE_NAME + " TEXT NOT NULL,"+TYPE_DESCRIPTION+" TEXT NOT NULL);";

    private static final String CREATE_INVOICE_TABLE = "create table " + Invoice_TABLE_NAME +"("+Invoice_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + Invoice_DESCRIPTION + " TEXT NOT NULL,"+Invoice_DATE+" TEXT NOT NULL ," + Invoice_AGENT_ID+ " INTEGER NOT NULL, "+ Invoice_IS_BUY + " INTEGER NOT NULL);";
    //Constructor

    private static final String CREATE_INVOICE_DETAILS_TABLE = "create table " + Invoice_Details_TABLE_NAME +"("+Invoice_Details_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + Invoice_Details_Invoice_ID + " INTEGER NOT NULL,"+Invoice_Details_Material_ID+" INTEGER NOT NULL ," + Invoice_Details_number+ " INTEGER NOT NULL,"+ Invoice_Details_price + " INTEGER NOT NULL, " + Invoice_Details_total+" INTEGER NOT NULL);";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AGENT_TABLE);
        db.execSQL(CREATE_MATERIAL_TABLE);
        db.execSQL(CREATE_TYPE_TABLE);
        db.execSQL(CREATE_INVOICE_TABLE);
        db.execSQL(CREATE_INVOICE_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + AGENT_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TYPE_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + MATERIAL_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + Invoice_TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + Invoice_Details_TABLE_NAME);
        onCreate(db);
    }


    public void addAgent(AgentModel employeeModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.AGENT_NAME, employeeModelClass.getName());
        contentValues.put(DatabaseHelper.AGENT_DESCRIPTION, employeeModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.AGENT_TABLE_NAME, null, contentValues);
    }

    public List<AgentModel> getAgentList() {
        String sql = "select * from " + AGENT_TABLE_NAME +";";
        sqLiteDatabase = this.getReadableDatabase();
        List<AgentModel> storeEmployee = new ArrayList<AgentModel>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String des = cursor.getString(2);
                storeEmployee.add(new AgentModel(id, name, des));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    public void deleteAgent(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(AGENT_TABLE_NAME, AGENT_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }


    public void addMaterial(MaterialModel employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MATERIAL_NAME, employeeModelClass.getName());
        contentValues.put(DatabaseHelper.MATERIAL_DESCRIPTION, employeeModelClass.getDescription());
        contentValues.put(DatabaseHelper.MATERIAL_TYPE_ID, employeeModelClass.getType_id());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.MATERIAL_TABLE_NAME, null,contentValues);
    }

    public List<MaterialModel> getMaterialList(){
        String sql = "select * from " + MATERIAL_TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<MaterialModel> materialList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int type_id = cursor.getInt(3);
                String des = cursor.getString(2);
                materialList.add(new MaterialModel(id,name,type_id,des));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return materialList;
    }

    public void deleteMaterial(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(MATERIAL_TABLE_NAME, MATERIAL_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    public void addEmployee(TypeModel employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TYPE_NAME, employeeModelClass.getName());
        contentValues.put(DatabaseHelper.TYPE_DESCRIPTION, employeeModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TYPE_TABLE_NAME, null,contentValues);
    }

    public List<TypeModel> getTypeList(){
        String sql = "select * from " + TYPE_TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<TypeModel> storeEmployee = new ArrayList<TypeModel>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int idd = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String des = cursor.getString(2);
                storeEmployee.add(new TypeModel(idd,name,des));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }


    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TYPE_TABLE_NAME, TYPE_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    public void addInvoice(InvoiceModel invoice){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Invoice_DATE, dateFormat.format(date).toString());
        contentValues.put(DatabaseHelper.Invoice_IS_BUY, invoice.isBuy() ? 1 : 0);
        contentValues.put(DatabaseHelper.Invoice_AGENT_ID, invoice.getAgent_id());
        contentValues.put(DatabaseHelper.Invoice_DESCRIPTION, invoice.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.Invoice_TABLE_NAME, null,contentValues);
    }

    public List<InvoiceModel> getInvoiceList(){
        String sql = "select * from " + Invoice_TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<InvoiceModel> storeEmployee = new ArrayList<InvoiceModel>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String des = cursor.getString(1);
                String date = cursor.getString(2);
                int agent_id = cursor.getInt(3);
                boolean isBuy = cursor.getInt(4) != 0;
                storeEmployee.add(new InvoiceModel(id,agent_id,date,isBuy,des));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }


    public Integer getMostAgentBuy(){
        String sql = "select " + Invoice_AGENT_ID + ", Count ("+Invoice_AGENT_ID+") AS `value_occurrence` From "+Invoice_TABLE_NAME+" Group By "+Invoice_AGENT_ID+" Order by `value_occurrence` DESC LIMIT 1;";

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        Log.i("yyyyys" , String.valueOf(cursor.getCount()));
        Log.i("yyyyys" , cursor.getColumnNames().toString());
        if(cursor.moveToFirst()){
            return  cursor.getInt(0);
        }else{
            return  -1;
        }
    }

    public void addInvoiceDetails(InvoiceDetailsModel invoice){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Invoice_Details_Invoice_ID, invoice.getInvoice_id());
        contentValues.put(DatabaseHelper.Invoice_Details_Material_ID, invoice.getMaterial_id());
        contentValues.put(DatabaseHelper.Invoice_Details_total, invoice.getTotal());
        contentValues.put(DatabaseHelper.Invoice_Details_number, invoice.getNumber());
        contentValues.put(DatabaseHelper.Invoice_Details_price, invoice.getPrice());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.Invoice_Details_TABLE_NAME, null,contentValues);
    }

    public List<InvoiceDetailsModel> getInvoiceDetailsList(){
        String sql = "select * from " + Invoice_Details_TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<InvoiceDetailsModel> storeEmployee = new ArrayList<InvoiceDetailsModel>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                int invoice_id = cursor.getInt(1);
                int material_id = cursor.getInt(2);
                int number = cursor.getInt(3);
                int price = cursor.getInt(4);
                int total = cursor.getInt(5);
                storeEmployee.add(new InvoiceDetailsModel(id,invoice_id,material_id,number,price,total));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }
}