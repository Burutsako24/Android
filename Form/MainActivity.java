package com.example.form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitbuttonHandler(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String fullName = nameEditText.getText().toString();
        System.out.println(fullName);

        EditText phoneEditText = (EditText) findViewById(R.id.phone);
        String phone = nameEditText.getText().toString();
        System.out.println(phone);

        EditText addressEditText = (EditText) findViewById(R.id.address);
        String address = nameEditText.getText().toString();
        System.out.println(address);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String gender = radioButton.getText().toString();
        System.out.println(gender);

        CheckBox conditionsCheckBox = (CheckBox) findViewById(R.id.conditions);
        boolean checkBoxState = conditionsCheckBox.isChecked();
        System.out.println(checkBoxState);

        String title ="";
        String msg ="";
        if(checkBoxState == true){
            title ="Comfirm Information";
            msg = fullName +"(" + gender +"), Your phone no. is " + phone + "and your address is " + address;
        }else{
            title = "Caution";
            msg = "Please accept our terms and conditions.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(msg).setTitle(title);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("MemberName",fullName);
                i.putExtra("MemberGender",gender);
                i.putExtra("MemberPhone",phone);
                i.putExtra("MemberAddress",address);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
    }
}
