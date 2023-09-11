package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtapps.billgen.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillEditorCardsRvAdapter extends RecyclerView.Adapter<BillEditorCardsRvAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Map<String, String>> bill;

    public BillEditorCardsRvAdapter(Context context, ArrayList<Map<String, String>> bill) {
        this.context = context;
        this.bill = bill;
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
        if(bill.get(position).get("Type").equals("Item")){
            holder.itemNameDisplayContainerLl.setVisibility(View.VISIBLE);
            holder.itemNameDisplayMtv.setText(bill.get(position).get("Name"));
        }
        else if(bill.get(position).get("Type").equals("ColorOrSize")){
            holder.colorOrSizeDisplayContainerSv.setVisibility(View.VISIBLE);
            holder.colorOrSizeDisplayMtv.setText(bill.get(position).get("ColorOrSize"));
            holder.quantityDisplayMtv.setText(bill.get(position).get("Quantity"));
            holder.unitDisplayMtv.setText(bill.get(position).get("Unit"));
            holder.itemPerUnitPriceDisplayMtv.setText(bill.get(position).get("PricePerUnit"));
            holder.itemTotalPriceDisplayMtv.setText((Double.parseDouble(bill.get(position).get("Quantity")) * Double.parseDouble(bill.get(position).get("PricePerUnit"))) + "");
        }

    }

    @Override
    public int getItemCount() {
        return bill.size();
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
