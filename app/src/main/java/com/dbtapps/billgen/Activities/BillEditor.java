package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityBillEditorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import DatabaseManager.FirebaseDatabaseConnections;

public class BillEditor extends AppCompatActivity {

    private ActivityBillEditorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initPage();
        btnListeners();
    }

    private void initPage(){
        binding.companyNameDisplayMtv.setText(FirebaseDatabaseConnections.connectedDatabaseName);
    }

    private void btnListeners() {

        binding.addBtn.setOnClickListener(v -> {
            showBottomSheetDialog();
        });

    }

    private void showBottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.add_item_colorandsize_dialog);
        bottomSheetDialog.show();
    }



}