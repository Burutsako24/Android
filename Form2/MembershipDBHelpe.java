package com.example.form;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class MembershipDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MembershipDB.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Membership.MemberEntry.TABLE_NAME + " (" +
                    Membership.MemberEntry._ID + " INTEGER PRIMARY KEY," +
                    Membership.MemberEntry.COLUMN_NAME + " TEXT," +
                    Membership.MemberEntry.COLUMN_GENDER + " TEXT," +
                    Membership.MemberEntry.COLUMN_PHONE + " TEXT," +
                    Membership.MemberEntry.COLUMN_ADDRESS + " TEXT)" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Membership.MemberEntry.TABLE_NAME;

    public MembershipDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);


    }
}
//  Defines Database Schema (table name and column names)
final class Membership {
    private Context ctx;

    // To prevent someone from accidentally instantiating the Membership class,
    // make the constructor private.
    private Membership(Context ctx) { this.ctx = ctx; }
    /* Inner class that defines the table contents */
    public static class MemberEntry implements BaseColumns {
        public static final String TABLE_NAME = "Member";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_GENDER = "Gender";
        public static final String COLUMN_PHONE = "Phone";
        public static final String COLUMN_ADDRESS = "Address";

    }
}
