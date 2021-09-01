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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String MemberName = extras.getString("MemberName");
            String MemberGender = extras.getString("MemberGender");
            String MemberPhone = extras.getString("MemberPhone");
            String MemberAddress = extras.getString("MemberAddress");

            EditText name = (EditText) findViewById(R.id.name);
            EditText phone = (EditText) findViewById(R.id.phone);
            EditText address = (EditText) findViewById(R.id.address);

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender);
            radioGroup.clearCheck();
            RadioButton maleButton = (RadioButton) findViewById(R.id.male);
            RadioButton femaleButton = (RadioButton) findViewById(R.id.female);
            RadioButton otherButton = (RadioButton) findViewById(R.id.other);

            switch (MemberGender) {
                case "Male":
                    maleButton.setChecked(true);
                    break;
                case "Female":
                    femaleButton.setChecked(true);
                    break;
                default:
                    otherButton.setChecked(true);
                    break;
            }

            name.setText(MemberName);
            phone.setText(MemberPhone);
            address.setText(MemberAddress);
        }
    }

    public void submitbuttonHandler(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String name = nameEditText.getText().toString();
//        System.out.println(name);

        EditText phoneEditText = (EditText) findViewById(R.id.phone);
        String phone = phoneEditText.getText().toString();
//        System.out.println(phone);

        EditText addressEditText = (EditText) findViewById(R.id.address);
        String address = addressEditText.getText().toString();
//        System.out.println(address);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String gender = radioButton.getText().toString();
//        System.out.println(gender);

        CheckBox conditionsCheckBox = (CheckBox) findViewById(R.id.conditions);
        Boolean checkBoxState = conditionsCheckBox.isChecked();
//        System.out.println(checkBoxState);


        String title = "";
        String msg = "";

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        if (checkBoxState == true) {
            title = "Comfirm Information";
            msg = name + " (" + gender + "). Your phone no. is " + phone + " and your address is " + address;


            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                //        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("MemberName", name);
                    i.putExtra("MemberGender", gender);
                    i.putExtra("MemberPhone", phone);
                    i.putExtra("MemberAddress", address);
                    startActivity(i);
                }
            });


        } else {
            title = "Caution";
            msg = "Please accept our terms and conditions.";
            builder.setNeutralButton("Back", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();

                }
            });

        }
        builder.setMessage(msg).setTitle(title);
        builder.create();
        builder.show();

    }
}
