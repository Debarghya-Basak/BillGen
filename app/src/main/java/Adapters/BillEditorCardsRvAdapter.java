package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtapps.billgen.Activities.BillEditor;
import com.dbtapps.billgen.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillEditorCardsRvAdapter extends RecyclerView.Adapter<BillEditorCardsRvAdapter.ViewHolder> {

    private Context context;
    private BillEditor billEditor;

    public BillEditorCardsRvAdapter(Context context, BillEditor billEditor) {
        this.context = context;
        this.billEditor = billEditor;
    }

    @NonNull
    @Override
    public BillEditorCardsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bill_editor_card_rv, parent,false);

        return new BillEditorCardsRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillEditorCardsRvAdapter.ViewHolder holder, int position) {
        if(BillEditor.bill.get(position).get("Type").equals("Item")){
            holder.itemNameDisplayContainerLl.setVisibility(View.VISIBLE);
            holder.itemNameDisplayMtv.setText(BillEditor.bill.get(position).get("Name"));
        }
        else if(BillEditor.bill.get(position).get("Type").equals("ColorOrSize")){
            holder.colorOrSizeDisplayContainerSv.setVisibility(View.VISIBLE);
            holder.colorOrSizeDisplayMtv.setText(BillEditor.bill.get(position).get("ColorOrSize"));
            holder.quantityDisplayMtv.setText(BillEditor.bill.get(position).get("Quantity"));
            holder.unitDisplayMtv.setText(BillEditor.bill.get(position).get("Unit"));
            String output = String.format("%10.2f", Double.parseDouble(BillEditor.bill.get(position).get("PricePerUnit")));
            holder.itemPerUnitPriceDisplayMtv.setText(output);
            output = String.format("%10.2f", (Double.parseDouble(BillEditor.bill.get(position).get("Quantity")) * Double.parseDouble(BillEditor.bill.get(position).get("PricePerUnit"))));
            holder.itemTotalPriceDisplayMtv.setText(output);

            //TODO: NEW PART ADDED (in-check bit-unstable) (Keep checking all combinations till its stable)
            holder.deleteItemBtn.setOnClickListener(v -> {
                //TODO: Give confirmation dialog box for the same

                BillEditor.bill.remove(position);

                if(BillEditor.bill.size() == position){
                    if(BillEditor.bill.get(position-1).get("Type").equals("Item"))
                        BillEditor.bill.remove(position - 1);

                }
                else{
                    if(BillEditor.bill.get(position).get("Type").equals("Item") && BillEditor.bill.get(position-1).get("Type").equals("Item")){
                        BillEditor.bill.remove(position - 1);
                    }
                }

                billEditor.updateRecyclerView(position);
            });
        }

    }

    @Override
    public int getItemCount() {
        return BillEditor.bill.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemNameDisplayContainerLl;
        HorizontalScrollView colorOrSizeDisplayContainerSv;
        MaterialTextView itemNameDisplayMtv,colorOrSizeDisplayMtv,quantityDisplayMtv,unitDisplayMtv,itemPerUnitPriceDisplayMtv, itemTotalPriceDisplayMtv;
        MaterialButton deleteItemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemNameDisplayContainerLl = (LinearLayout) itemView.findViewById(R.id.itemNameDisplayContainerLl);
            colorOrSizeDisplayContainerSv = (HorizontalScrollView) itemView.findViewById(R.id.colorOrSizeDisplayContainerSv);

            itemNameDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.itemNameDisplayMtv);
            colorOrSizeDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.colorOrSizeDisplayMtv);
            quantityDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.quantityDisplayMtv);
            unitDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.unitDisplayMtv);
            itemTotalPriceDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.itemTotalPriceDisplayMtv);
            itemPerUnitPriceDisplayMtv = (MaterialTextView) itemView.findViewById(R.id.itemPerUnitPriceDisplayMtv);

            deleteItemBtn = (MaterialButton) itemView.findViewById(R.id.deleteItemBtn);

        }
    }
}
