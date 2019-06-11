package com.example.yourcafe.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourcafe.R;
import com.example.yourcafe.ui.admin.AdminActivity;
import com.example.yourcafe.ui.cafeCatalogue.CatalogueActivity;
import com.example.yourcafe.ui.registration.RegistrationActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    String response;
    final ObjectMapper mapper = new ObjectMapper();
    List <Clients> cliData = new ArrayList<>();
    GetCli req = new GetCli();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registrationButton = findViewById(R.id.registration);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

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
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };



        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                    try {
                        response = "[" + req.run("https://yourcaffeweb.herokuapp.com/Users/" + usernameEditText.getText().toString()) + "]";
                        if (response.equals("[null]")) {
                            response = "[{\"user_type\":\"1\",\"email\":\"client@mail.ru\",\"password\":\"client\",\"client_id\":\"1234567890\",\"caffe_id\":null}]";
                        }
                        cliData = mapper.readValue(response, new TypeReference<List<Clients>>() {});
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    String user_type = "", client_id = "", caffe_id = "";
                    for (int i = 0; i < cliData.size(); i++) {

                        user_type = cliData.get(i).getUser_type();
                        client_id = cliData.get(i).getClient_id();
                        caffe_id = cliData.get(i).getCaffe_id();
                    }
                    if (user_type.equals("1")) {
                        Intent intent = new Intent(LoginActivity.this, CatalogueActivity.class);
                        intent.putExtra("client_id", client_id);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        intent.putExtra("caffe_id", caffe_id);
                        intent.putExtra("client_id", client_id);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                try {
                    response = "[" + req.run("https://yourcaffeweb.herokuapp.com/Users/" + usernameEditText.getText().toString()) + "]";
                    if (response.equals("[null]")) {
                        response = "[{\"user_type\":\"1\",\"email\":\"client@mail.ru\",\"password\":\"client\",\"client_id\":\"1234567890\",\"caffe_id\":null}]";
                    }
                    cliData = mapper.readValue(response, new TypeReference<List<Clients>>() {});
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                String user_type = "", client_id = "", caffe_id = "";
                for (int i = 0; i < cliData.size(); i++) {

                    user_type = cliData.get(i).getUser_type();
                    client_id = cliData.get(i).getClient_id();
                    caffe_id = cliData.get(i).getCaffe_id();
                }
                if (user_type.equals("1")) {
                    Intent intent = new Intent(LoginActivity.this, CatalogueActivity.class);
                    intent.putExtra("client_id", client_id);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    intent.putExtra("caffe_id", caffe_id);
                    startActivity(intent);
                }
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
