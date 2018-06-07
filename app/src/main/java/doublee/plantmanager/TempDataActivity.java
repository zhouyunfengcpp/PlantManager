package doublee.plantmanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import doublee.plantmanager.helper.PlantManagerHelper;

public class TempDataActivity extends AppCompatActivity{
    private PlantManagerHelper indexHelper = new PlantManagerHelper(this, null, 1);
    private SQLiteDatabase db;
    private String TAG = "TempDataActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "TempDataActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tempdataactivity);
        Intent intent = getIntent();
        Log.d(TAG, "TempDataActivity onCreate");

        TextView showGraphData = (TextView) findViewById(R.id.showgraphdata);
        String numGraph = "GraphDataNum="+String.valueOf(listGraphNumData());
        showGraphData.setText(numGraph);
        insertGraphData();

        TextView showImageData = (TextView) findViewById(R.id.showimagedata);
        String numImage = "ImageDataNum="+String.valueOf(listImageNumData());
        showImageData.setText(numImage);
        insertImageData();

        drop();
    }

    public int listGraphNumData() {
        db = indexHelper.getReadableDatabase();
        int num = 0;
        Cursor cursor = db.query("indexs", null, null, null, null, null, "time DESC");
        if (cursor.moveToFirst()) {
            num = cursor.getCount();
            Log.d(TAG, "listGraphData OK num="+String.valueOf(num));
        }else {
            Log.d(TAG, "listGraphNumData error");
        }
        return num;
    }

    public void insertGraphData(){
        Button button = (Button) findViewById(R.id.insertgraphdata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = indexHelper.getWritableDatabase();
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                String timetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
                ContentValues cValue = new ContentValues();
                cValue.put("time", timetemp);
                cValue.put("humidity", "111");
                cValue.put("temperature", "222");
                cValue.put("quality", "333");
                long res = db.insert("indexs",null, cValue);
                Log.d(TAG, "insertGraphData OK");
            }
        });
    }

    public int listImageNumData() {
        db = indexHelper.getReadableDatabase();
        int num = 0;
        Cursor cursor = db.query("images", null, null, null, null, null, "time DESC");
        if (cursor.moveToFirst()) {
            num = cursor.getCount();
            Log.d(TAG, "listImageData OK num="+String.valueOf(num));
        }else {
            Log.d(TAG, "listImageNumData error");
        }
        return num;
    }

    public void insertImageData(){
        Button button = (Button) findViewById(R.id.insertimagedata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = indexHelper.getWritableDatabase();
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                String timetemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
                ContentValues cValue = new ContentValues();
                cValue.put("time", timetemp);
                cValue.put("imagepath", "/data/PlantClient/Image/123.jpg");
                long res = db.insert("images",null, cValue);
                Log.d(TAG, "insertImageData OK");
            }
        });
    }

    public void drop(){
        Button drop = (Button) findViewById(R.id.drop);
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = indexHelper.getWritableDatabase();
                try {
                    db.delete("indexs", null, null);
                    Log.d(TAG, "drop table indexs OK");
                }catch (SQLException e){
                    Intent intent = new Intent(TempDataActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                try {
                    db.delete("images", null, null);
                    Log.d(TAG, "drop table images OK");
                }catch (SQLException e){
                    Intent intent = new Intent(TempDataActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
