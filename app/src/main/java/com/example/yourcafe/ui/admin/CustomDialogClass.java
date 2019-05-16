package com.example.yourcafe.ui.admin;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.yourcafe.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button save, cancel, del;

    public CustomDialogClass(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        save = (Button) findViewById(R.id.buttonSave);
        cancel = (Button) findViewById(R.id.buttonCancel);
        del = (Button) findViewById(R.id.buttonDel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
//                c.finish();
                break;
            case R.id.buttonCancel:
                c.finish();
                break;
            case R.id.buttonDel:
//                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
