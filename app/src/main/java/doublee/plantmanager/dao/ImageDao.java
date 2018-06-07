package doublee.plantmanager.dao;

import android.content.ContentValues;
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

import doublee.plantmanager.entity.Image;
import doublee.plantmanager.helper.PlantManagerHelper;

/**
 * @author zyf
 * @version 1.0
 */
public class ImageDao {
    private String DBname = "PlantManager.db";
    private String tablename = "images";
    private String imagepath = "/data/PlantClient/Image/";
    private SQLiteDatabase db;
    private PlantManagerHelper imageHelper;
    private String TAG = "ImageDao";

    public Long insertImageByTime(Context context, Calendar time, String imagefile){
        imageHelper = new PlantManagerHelper(context, null, 1);
        db = imageHelper.getWritableDatabase();
        ContentValues cValue = new ContentValues();
        cValue.put("time", time.toString());
        cValue.put("imagepath", imagepath+imagefile);
        long res = db.insert("image",null, cValue);
        Log.d(TAG, "insertImageByTime OK");
        db.close();
        return res;
    }

    public List<Image> listImageByTimeDelta(Context context, int timedelta) {
        imageHelper = new PlantManagerHelper(context, null, 1);
        db = imageHelper.getReadableDatabase();
        List<Image> images = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, timedelta);
        Date time = calendar.getTime();
        String timetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        Log.d(TAG, time.toString());
//        Cursor cursor = db.query(tablename, null, "time <= \"" + timetemp + "\"", null, null, null, null);
        Cursor cursor = db.query(tablename, null, null, null, null, null, "time DESC", "10");
        Log.d(TAG, "cursor num="+String.valueOf(cursor.getCount()));
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Log.d(TAG, "i num="+String.valueOf(i));
                long id = cursor.getInt(0);
                String imagepath = cursor.getString(1);
                String imagetimeStr = cursor.getString(2);
                Image image = new Image();
                image.setImagePath(imagepath);
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date imagetime = format.parse(cursor.getString(2));
                    image.setTime(imagetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
                images.add(image);
            }
            Log.d(TAG, "listImageByTimeDelta OK");
        }else {
//            Image image = null;
//            images.add(image);
            Log.d(TAG, "listImageByTimeDelta data null");
        }
        db.close();
        return images;
    }
}
