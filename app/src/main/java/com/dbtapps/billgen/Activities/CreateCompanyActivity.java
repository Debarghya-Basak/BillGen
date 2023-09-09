package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityCreateCompanyBinding;

public class CreateCompanyActivity extends AppCompatActivity {

    private ActivityCreateCompanyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}