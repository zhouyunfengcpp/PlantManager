package doublee.plantmanager.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import doublee.plantmanager.entity.Graph;
import doublee.plantmanager.helper.PlantManagerHelper;

/**
 * @author zyf
 * @version 1.0
 */
public class GraphDao {
    private String tablename = "indexs";
    private PlantManagerHelper indexHelper;
    private SQLiteDatabase db;
    private String TAG = "GraphDao";

    public Graph listGraphDataByTimeDelta(Context context, int timedelta) {
        indexHelper = new PlantManagerHelper(context, null, 1);
        db = indexHelper.getReadableDatabase();
        Graph graph = new Graph();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, timedelta);
        Date time = calendar.getTime();
        String timetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        List<String> Humiditys = new ArrayList<>();
        List<String> Temperatures = new ArrayList<>();
        List<String> Qualitys = new ArrayList<>();
        List<Date> Times = new ArrayList<>();
//        Cursor cursor = db.query(tablename, null, "time <= \"" + timetemp + "\"", null, null, null, "time DESC", "10");
        Cursor cursor = db.query(tablename, null, null, null, null, null, "time DESC", "10");
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String Humidity = cursor.getString(2);
                String Temperature = cursor.getString(3);
                String Quality = cursor.getString(4);
                Humiditys.add(Humidity);
                Temperatures.add(Temperature);
                Qualitys.add(Quality);
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date graphtime = format.parse(cursor.getString(1));
                    Times.add(graphtime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
            graph.setHumiditys(Humiditys);
            graph.setQualitys(Qualitys);
            graph.setTemperatures(Temperatures);
            graph.setTimes(Times);
            Log.d(TAG, "listGraphDataByTimeDelta OK");
        }else {
            Log.d(TAG, "listGraphDataByTimeDelta 数据库未找到数据/找到0");
        }
        db.close();
        return graph;
    }
}
