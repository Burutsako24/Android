package com.example.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.form.MainActivity2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // This part gets data from internal storage
        File files = getFilesDir();
        String[] array = files.list();
        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        for (String filename : array) {
            filename = filename.replace(".txt", "");
            System.out.println(filename);
            adapter.add(filename);
        }

        final ListView listView = (ListView) findViewById(R.id.listid);
        listView.setAdapter(adapter);
        // ---------------


        // get content from selected one
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listView.getItemAtPosition(position).toString();
                String FILE_NAME = item + ".txt";

                //get content from file using BufferedReader
                try {
                    FileInputStream fis = openFileInput(FILE_NAME);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    String whole = "";
                    while ((line = reader.readLine()) != null) {
//                        if (whole == "") {
                        whole = whole + line;
//                        } else {
//                            whole = whole + "\n" + line;
//                        }
                    }
                    reader.close();

                    System.out.println("Whole:" + whole);

                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                    String[] content = whole.split(" \\| ");

                    for (String ct : content) {
                        System.out.println("Content:" + ct);
                    }

                    intent.putExtra("MemberName", (String) Array.get(content, 0));
                    intent.putExtra("MemberGender", (String) Array.get(content, 1));
                    intent.putExtra("MemberPhone", (String) Array.get(content, 2));
                    intent.putExtra("MemberAddress", (String) Array.get(content, 3));
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void CreateMember(View view) {
        Intent i = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(i);
    }
}
