package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.dbtapps.billgen.databinding.ActivityCreateCompanyBinding;

import DatabaseManager.DatabaseDataHandler;
import DatabaseManager.FirebaseDatabaseManager;
import DatabaseManager.SqliteDatabaseConnections;

public class CreateCompanyActivity extends AppCompatActivity {

    private ActivityCreateCompanyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.topAppBarTab);
        btnListeners();
    }

    private void btnListeners() {
        binding.submitBtn.setOnClickListener(v -> {
            binding.companyNameTil.clearFocus();

            FirebaseDatabaseManager.addDatabaseNameToFirebase(this, binding.companyNameTiet.getText().toString());

            //TODO: SQlite Database code
//            if(new DatabaseManager(this).contains(binding.companyNameTiet.getText().toString())){
//                Toast.makeText(this, "Company already present", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                SqliteDatabaseConnections.DBDATA = new DatabaseDataHandler(this, binding.companyNameTiet.getText().toString()).getWritableDatabase();
//                new DatabaseManager(this).addDatabaseName(binding.companyNameTiet.getText().toString());
//            }

        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main_action_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}