package doublee.plantmanager.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlantManagerHelper extends SQLiteOpenHelper {
    public static final String CREATE_INDEX_IMAGES = "create table if not exists images (" +
            "id integer primary key autoincrement, " +
            "time datetime, " +
            "imagepath text)";
    public static final String CREATE_INDEX_INDEXS = "create table if not exists indexs (" +
            "id integer primary key autoincrement, " +
            "time datetime, " +
            "humidity text, " +
            "temperature text, " +
            "quality text)";
    private Context mContext;
    private String TAG = "PlantManagerHelper";

    public PlantManagerHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "PlantManager3.0.db", factory, version);
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INDEX_IMAGES);
        db.execSQL(CREATE_INDEX_INDEXS);
        Log.d(TAG, "Create succeed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
