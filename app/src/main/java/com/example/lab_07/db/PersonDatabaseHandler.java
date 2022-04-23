package com.example.lab_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PersonDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Lab07";
    private static final String TABLE_NAME = "person";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public PersonDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME + "("
               // + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT" + " )";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

//    public  void resetDatabase() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("Drop Table if exists " + TABLE_NAME);
//        onCreate(db);
//    }

    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Person getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME},
                KEY_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();
        Person person = new Person(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1)
        );
        return person;
    }

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setName(cursor.getString(1));
                personList.add(person);
            } while (cursor.moveToNext());
        }
        return personList;
    }



    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + " like ?",
                new String[] { person.getName() });
        db.close();
    }
}
