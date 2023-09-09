package com.dbtapps.billgen.Activities;

/*TO RUN ANDROID 13 EMULATOR (USING VULKAN DRIVER)......

GO TO COMMAND LINE AND TYPE

emulator -avd <name of the emulator> -feature -Vulkan
*/


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dbtapps.billgen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnListeners();
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