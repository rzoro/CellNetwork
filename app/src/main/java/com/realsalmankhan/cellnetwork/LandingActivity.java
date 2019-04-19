package com.realsalmankhan.cellnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void gotoMain(View view) {
        finish();
        startActivity(new Intent(LandingActivity.this,MainActivity.class));
    }
}
