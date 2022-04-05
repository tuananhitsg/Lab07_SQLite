package com.example.lab_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME  = "Lab07_SQLite";
    private static final String TABLE_CONTACTS = "person";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createPersonTable = "Create table "+ TABLE_CONTACTS +"("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT "+")";
        sqLiteDatabase.execSQL(createPersonTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, person.getId());
        values.put(KEY_NAME, person.getName()); // Person Name


        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(person.getId()) });
        db.close();
    }
    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));

                // Adding contact to list
                personList.add(person);
            } while (cursor.moveToNext());
        }

        // return contact list
        return personList;
    }
}
