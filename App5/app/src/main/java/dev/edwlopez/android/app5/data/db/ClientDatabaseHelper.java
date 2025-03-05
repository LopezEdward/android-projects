package dev.edwlopez.android.app5.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientDatabaseHelper extends SQLiteOpenHelper {
    private static final String DEFAULT_NAME = "client";
    private final Context context;

    public ClientDatabaseHelper (Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
