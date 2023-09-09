package DatabaseManager;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseDataHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME;
    private static String TABLE_ITEM = "table_item";
    private static String TABLE_ITEM_ID = "id";
    private static String TABLE_ITEM_NAME = "name";
    private static String TABLE_COLORANDSIZE = "table_colorandsize";
    private static String TABLE_COLORANDSIZE_ID = "id";
    private static String TABLE_COLORANDSIZE_NAME = "name";

    public DatabaseDataHandler(@Nullable Context context, @Nullable String name) {
        super(context, name + ".db", null, 1);
        DATABASE_NAME = name + ".db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_ITEM +
                " (" + TABLE_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_ITEM_NAME + " TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_COLORANDSIZE +
                " (" + TABLE_COLORANDSIZE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COLORANDSIZE_NAME + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
