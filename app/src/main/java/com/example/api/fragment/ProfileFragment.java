package com.example.api.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.LoginActivity;
import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.database.AppDatabase;
import com.example.api.database.DataUser;
import com.example.api.database.DataUserDAO;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    String username;
    String fullname;
    String password;
    int id;

    public ProfileFragment(String username, String fullname, String password, int id) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.id = id;
    }

    EditText etUsername, etFullname, etPassword;
    TextView tvUsername;
    Button btnEdit, btnDelete, btnLogout;
    DataUserDAO dataUserDAO;
    DataUser dataUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataUserDAO = AppDatabase.getAppDatabase(getContext()).dao();
        etUsername = view.findViewById(R.id.ti_username);
        etFullname = view.findViewById(R.id.ti_fullname);
        etPassword = view.findViewById(R.id.ti_password);
        tvUsername = view.findViewById(R.id.username);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnEdit = view.findViewById(R.id.btn_update);
        btnLogout = view.findViewById(R.id.btn_logout);

        etUsername.setText(username);
        etFullname.setText(fullname);
        etPassword.setText(password);
        tvUsername.setText(username);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnEdit.equals(v)) {
            String sUsername = etUsername.getText().toString();
            String sFullname = etFullname.getText().toString();
            String sPassword = etPassword.getText().toString();

            DataUser userUpdate = new DataUser(id, sUsername, sFullname, sPassword);
            dataUserDAO.updateData(userUpdate);
            Toast.makeText(getContext(), "Berhasil Update Data User", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra("id", String.valueOf(id));
            intent.putExtra("username", sUsername);
            intent.putExtra("fullname", sFullname);
            intent.putExtra("password", sPassword);
            startActivity(intent);
        }
        if (btnDelete.equals(v)) {
            DataUser userDelete = new DataUser(id, username, fullname, password);
            deleteData(userDelete);
        }
        if (btnLogout.equals(v)) {
            Toast.makeText(getContext(), "Berhasil Logout! Terimakasih", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    public void deleteData(DataUser item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Menghapus Data").setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataUserDAO.deleteData(item);
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(R.drawable.ic_baseline_delete_24)
                .show();
    }
}