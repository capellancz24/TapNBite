package com.example.tapnbite_original.SQLiteHelper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TapNBite.db";
    private static final int DB_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String customers = ("CREATE TABLE tblCustomers(customer_id INTEGER PRIMARY KEY AUTOINCREMENT, fullName TEXT(25) NOT NULL, nuid TEXT(11) NOT NULL UNIQUE, nuemail TEXT(25) NOT NULL UNIQUE, password TEXT(10)  NOT NULL)");
        db.execSQL(customers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblCustomers");
        onCreate(db);
    }

    public Boolean addCustomerData (String fullname, String id, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("fullName", fullname);
        cv.put("nuid", id);
        cv.put("nuemail", email);
        cv.put("password", pass);

        long result = db.insert("tblCustomers", null, cv);

        if (result == -1){
            Toast.makeText(context, "Failed Data Inserted", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Success Data Inserted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    //checks email if it exist in table or not
    public Boolean checkEmail (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblCustomers where nuemail = ?", new String[] {email});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkPass (String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblCustomers where password = ?", new String[] {pass});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblCustomers where nuemail = ? and password = ?", new String[] {email, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}

