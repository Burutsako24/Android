package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void onClickJapan(View view) {
        TextClock clock = findViewById(R.id.my_clock);
        clock.setTimeZone("Asia/Tokyo");
    }

    public void onClickThai(View view) {
        TextClock clock = findViewById(R.id.my_clock);
        clock.setTimeZone("Asia/Bangkok");
    }

    public void onClickTunisia(View view) {
        TextClock clock = findViewById(R.id.my_clock);
        clock.setTimeZone("Africa/Tunis");
    }
    public void onClickMexico(View view) {
        TextClock clock = findViewById(R.id.my_clock);
        clock.setTimeZone("America/Mexico_City");
    }
}
