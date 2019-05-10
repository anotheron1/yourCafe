package com.example.yourcafe.ui.caffeClientMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.yourcafe.R;

public class ccmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffe_client_menu);
        String caffeName = getIntent().getStringExtra("caffeName");
        Toast.makeText(getApplicationContext(), caffeName, Toast.LENGTH_LONG).show();
    }
}
