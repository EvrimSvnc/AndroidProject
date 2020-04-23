package com.example.bt_mobilapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAssistant extends SQLiteOpenHelper {
    public DatabaseAssistant(Context context) {
        super(context, "UserList",null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"Users\" (\n" +
                "\t\"user_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"username\"\tTEXT,\n" +
                "\t\"email\"\tTEXT,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"image\"\tTEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
