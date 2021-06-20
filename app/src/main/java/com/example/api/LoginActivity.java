package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.database.AppDatabase;
import com.example.api.database.DataUser;
import com.example.api.database.DataUserDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvRegister;
    EditText etUsername, etPassword;
    Button btnLogin;
    DataUserDAO dataUserDAO;
    String dbUsername, dbPassword, dbFullname;
    int dbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.ti_username);
        etPassword = findViewById(R.id.ti_password);

        dataUserDAO = AppDatabase.getAppDatabase(this).dao();

        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (btnLogin.equals(v)) {
            String sUsername = etUsername.getText().toString();
            String sPassword = etPassword.getText().toString();
            if (sUsername.equals("")) {
                Toast.makeText(getApplicationContext(), "Username is required!", Toast.LENGTH_SHORT).show();
            }
            if (sPassword.equals("")) {
                Toast.makeText(getApplicationContext(), "Password is required!", Toast.LENGTH_SHORT).show();
            }
            if (!sUsername.equals("") && !sPassword.equals("")) {
                checkUser(sUsername, sPassword);
            }
        }
        if (tvRegister.equals(v)) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void checkUser(String username, String password) {
        boolean checking = dataUserDAO.dataExist(username);
        boolean login = false;
        if (checking) {
            List<DataUser> datas = dataUserDAO.getData();
            Log.d("Hasil : ", String.valueOf(datas.size()));
            for (DataUser dataUser : datas) {
                System.out.println("Output");
                if (dataUser.getUsername().equals(username) && dataUser.getPassword().equals(password)) {
                    Log.d("Hasil : ", dataUser.getUsername());
                    dbId = dataUser.getId();
                    dbUsername = dataUser.getUsername();
                    dbFullname = dataUser.getFullname();
                    dbPassword = dataUser.getPassword();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", String.valueOf(dbId));
                    intent.putExtra("username", dbUsername);
                    intent.putExtra("fullname", dbFullname);
                    intent.putExtra("password", dbPassword);
                    login = true;
                    Toast.makeText(getApplicationContext(), "Selamat! User berhasil login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
            if(!login){
                Toast.makeText(getApplicationContext(), "Password is wrong!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Maaf user belum terdaftar", Toast.LENGTH_SHORT).show();
        }
    }
}