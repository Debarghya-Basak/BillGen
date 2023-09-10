package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityLoadCompanyBinding;

import java.util.ArrayList;

import Adapters.LoadDatabaseNamesRVAdapter;
import DatabaseManager.DatabaseManager;
import DatabaseManager.FirebaseDatabaseManager;

public class LoadCompanyActivity extends AppCompatActivity {

    private ActivityLoadCompanyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: SQlite database codes
        //loadDatabaseNamesFromDatabase();
        loadDatabaseNamesFromFirebase();
    }

    private void loadDatabaseNamesFromFirebase() {

        FirebaseDatabaseManager.getDatabaseNamesFromFirebase(this, binding.databaseNamesRv);

    }

    private void loadDatabaseNamesFromDatabase() {

        ArrayList<String> databaseNames = new ArrayList<>();

        Cursor cursor = new DatabaseManager(this).returnDatabaseNames();

        if(cursor != null) {
            if (cursor.getCount() == 0)
                Toast.makeText(this, "No Companies to display. Please create one.", Toast.LENGTH_SHORT).show();
            else {
                while (cursor.moveToNext()){
                    databaseNames.add(cursor.getString(0));
                }
            }
        }

        LoadDatabaseNamesRVAdapter adapter = new LoadDatabaseNamesRVAdapter(this, databaseNames);
        binding.databaseNamesRv.setAdapter(adapter);
        binding.databaseNamesRv.setLayoutManager(new LinearLayoutManager(this));

    }
}