package com.example.daandharma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String qry1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }

    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null,cv);
        db.close();
    }

    public int login(String username, String email, String password){
        int result=0;
        String str[]= new String[3];
        str[0] = username;
        str[1]= email;
        str[2]= password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and email=? and password=?", str);
        if(c.moveToFirst()){
            result=1;
        }else{
            result=0;
        }
        return result;
    }

    public ArrayList<UserModal> getLoggedinUserDetails(String username, String useremail){
        ArrayList<UserModal> al = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from users where username='"+username+"' and email='"+useremail+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            String email = cursor.getString(1);

            UserModal userModal = new UserModal();
            userModal.setuserName(name);
            userModal.setEmail(email);

            al.add(userModal);
        }

        return al;
    }
}
