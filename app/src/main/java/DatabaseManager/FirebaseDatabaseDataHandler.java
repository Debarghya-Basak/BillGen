package DatabaseManager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dbtapps.billgen.Activities.BillEditor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDatabaseDataHandler {

    public static ArrayList<String> item_names;
    public static ArrayList<String> colororsize_names;
    public static HashMap<String, Double> price_list_table;

    public static void receiveDataSnapshotsFromDatabase(){

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("items")
                .addSnapshotListener(((value, error) -> {
                    item_names = (ArrayList<String>) value.get("item_names");
                    if(item_names != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + item_names.toString());
                }));

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("colorsorsizes")
                .addSnapshotListener(((value, error) -> {
                    colororsize_names = (ArrayList<String>) value.get("colororsize_names");
                    if(colororsize_names != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + colororsize_names.toString());
                }));

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("price_list")
                .addSnapshotListener(((value, error) -> {
                    price_list_table = (HashMap<String, Double>) value.get("price_list_table");
                    if(price_list_table != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + price_list_table.toString());
                }));

    }

    public static void addItemNameToDatabase(Context context, String itemName) {

        if(item_names == null) {
            item_names = new ArrayList<>();
        }

        if(!item_names.contains(itemName)){
            item_names.add(itemName);
            Map<String, ArrayList<String>> data = new HashMap<>();
            data.put("item_names", item_names);

            FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                    .document("items")
                    .set(data)
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Cant add item name to database", Toast.LENGTH_SHORT).show();
                    });
        }

    }

    public static void addColorOrSizeToDatabase(Context context, String colorOrSizeName) {

        if(colororsize_names == null) {
            colororsize_names = new ArrayList<>();
        }

        if(!colororsize_names.contains(colorOrSizeName)) {
            colororsize_names.add(colorOrSizeName);
            Map<String, ArrayList<String>> data = new HashMap<>();
            data.put("colororsize_names", colororsize_names);

            FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                    .document("colorsorsizes")
                    .set(data)
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Cant add Color or size names to database", Toast.LENGTH_SHORT).show();
                    });
        }

    }
}
