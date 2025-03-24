package com.example.session4databasecrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    private static final String DATABASE_NAME = "userDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ROLLNO = "rollno";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_UTYPE = "uType";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "MCA202426(MAD)", null, 1);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ROLLNO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " VARCHAR(50), "
                + COLUMN_NAME + " VARCHAR(100), "
                + COLUMN_PASSWORD + " VARCHAR(100), "
                + COLUMN_UTYPE + " VARCHAR(50));";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String rollno, String email, String name, String password, String uType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_UTYPE, uType);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public String getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder records = new StringBuilder();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String rollno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLLNO));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String uType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UTYPE));

                records.append("Roll No: ").append(rollno).append("\n");
                records.append("Email: ").append(email).append("\n");
                records.append("Name: ").append(name).append("\n");
                records.append("User Type: ").append(uType).append("\n\n");

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return records.toString();
    }

    public String searchRecord(String rollno) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder record = new StringBuilder();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rollno = ?", new String[]{rollno});

        if (cursor.moveToFirst()) {
            String rollNumber = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLLNO));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String uType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UTYPE));

            record.append("Roll No: ").append(rollNumber).append("\n");
            record.append("Email: ").append(email).append("\n");
            record.append("Name: ").append(name).append("\n");
            record.append("User Type: ").append(uType).append("\n\n");
        } else {
            record.append("No record found.");
        }

        cursor.close();
        db.close();
        return record.toString();
    }

    public boolean updateRecord(String rollno, String email, String name, String password, String uType) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rollno = ?", new String[]{rollno});

        if (!cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        }

        String currentEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
        String currentName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
        String currentPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
        String currentUType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UTYPE));

        cursor.close();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email.isEmpty() ? currentEmail : email);
        values.put(COLUMN_NAME, name.isEmpty() ? currentName : name);
        values.put(COLUMN_PASSWORD, password.isEmpty() ? currentPassword : password);
        values.put(COLUMN_UTYPE, uType.isEmpty() ? currentUType : uType);

        int rowsAffected = db.update(TABLE_NAME, values, "rollno = ?", new String[]{rollno});
        db.close();

        return rowsAffected > 0;
    }

    public boolean deleteUser(String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rollno = ?", new String[]{rollno});

        if (!cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        }

        cursor.close();

        int rowsAffected = db.delete(TABLE_NAME, "rollno = ?", new String[]{rollno});
        db.close();

        return rowsAffected > 0;
    }
}