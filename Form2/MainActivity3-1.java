package com.example.form;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.form.MainActivity;
import com.example.form.MainActivity2;
import com.example.form.Membership;
import com.example.form.MembershipDBHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    MembershipDBHelper dbHelper = new MembershipDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //*
        String[] memberDataList = {
                BaseColumns._ID,
                Membership.MemberEntry.COLUMN_NAME,
                Membership.MemberEntry.COLUMN_GENDER,
                Membership.MemberEntry.COLUMN_PHONE,
                Membership.MemberEntry.COLUMN_ADDRESS
        };

        // Cursor provides random read-write access to the result set returned by a database query.
        Cursor cursor = db.query(
                Membership.MemberEntry.TABLE_NAME,   // The table to query
                memberDataList,        // The array of columns to return (pass null to get all)
                null,          // The columns for the WHERE clause
                null,       // The values for the WHERE clause
                null,          // The groupBy the rows
                null,           // The filter by row groups
                null           // The sort order
        );

        ArrayList<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String memberName = cursor.getString(cursor.getColumnIndexOrThrow(Membership.MemberEntry.COLUMN_NAME));
            list.add(memberName);
        }

        cursor.close();

        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        for (String name : list) {
            adapter.add(name);
        }

        final ListView listView = (ListView) findViewById(R.id.listid);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String item = listView.getItemAtPosition(position).toString();

                //get content from file using BufferedReader
                try {
                    String selection = Membership.MemberEntry.COLUMN_NAME + " LIKE ?";
                    String[] selectionArgs = {item}; // select *(memberDataList) from TABLE_NAME  where selection like selectionArgs 1 and selectionArgs 2
                    Cursor cursor = db.query( // select *(memberDataList) from TABLE_NAME  where selection like selectionArgs
                            Membership.MemberEntry.TABLE_NAME, memberDataList, selection, selectionArgs, null, null, null
                    );

                    String[] content = new String[4];

                    while (cursor.moveToNext()) {
                        content[0] = cursor.getString(
                                cursor.getColumnIndexOrThrow(Membership.MemberEntry.COLUMN_NAME));
                        content[1] = cursor.getString(
                                cursor.getColumnIndexOrThrow(Membership.MemberEntry.COLUMN_GENDER));
                        content[2] = cursor.getString(
                                cursor.getColumnIndexOrThrow(Membership.MemberEntry.COLUMN_PHONE));
                        content[3] = cursor.getString(
                                cursor.getColumnIndexOrThrow(Membership.MemberEntry.COLUMN_ADDRESS));

                        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                        intent.putExtra("MemberName", (String) Array.get(content, 0));
                        intent.putExtra("MemberGender", (String) Array.get(content, 1));
                        intent.putExtra("MemberPhone", (String) Array.get(content, 2));
                        intent.putExtra("MemberAddress", (String) Array.get(content, 3));
                        startActivity(intent);
                    }

                    cursor.close();

                }  catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
//
//        // This part gets data from internal storage
//        File files = getFilesDir();
//        String[] array = files.list();
//        ArrayList<String> arrayList = new ArrayList<>();
//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        for (String filename : array) {
//            filename = filename.replace(".txt", "");
//            System.out.println(filename);
//            adapter.add(filename);
//        }
//
//        final ListView listView = (ListView) findViewById(R.id.listid);
//        listView.setAdapter(adapter);
//        // ---------------
//
//
//        // get content from selected one
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = listView.getItemAtPosition(position).toString();
//                String FILE_NAME = item + ".txt";
//
//                //get content from file using BufferedReader
//                try {
//                    FileInputStream fis = openFileInput(FILE_NAME);
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//                    String line;
//                    String whole = "";
//                    while ((line = reader.readLine()) != null) {
////                        if (whole == "") {
//                        whole = whole + line;
////                        } else {
////                            whole = whole + "\n" + line;
////                        }
//                    }
//                    reader.close();
//
//                    System.out.println("Whole:" + whole);
//
//                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
//                    String[] content = whole.split(" \\| ");
//
//                    for (String ct : content) {
//                        System.out.println("Content:" + ct);
//                    }
//
//                    intent.putExtra("MemberName", (String) Array.get(content, 0));
//                    intent.putExtra("MemberGender", (String) Array.get(content, 1));
//                    intent.putExtra("MemberPhone", (String) Array.get(content, 2));
//                    intent.putExtra("MemberAddress", (String) Array.get(content, 3));
//                    startActivity(intent);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//
//    }

    public void CreateMember(View view) {
        Intent i = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(i);
    }
}
