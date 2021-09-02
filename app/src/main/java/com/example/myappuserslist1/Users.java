package com.example.myappuserslist1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myappuserslist1.database.UserBaseHelper;
import com.example.myappuserslist1.database.UserDbSchema;

import java.util.ArrayList;
import java.util.UUID;

public class Users {
    private ArrayList<User> userList;
    private SQLiteDatabase database;
    private Context context;

    public Users(Context context){
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(this.context).getWritableDatabase();
    }

    public void addUser(User user){
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    public void updateUser(User user, UUID uuid){
        ContentValues values = getContentValues(user);
        String str = uuid.toString().substring(24);
        database.update(UserDbSchema.UserTable.NAME, values, UserDbSchema.Cols.UUID+"="+uuid.toString(), null);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.Cols.USERNAME, user.getUserName().toString());
        values.put(UserDbSchema.Cols.USERLASTNAME, user.getUserLastName().toString());
        values.put(UserDbSchema.Cols.PHONE, user.getPhone().toString());
        return values;
    }

    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME, null, null, null, null, null, null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList(){
        this.userList = new ArrayList<>();
        //Создаём список пользователей
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return this.userList;
    }

}
