package com.dbtapps.billgen.Activities;

/*TO RUN ANDROID 13 EMULATOR (USING VULKAN DRIVER)......

GO TO COMMAND LINE AND TYPE

emulator -avd <name of the emulator> -feature -Vulkan
*/

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.dbtapps.billgen.databinding.ActivityMainBinding;

import DatabaseManager.DatabaseManager;
import DatabaseManager.FirebaseDatabaseManager;
import DatabaseManager.SqliteDatabaseConnections;
import Utilities.PermissionManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TODO: SQLite datbase code
        dbnamesInit();
        permissionManager();
        btnListeners();
    }

    private void dbnamesInit() {
        //SqliteDatabaseConnections.DBNAMES = new DatabaseManager(this).getWritableDatabase();
        FirebaseDatabaseManager.init();
    }

    private void permissionManager() {
        PermissionManager.permissionManager(this);
    }

    private void btnListeners(){
        binding.createCompanyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateCompanyActivity.class);
            startActivity(intent);
        });

        binding.loadCompanyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoadCompanyActivity.class);
            startActivity(intent);
        });
    }
}