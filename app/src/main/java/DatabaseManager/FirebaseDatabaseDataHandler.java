package DatabaseManager;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class FirebaseDatabaseDataHandler {

    public static ArrayList<String> items;
    public static ArrayList<String> colorsorsizes;
    public static HashMap<String, Double> priceList;

    public static void receiveDataSnapshotsFromDatabase(){

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("items")
                .addSnapshotListener(((value, error) -> {
                    items = (ArrayList<String>) value.get("item_names");
                    if(items != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + items.toString());
                }));

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("colorsorsizes")
                .addSnapshotListener(((value, error) -> {
                    colorsorsizes = (ArrayList<String>) value.get("colororsize_names");
                    if(colorsorsizes != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + colorsorsizes.toString());
                }));

        FirebaseDatabaseManager.db.collection(FirebaseDatabaseConnections.connectedDatabaseName)
                .document("price_list")
                .addSnapshotListener(((value, error) -> {
                    priceList = (HashMap<String, Double>) value.get("price_list_table");
                    if(priceList != null)
                        Log.d("Debug", "FirebaseDatabaseDataHandler : " + priceList.toString());
                }));

    }

}
