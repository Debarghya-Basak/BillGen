package Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtapps.billgen.Activities.BillEditor;
import com.dbtapps.billgen.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import DatabaseManager.FirebaseDatabaseConnections;

public class LoadDatabaseNamesRVAdapter extends RecyclerView.Adapter<LoadDatabaseNamesRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> databaseNames;

    public LoadDatabaseNamesRVAdapter(Context context, ArrayList<String> databaseNames) {
        this.context = context;
        this.databaseNames = databaseNames;
    }

    @NonNull
    @Override
    public LoadDatabaseNamesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.database_names_item_rv, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadDatabaseNamesRVAdapter.ViewHolder holder, int position) {

        holder.databaseNameTv.setText(databaseNames.get(position));
        holder.loadCompanyBtn.setOnClickListener(v -> {
            FirebaseDatabaseConnections.connectedDatabaseName = databaseNames.get(position);
            Intent intent = new Intent(context, BillEditor.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return databaseNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView databaseNameTv;
        MaterialButton loadCompanyBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            databaseNameTv = (MaterialTextView) itemView.findViewById(R.id.databaseNameTv);
            loadCompanyBtn = (MaterialButton) itemView.findViewById(R.id.loadCompanyBtn);
        }
    }
}
