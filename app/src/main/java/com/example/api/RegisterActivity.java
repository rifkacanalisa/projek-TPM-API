package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.database.AppDatabase;
import com.example.api.database.DataUser;
import com.example.api.database.DataUserDAO;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    DataUserDAO dataUserDAO;
    EditText etUsername, etFullname, etPassword;
    TextView btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataUserDAO = AppDatabase.getAppDatabase(this).dao();

        etUsername = findViewById(R.id.ti_username);
        etFullname = findViewById(R.id.ti_fullname);
        etPassword = findViewById(R.id.ti_password);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(btnRegister.equals(v)){
            String username = etUsername.getText().toString();
            String fullname = etFullname.getText().toString();
            String password = etPassword.getText().toString();

            DataUser dataUser = new DataUser(username, fullname, password);
            dataUserDAO.InsertData(dataUser);
            Toast.makeText(getApplicationContext(), "Berhasil Registrasi Data User", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(btnLogin.equals(v)){
            finish();
        }
    }
}