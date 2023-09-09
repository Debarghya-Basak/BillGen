package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityLoadCompanyBinding;

public class LoadCompanyActivity extends AppCompatActivity {

    private ActivityLoadCompanyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}