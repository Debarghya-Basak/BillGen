package Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtapps.billgen.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

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
        View view = inflater.inflate(R.layout.database_names_item_rv, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadDatabaseNamesRVAdapter.ViewHolder holder, int position) {

        holder.databaseNameTv.setText(databaseNames.get(position));

    }

    @Override
    public int getItemCount() {
        return databaseNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView databaseNameTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            databaseNameTv = (MaterialTextView) itemView.findViewById(R.id.databaseNameTv);
        }
    }
}
