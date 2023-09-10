package DatabaseManager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Adapters.LoadDatabaseNamesRVAdapter;

public class FirebaseDatabaseManager {

    public static FirebaseFirestore db;
    public static ArrayList<String> databaseNames;

    public static void init(){
        db = FirebaseFirestore.getInstance();
    }

    public static void getDatabaseNamesFromFirebase(Context context, RecyclerView databaseNamesRv){

        //TODO: Replace .document("uid") with the userid of user if app is remade with user authentication
        db.collection("database_names")
                .document("uid")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){

                        DocumentSnapshot documentSnapshot = task.getResult();
                        databaseNames = (ArrayList<String>) documentSnapshot.get("names");
                        if(databaseNames != null) {
                            Log.d("Debug", "FirebaseDatabaseManager : " + databaseNames.toString());

                            LoadDatabaseNamesRVAdapter adapter = new LoadDatabaseNamesRVAdapter(context, databaseNames);
                            databaseNamesRv.setAdapter(adapter);
                            databaseNamesRv.setLayoutManager(new LinearLayoutManager(context));
                        }
                        else {
                            Log.d("Debug", "FirebaseDatabaseManager : No Companies present");
                            Toast.makeText(context, "No companies present. Please create one.", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Log.d("Debug", "FirebaseDatabaseManager : Error fetching database names");
                    }
                });

    }

    public static void addDatabaseNameToFirebase(Activity activity, String name){

        //Fetch company names from firebase firestore and store it in arraylist
        db.collection("database_names")
                .document("uid")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){

                        DocumentSnapshot documentSnapshot = task.getResult();
                        databaseNames = (ArrayList<String>) documentSnapshot.get("names");

                        //If company is present then add new company in the arraylist and send the new arraylist to firestore
                        if(databaseNames != null) {
                            Log.d("Debug", "FirebaseDatabaseManager : Old Array " + databaseNames.toString());
                            if(!databaseNames.contains(name)) {
                                databaseNames.add(name);

                                Map<String, ArrayList<String>> data = new HashMap<>();
                                data.put("names", databaseNames);

                                Map<String, Timestamp> companyCreationTimestamp = new HashMap<>();
                                companyCreationTimestamp.put("timestamp", Timestamp.now());

                                db.collection(databaseNames.get(databaseNames.size()-1))
                                        .add(companyCreationTimestamp)
                                        .addOnSuccessListener(documentReferenceTemp -> {
                                            Log.d("Debug", "FirebaseDatabaseManager : Company data added to Firebase");

                                            db.collection("database_names")
                                                    .document("uid")
                                                    .set(data)
                                                    .addOnSuccessListener(documentReference -> {
                                                        Log.d("Debug", "FirebaseDatabaseManager : Company added to Firebase");
                                                        Toast.makeText(activity.getApplicationContext(), "Company Successfully added", Toast.LENGTH_SHORT).show();
                                                        activity.onBackPressed();
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        Log.d("Debug" , "FirebaseDatabaseManager : Company failed to be added");
                                                        activity.onBackPressed();
                                                    });

                                        })
                                        .addOnFailureListener(e -> {
                                            Log.d("Debug" , "FirebaseDatabaseManager : Company data failed to be added");
                                            activity.onBackPressed();
                                        });

                            }
                            else {
                                Log.d("Debug", "FirebaseDatabaseManage : Company already present. Please load company");
                                Toast.makeText(activity.getApplicationContext(), "Company already present. Please load company", Toast.LENGTH_SHORT).show();
                                activity.onBackPressed();
                            }
                            Log.d("Debug", "FirebaseDatabaseManager : New Array " + databaseNames.toString());
                        }
                        else {
                            Log.d("Debug", "FirebaseDatabaseManager : No Companies present");
                            databaseNames = new ArrayList<>();
                            databaseNames.add(name);

                            Map<String, ArrayList<String>> data = new HashMap<>();
                            data.put("names", databaseNames);

                            Map<String, Timestamp> companyCreationTimestamp = new HashMap<>();
                            companyCreationTimestamp.put("timestamp", Timestamp.now());

                            db.collection(databaseNames.get(databaseNames.size()-1))
                                    .add(companyCreationTimestamp)
                                    .addOnSuccessListener(documentReferenceTemp -> {
                                        Log.d("Debug", "FirebaseDatabaseManager : Company data added to Firebase");

                                        db.collection("database_names")
                                                .document("uid")
                                                .set(data)
                                                .addOnSuccessListener(documentReference -> {
                                                    Log.d("Debug", "FirebaseDatabaseManager : Company added to Firebase");
                                                    Toast.makeText(activity.getApplicationContext(), "Company Successfully added", Toast.LENGTH_SHORT).show();
                                                    activity.onBackPressed();
                                                })
                                                .addOnFailureListener(e -> {
                                                    Log.d("Debug" , "FirebaseDatabaseManager : Company failed to be added");
                                                    activity.onBackPressed();
                                                });

                                    })
                                    .addOnFailureListener(e -> {
                                        Log.d("Debug" , "FirebaseDatabaseManager : Company data failed to be added");
                                        activity.onBackPressed();
                                    });
                            Log.d("Debug", "FirebaseDatabaseManager : New Array " + databaseNames.toString());
                        }

                    }
                    else{
                        Log.d("Debug", "FirebaseDatabaseManager : Error fetching database names");
                    }
                });


    }

}
