package com.example.test6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickJapan(View view) {
        TextClock clock = findViewById(R.id.my_clock);
        clock.setTimeZone("Asia/Tokyo");
        ImageView im = findViewById(R.id.cityImage);
        im.setImageResource(R.drawable.tokyo);
        TextView tv = findViewById(R.id.cityContent);
        tv.setText("The city has hosted multiple international events, including the 1964 Summer Olympics, the postponed 2020 Summer Olympics");
    }
        public void onClickThai (View view){
            TextClock clock = findViewById(R.id.my_clock);
            clock.setTimeZone("Asia/Bangkok");
            ImageView im = findViewById(R.id.cityImage);
            im.setImageResource(R.drawable.bangkok);
            TextView tv = findViewById(R.id.cityContent);
            tv.setText("Bangkok[a] is the capital and most populous city of Thailand. It is known in Thai as Krung Thep Maha Nakhon[b] or simply Krung Thep.[c] The city occupies 1,568.7 square kilometres (605.7 sq mi) in the Chao Phraya River delta in central Thailand and has an estimated population of 10.539 million as of 2020, 15.3 percent of the country's population.");
        }

        public void onClickTunisia (View view){
            TextClock clock = findViewById(R.id.my_clock);
            clock.setTimeZone("Africa/Tunis");
            ImageView im = findViewById(R.id.cityImage);
            im.setImageResource(R.drawable.tunisia);
            TextView tv = findViewById(R.id.cityContent);
            tv.setText("Tunisia,[a] officially the Republic of Tunisia,[b] is the northernmost country in Africa. It is a part of the Maghreb region of North Africa, ");
        }
        public void onClickMexico (View view){
            TextClock clock = findViewById(R.id.my_clock);
            clock.setTimeZone("America/Mexico_City");
            ImageView im = findViewById(R.id.cityImage);
            im.setImageResource(R.drawable.mexicocity);
            TextView tv = findViewById(R.id.cityContent);
            tv.setText("is the capital and largest city of Mexico and the most populous city in North America");

        }
    }

