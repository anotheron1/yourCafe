package com.example.yourcafe.ui.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yourcafe.R;
import com.hbb20.CountryCodePicker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    EditText fullNameEditText;
    @Email
    EditText usernameRegEditText;
    @Password
    EditText passwordRegEditText;
    @ConfirmPassword
    EditText confirmPassRegEditText;
    CountryCodePicker ccp;
    @NotEmpty
    EditText editTextCarrierNumber;
    Button regDoneButton;
    Button regPhoneCheck;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fullNameEditText = (EditText) findViewById(R.id.fullnameReg);
        usernameRegEditText = (EditText) findViewById(R.id.usernameReg);
        passwordRegEditText = (EditText) findViewById(R.id.passwordReg);
        confirmPassRegEditText = (EditText) findViewById(R.id.passwordRegRepeat);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        editTextCarrierNumber = (EditText) findViewById(R.id.editText_carrierNumber);
        ccp.registerCarrierNumberEditText(editTextCarrierNumber);
        regDoneButton = findViewById(R.id.regDone);
        regPhoneCheck = findViewById(R.id.regPhoneCheck);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        validator = new Validator(this);
        validator.setValidationListener(this);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                validator.validate();
            }
        };
        fullNameEditText.addTextChangedListener(afterTextChangedListener);
        usernameRegEditText.addTextChangedListener(afterTextChangedListener);
        passwordRegEditText.addTextChangedListener(afterTextChangedListener);
        confirmPassRegEditText.addTextChangedListener(afterTextChangedListener);
        editTextCarrierNumber.addTextChangedListener(afterTextChangedListener);
    }

    @Override
    public void onValidationSucceeded() {
        regPhoneCheck.setEnabled(true);
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void regDone(View view) {

    }

    public void regPhoneCheckClick(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm phone number");
        alert.setMessage("Expect a message with a confirmation code");
        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString(); //if value == sms code
                regDoneButton.setEnabled(true);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }
}
