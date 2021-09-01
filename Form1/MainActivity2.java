package com.example.form;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    String FILE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
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
        String fileContents = MemberName + " | " + MemberGender + " | " + MemberPhone + " | " + MemberAddress;
        String filename = MemberName.replaceAll(" ", "");
//        String fileContents = MemberName + " | " + MemberGender + " | " + MemberPhone + " | " + MemberAddress;

        try (FileOutputStream fos = openFileOutput(filename + ".txt",
                Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(i);
    }

    public void deletebuttonHandler(View view) {
        File dir = getFilesDir();
        File file = new File(dir, FILE_NAME);
        file.delete();
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
