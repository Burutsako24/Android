package com.example.form;

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

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            String MemberName = extras.getString("MemberName");
            String MemberGender = extras.getString("MemberGender");
            String MemberPhone = extras.getString("MemberPhone");
            String MemberAddress = extras.getString("MemberAddress");

            TextView name = (TextView) findViewById(R.id.nameShow);
            TextView phone = (TextView) findViewById(R.id.phoneShow);
            TextView address = (TextView) findViewById(R.id.addressShow);
            TextView gender = (TextView) findViewById(R.id.genderShow);

            name.setText(MemberName);
            phone.setText(MemberPhone);
            address.setText(MemberAddress);
            gender.setText(MemberGender);
    }

        }
    public void submitbuttonHandler(View view){
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
    }
}
