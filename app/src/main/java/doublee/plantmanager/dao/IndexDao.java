package doublee.plantmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.entity.Index;
import doublee.plantmanager.helper.PlantManagerHelper;

public class IndexDao {
    private String tablename = "indexs";
    private SQLiteDatabase db;
    private PlantManagerHelper indexHelper;
    private String TAG = "IndexDao";

    public Long insertIndexByTime(Context context, Calendar time, String Humidity, String Temperature, String Quality){
        indexHelper = new PlantManagerHelper(context, null, 1);
        db = indexHelper.getWritableDatabase();
        ContentValues cValue = new ContentValues();
        cValue.put("time", time.toString());
        cValue.put("humility", Humidity.toString());
        cValue.put("temperature", Temperature.toString());
        cValue.put("quality", Quality.toString());
        long res = db.insert(tablename,null, cValue);
        Log.d(TAG, "insertIndexByTime OK");
        return res;
    }

    public List<Index> listLatestIndexs(Context context) {
        indexHelper = new PlantManagerHelper(context, null, 1);
        db = indexHelper.getWritableDatabase();
        List<Index> indexs = null;
        Cursor cursor = db.query(tablename, null, null, null, null, null, "time DESC", "1");
        if (cursor.moveToFirst()) {
            String Humidity = cursor.getString(2);
            String Temperature = cursor.getString(2);
            String Quality = cursor.getString(2);
            Index index = new Index();
            index.setHumiditys(Humidity);
            index.setTemperatures(Temperature);
            index.setQualitys(Quality);
            indexs.add(index);
        }else {
            Log.d(TAG, "listLatestIndexs Error");
        }
        Log.d(TAG, "listLatestIndexs OK");
        return indexs;
    }
}
