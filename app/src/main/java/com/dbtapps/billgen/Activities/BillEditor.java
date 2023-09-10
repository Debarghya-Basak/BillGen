package com.dbtapps.billgen.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityBillEditorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

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
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        LinearLayout addItemAddColorOrSizeBtnContainerLl = bottomSheetDialog.findViewById(R.id.addItemAddColorOrSizeBtnContainerLl);
        LinearLayout addItemContainerLl = bottomSheetDialog.findViewById(R.id.addItemContainerLl);
        LinearLayout addColorOrSizeContainerLl = bottomSheetDialog.findViewById(R.id.addColorOrSizeContainerLl);
        LinearLayout addCancelBtnContainerLl = bottomSheetDialog.findViewById(R.id.addCancelBtnContainerLl);
        MaterialButton addItemBtn = bottomSheetDialog.findViewById(R.id.addItemBtn);
        MaterialButton addColorOrSizeBtn = bottomSheetDialog.findViewById(R.id.addColorOrSizeBtn);

        addItemBtn.setOnClickListener(v -> {
            addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
            addItemContainerLl.setVisibility(View.VISIBLE);
            addCancelBtnContainerLl.setVisibility(View.VISIBLE);
        });

        addColorOrSizeBtn.setOnClickListener(v -> {
            addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
            addColorOrSizeContainerLl.setVisibility(View.VISIBLE);
            addCancelBtnContainerLl.setVisibility(View.VISIBLE);
        });

        bottomSheetDialog.show();
    }



}