package com.dbtapps.billgen.Activities;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dbtapps.billgen.R;
import com.dbtapps.billgen.databinding.ActivityBillEditorBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapters.BillEditorCardsRvAdapter;
import DatabaseManager.FirebaseDatabaseConnections;
import DatabaseManager.FirebaseDatabaseDataHandler;

public class BillEditor extends AppCompatActivity {

    private ActivityBillEditorBinding binding;
    private ArrayList<Map<String, String>> bill;
    private Map<String, String> billItem;
    private String lastItemGroupName = "";
    private int chooseItemOrColorOrSizeFlag = -1;
    private ArrayAdapter<String> listAdapter;
    private String itemUnitArr[] = {"Kg", "Gm", "Ct", "Rt", "Tl"};


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
        AutoCompleteTextView itemUnitActv = bottomSheetDialog.findViewById(R.id.itemUnitActv);
        TextInputEditText quantityTiet = bottomSheetDialog.findViewById(R.id.quantityTiet);
        TextInputEditText pricePerUnitTiet = bottomSheetDialog.findViewById(R.id.pricePerUnitTiet);

        addItemBtn.setOnClickListener(v -> {
            if(bill.size() == 0) {
                addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
                addItemContainerLl.setVisibility(View.VISIBLE);
                addCancelBtnContainerLl.setVisibility(View.VISIBLE);

                chooseItemOrColorOrSizeFlag = 0;

                //TODO: TEMPORARY
                if (FirebaseDatabaseDataHandler.item_names != null) {
                    listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseDatabaseDataHandler.item_names);
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
            }
            else if(bill.size() != 0){

                if(bill.get(bill.size()-1).get("Type").equals("Item"))
                    Toast.makeText(this, "Please delete the previous item group to add another item.",Toast.LENGTH_SHORT).show();
                else{
                    addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
                    addItemContainerLl.setVisibility(View.VISIBLE);
                    addCancelBtnContainerLl.setVisibility(View.VISIBLE);

                    chooseItemOrColorOrSizeFlag = 0;

                    //TODO: TEMPORARY
                    if (FirebaseDatabaseDataHandler.item_names != null) {
                        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseDatabaseDataHandler.item_names);
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
                }

            }

        });

        addColorOrSizeBtn.setOnClickListener(v -> {

            if(bill.size() != 0) {

                addItemAddColorOrSizeBtnContainerLl.setVisibility(View.GONE);
                addColorOrSizeContainerLl.setVisibility(View.VISIBLE);
                addCancelBtnContainerLl.setVisibility(View.VISIBLE);

                chooseItemOrColorOrSizeFlag = 1;

                //TODO: TEMPORARY
                if (FirebaseDatabaseDataHandler.colororsize_names != null) {
                    listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FirebaseDatabaseDataHandler.colororsize_names);
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

                ArrayAdapter<String> itemUnitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemUnitArr);
                itemUnitActv.setAdapter(itemUnitAdapter);
            }
            else{
                Toast.makeText(this, "Add an item first to add color or sizes.", Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(v -> {

            if(chooseItemOrColorOrSizeFlag == 0){
                if(!TextUtils.isEmpty(itemNameActv.getText())) {
                    billItem = new HashMap<>();
                    billItem.put("Type", "Item");
                    billItem.put("Name", itemNameActv.getText().toString());

                    bill.add(billItem);
                    Log.d("Debug", "BillEditor : " + itemNameActv.getText().toString());

                    FirebaseDatabaseDataHandler.addItemNameToDatabase(this, itemNameActv.getText().toString());

                    bottomSheetDialog.dismiss();

                    BillEditorCardsRvAdapter rvAdapter = new BillEditorCardsRvAdapter(this, bill);
                    binding.billItemCardsRv.setAdapter(rvAdapter);
                    binding.billItemCardsRv.setLayoutManager(new LinearLayoutManager(this));
                }
                else{
                    Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
                }
            }
            else if(chooseItemOrColorOrSizeFlag == 1){

                if(!(TextUtils.isEmpty(colorOrSizeActv.getText()) || TextUtils.isEmpty(quantityTiet.getText()) || TextUtils.isEmpty(itemUnitActv.getText()) || TextUtils.isEmpty(pricePerUnitTiet.getText()))) {
                    billItem = new HashMap<>();
                    billItem.put("Type", "ColorOrSize");
                    billItem.put("Name", lastItemGroupName);
                    billItem.put("ColorOrSize", colorOrSizeActv.getText().toString());
                    billItem.put("Quantity", quantityTiet.getText().toString());
                    billItem.put("Unit", itemUnitActv.getText().toString());
                    billItem.put("PricePerUnit", pricePerUnitTiet.getText().toString());

                    bill.add(billItem);
                    Log.d("Debug", "BillEditor : Unit -> " + billItem.get("Unit"));

                    FirebaseDatabaseDataHandler.addColorOrSizeToDatabase(this, colorOrSizeActv.getText().toString());

                    bottomSheetDialog.dismiss();

                    BillEditorCardsRvAdapter rvAdapter = new BillEditorCardsRvAdapter(this, bill);
                    binding.billItemCardsRv.setAdapter(rvAdapter);
                    binding.billItemCardsRv.setLayoutManager(new LinearLayoutManager(this));
                }
                else{
                    Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
                }

            }

        });

        cancelBtn.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();

    }

}