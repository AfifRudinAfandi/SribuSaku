package com.aknela.sribusaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class ActivityLainnya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lainnya);



        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.btn_kembali_lainnya);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        FrameLayout frameLayout10 = (FrameLayout) findViewById(R.id.ly_pulsa);
        frameLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLainnya.this, PulsaActivity.class);
                startActivity(intent);
            }
        });


        FrameLayout frameLayout9 = (FrameLayout) findViewById(R.id.ly_zakat);
        frameLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLainnya.this, ZakatActivity.class);
                startActivity(intent);
            }
        });
        }
    }
