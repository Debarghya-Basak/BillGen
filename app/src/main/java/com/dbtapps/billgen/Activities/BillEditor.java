package com.dbtapps.billgen.Activities;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityBillEditorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DatabaseManager.FirebaseDatabaseConnections;
import DatabaseManager.FirebaseDatabaseDataHandler;

public class BillEditor extends AppCompatActivity {

    private ActivityBillEditorBinding binding;
    private ArrayList<Map<String, String>> bill;
    private Map<String, String> billItem;
    private int chooseItemOrColorOrSizeFlag = -1;
    private ArrayAdapter<String> listAdapter;
    //String tempArr[] = {"Hello", "My", "Name", "Is", "Debarghya", "Basak","And","My","Parent's","Name","Is","Debasish Basak","And","Debashree Basak"};


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
        bill = new ArrayList<>();

        FirebaseDatabaseDataHandler.receiveDataSnapshotsFromDatabase();
    }

    private void btnListeners() {

        binding.addBtn.setOnClickListener(v -> {
            showBottomSheetDialog();
        });

    }

    private void showBottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.add_item_colororsize_dialog);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        LinearLayout addItemAddColorOrSizeBtnContainerLl = bottomSheetDialog.findViewById(R.id.addItemAddColorOrSizeBtnContainerLl);
        LinearLayout addItemContainerLl = bottomSheetDialog.findViewById(R.id.addItemContainerLl);
        LinearLayout addColorOrSizeContainerLl = bottomSheetDialog.findViewById(R.id.addColorOrSizeContainerLl);
        LinearLayout addCancelBtnContainerLl = bottomSheetDialog.findViewById(R.id.addCancelBtnContainerLl);
        MaterialButton addItemBtn = bottomSheetDialog.findViewById(R.id.addItemBtn);
        MaterialButton addColorOrSizeBtn = bottomSheetDialog.findViewById(R.id.addColorOrSizeBtn);
        MaterialButton addBtn = bottomSheetDialog.findViewById(R.id.addBtn);
        MaterialButton cancelBtn = bottomSheetDialog.findViewById(R.id.cancelBtn);
        AutoCompleteTextView itemNameActv = bottomSheetDialog.findViewById(R.id.itemNameActv);
        AutoCompleteTextView colorOrSizeActv = bottomSheetDialog.findViewById(R.id.colorOrSizeActv);

        addItemBtn.setOnClickListener(v -> {
            addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
            addItemContainerLl.setVisibility(View.VISIBLE);
            addCancelBtnContainerLl.setVisibility(View.VISIBLE);

            chooseItemOrColorOrSizeFlag = 0;

            //TODO: TEMPORARY
            if(FirebaseDatabaseDataHandler.items != null) {
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseDatabaseDataHandler.items);
                itemNameActv.setAdapter(listAdapter);
                itemNameActv.setDropDownHeight(0);

                itemNameActv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count != 0)
                            itemNameActv.setDropDownHeight(WRAP_CONTENT);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        addColorOrSizeBtn.setOnClickListener(v -> {
            addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
            addColorOrSizeContainerLl.setVisibility(View.VISIBLE);
            addCancelBtnContainerLl.setVisibility(View.VISIBLE);

            chooseItemOrColorOrSizeFlag = 1;

            //TODO: TEMPORARY
            if(FirebaseDatabaseDataHandler.colorsorsizes != null) {
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseDatabaseDataHandler.colorsorsizes);
                colorOrSizeActv.setAdapter(listAdapter);
                colorOrSizeActv.setDropDownHeight(0);

                colorOrSizeActv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count != 0)
                            colorOrSizeActv.setDropDownHeight(WRAP_CONTENT);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        addBtn.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();

            if(chooseItemOrColorOrSizeFlag == 0){
                billItem = new HashMap<>();
                billItem.put("Type", "AddItem");
//                billItem.put("ItemName", itemNameActv);
            }
            else if(chooseItemOrColorOrSizeFlag == 1){
                billItem = new HashMap<>();
                billItem.put("Type", "AddColorOrSize");
            }

        });

        cancelBtn.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }



}