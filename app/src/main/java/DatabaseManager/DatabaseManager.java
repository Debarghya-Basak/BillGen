package DatabaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBNAMES.db";
    private static final String TABLE_NAME = "databasenames";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static Context context;

    public DatabaseManager(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDatabaseName(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1)
            Toast.makeText(context, "Failed to create database", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Database created successfully", Toast.LENGTH_SHORT).show();

    }
}
