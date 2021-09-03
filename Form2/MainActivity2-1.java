package com.example.form;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    String MemberName;
    String MemberGender;
    String MemberPhone;
    String MemberAddress;
    String OldMemberName;

    String FILE_NAME;

    MembershipDBHelper dbHelper = new MembershipDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            OldMemberName = extras.getString("MemberName");
            MemberName = extras.getString("MemberName");
            MemberGender = extras.getString("MemberGender");
            MemberPhone = extras.getString("MemberPhone");
            MemberAddress = extras.getString("MemberAddress");

            TextView name = (TextView) findViewById(R.id.nameShow);
            TextView phone = (TextView) findViewById(R.id.phoneShow);
            TextView address = (TextView) findViewById(R.id.addressShow);
            TextView gender = (TextView) findViewById(R.id.genderShow);

            name.setText(MemberName);
            phone.setText(MemberPhone);
            address.setText(MemberAddress);
            gender.setText(MemberGender);

            FILE_NAME = MemberName.replaceAll(" ", "") + ".txt";
        }

    }


    public void submitbuttonHandler(View view) {
        //     String fileContents = MemberName + " | " + MemberGender + " | " + MemberPhone + " | " + MemberAddress;
   //     String filename = MemberName.replaceAll(" ", "");
//        String fileContents = MemberName + " | " + MemberGender + " | " + MemberPhone + " | " + MemberAddress;
    //    try (FileOutputStream fos = openFileOutput(filename + ".txt",
      //          Context.MODE_PRIVATE)) {
     //       fos.write(fileContents.getBytes());
      //  } catch (FileNotFoundException e) {
      //      e.printStackTrace();
     //   } catch (IOException e) {
        //         e.printStackTrace();
 //       }
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(Membership.MemberEntry.COLUMN_NAME,MemberName);
            values.put(Membership.MemberEntry.COLUMN_GENDER,MemberGender);
            values.put(Membership.MemberEntry.COLUMN_PHONE,MemberPhone);
            values.put(Membership.MemberEntry.COLUMN_ADDRESS,MemberAddress);
            //*
            String[] memberDataList = {
                    BaseColumns._ID,
                    Membership.MemberEntry.COLUMN_NAME,
                    Membership.MemberEntry.COLUMN_GENDER,
                    Membership.MemberEntry.COLUMN_PHONE,
                    Membership.MemberEntry.COLUMN_ADDRESS
            };

            // Which row to update, based on the title
            String selection = Membership.MemberEntry.COLUMN_NAME + " LIKE ?";
            String[] selectionArgs = {OldMemberName};
//            Cursor cursor = db.query( // select *(memberDataList) from TABLE_NAME  where selection like selectionArgs
//                    Membership.MemberEntry.TABLE_NAME, memberDataList, selection, selectionArgs, null, null, null
//            );

            if(OldMemberName == null ){
                long newRowId = db.insert(Membership.MemberEntry.TABLE_NAME, null, values);
            }else{
                Cursor cursor = db.query(
                        Membership.MemberEntry.TABLE_NAME, memberDataList, selection, selectionArgs, null, null, null
                );

                if(cursor.getCount() == 0) {
                    // Insert the new row, returning the primary key value of the new row
                    long newRowId = db.insert(Membership.MemberEntry.TABLE_NAME, null, values);
                }else{
                    // Update to existing row
                    int count = db.update(Membership.MemberEntry.TABLE_NAME, values, selection, selectionArgs);
                }
            }

            db.close();

        }catch(Exception e){
        e.printStackTrace();
        }

        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(i);
    }

    public void deletebuttonHandler(View view) {

        String selection = Membership.MemberEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {MemberName};

        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            // delete = (MemberDataList) from TABLE_NAME where selection like selectionArgs
            int deletedRows = db.delete(Membership.MemberEntry.TABLE_NAME, selection, selectionArgs);
            db.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(i);

    }

    public void editbuttonHandler(View view) {
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        i.putExtra("MemberName", MemberName);
        i.putExtra("MemberGender", MemberGender);
        i.putExtra("MemberPhone", MemberPhone);
        i.putExtra("MemberAddress", MemberAddress);
        startActivity(i);
    }

}
