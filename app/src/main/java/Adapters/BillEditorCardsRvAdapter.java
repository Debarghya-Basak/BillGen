package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtapps.billgen.R;

public class BillEditorCardsRvAdapter extends RecyclerView.Adapter<BillEditorCardsRvAdapter.ViewHolder> {

    private Context context;

    @NonNull
    @Override
    public BillEditorCardsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bill_editor_card_rv, parent,false);

        return new BillEditorCardsRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillEditorCardsRvAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
