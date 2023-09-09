package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityCreateCompanyBinding;

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

            Toast.makeText(this, "Company created", Toast.LENGTH_SHORT).show();
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main_action_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}