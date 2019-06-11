package com.example.yourcafe.ui.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yourcafe.R;
import com.example.yourcafe.ui.login.Clients;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class AdminActivity extends AppCompatActivity  implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    static final Integer CAMERA = 0x1;
    String caffe_id;
    String client_id;
    String response;
    final ObjectMapper mapper = new ObjectMapper();
//    List<Clients> cliData = new ArrayList<>();
    GetFill req = new GetFill();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_admin);
        caffe_id = getIntent().getStringExtra("caffe_id");
//        client_id = getIntent().getStringExtra("client_id");
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        contentFrame.addView(mScannerView);
        askForPermission(Manifest.permission.CAMERA, CAMERA);
//            mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
//            Button button = new Button(this);
//            button.setText("Мой магазин");
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(AdminActivity.this, CatalogueActivity.class);
//                    startActivity(intent);
//                }
//            });
//            button.setLayoutParams(
//                    new ViewGroup.LayoutParams(
//                            android.view.ViewGroup.LayoutParams.FILL_PARENT,
//                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
//                    )
//            );
//            mScannerView.addView(button);
//            setContentView(mScannerView);                // Set the scanner view as the content view

    }

    private void askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(AdminActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(AdminActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(AdminActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(AdminActivity.this, new String[]{permission}, requestCode);
            }
        } else {

            // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                //Camera
                case 1:
                    mScannerView.setResultHandler(this);
                    mScannerView.startCamera();
                    break;
            }
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        //в резалте будет айди клиента. делаем ПОСТ с айди клиена, айжи кофейни и "чашка +1"
        client_id = rawResult.getContents();
        try {
            response = req.run("https://yourcaffeweb.herokuapp.com/Interaction/FillCup?caffe_id=" + caffe_id + "&client_id=" + client_id);
            if (response == null) {response = "[{\"id\":1,\"client_id\":\"1234567890\",\"client_qr\":null,\"caffe_id\":\"1\",\"all_cup\":\"7\",\"fill_cup\":\"3\"}]";
            }
//            cliData = mapper.readValue(response, new TypeReference<List<Clients>>() {});
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Cup filled!", Toast.LENGTH_SHORT).show();
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

    public void admButton(View view) {
        this.finish();
        //передаем айди кофейни
        Intent intent = new Intent(AdminActivity.this, CabinetActivity.class);
        startActivity(intent);
    }
}
