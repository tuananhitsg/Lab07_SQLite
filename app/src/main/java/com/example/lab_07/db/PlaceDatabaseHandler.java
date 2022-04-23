package com.example.lab_07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlaceDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "placeManager";
    private static final String TABLE_NAME = "places";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public PlaceDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.getName()); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Place getPlace(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Place place = new Place(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return place;
    }

    // code to get all contacts in a list view
    public List<Place> getAllPlace() {
        List<Place> placetList = new ArrayList<Place>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Place place = new Place();
                place.setId(Integer.parseInt(cursor.getString(0)));
                place.setName(cursor.getString(1));

                // Adding contact to list
                placetList.add(place);
            } while (cursor.moveToNext());
        }

        // return contact list
        return placetList;
    }

    // code to update the single contact
    public int updatePlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.getName());

        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(place.getId()) });
    }

    // Deleting single contact
    public void deletePlace(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


}
