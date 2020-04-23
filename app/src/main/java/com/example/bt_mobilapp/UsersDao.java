package com.example.bt_mobilapp;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileInputStream;
import java.util.ArrayList;
public class UsersDao {
    public void UserAdd(DatabaseAssistant dbAsist, String username, String email, String password,String image){
        SQLiteDatabase dbx = dbAsist.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email",email);
        values.put("password",password);
        values.put("image",image);


        dbx.insertOrThrow("users",null,values);
        dbx.close();
    }

    public ArrayList<Users> AllUsers(DatabaseAssistant dbAssist){
        ArrayList<Users> usersArrayList = new ArrayList<>();
        SQLiteDatabase dbx = dbAssist.getWritableDatabase();

        Cursor c = dbx.rawQuery("Select * from users",null);
        //Satır satır veri okuyacak.
        while (c.moveToNext()){
            Users user = new Users(c.getInt(c.getColumnIndex("user_id"))
                    ,c.getString(c.getColumnIndex("username"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getString(c.getColumnIndex("password"))
                    ,c.getString(c.getColumnIndex("image")));

            usersArrayList.add(user);
        }
        return  usersArrayList;
    }
    public  void UserDelete(DatabaseAssistant dbAssist, int user_id){
        SQLiteDatabase dbx = dbAssist.getWritableDatabase();
        dbx.delete("users","user_id=?",new String[]{String.valueOf(user_id)});
        dbx.close();
    }


    public void UserUpdate(DatabaseAssistant dbAsist,int user_id, String username, String email, String password,String image){
        SQLiteDatabase dbx = dbAsist.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email",email);
        values.put("password",password);
        values.put("image",image);


        dbx.update("users",values,"user_id=?",new String[]{String.valueOf(user_id)});
        dbx.close();
    }

    public Users UserInfo(DatabaseAssistant dbAsist, int user_id){
        Users user = new Users();
        SQLiteDatabase dbx = dbAsist.getWritableDatabase();
        Cursor c = dbx.rawQuery("Select * from users where user_id = " + user_id,null);

        while (c.moveToNext()){
            user = new Users(c.getInt(c.getColumnIndex("user_id"))
                    ,c.getString(c.getColumnIndex("username"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getString(c.getColumnIndex("password"))
                    ,c.getString(c.getColumnIndex("image")));
        }
        return  user;
    }
    public Users LastUserInfo(DatabaseAssistant dbAsist){
        Users user = new Users();
        SQLiteDatabase dbx = dbAsist.getWritableDatabase();
        Cursor c = dbx.rawQuery("Select * from users order by user_id desc ",null);
        if( c != null){
            c.moveToFirst();
        }
        user = new Users(c.getInt(c.getColumnIndex("user_id"))
                    ,c.getString(c.getColumnIndex("username"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getString(c.getColumnIndex("password"))
                    ,c.getString(c.getColumnIndex("image")));
        return  user;
    }
}
