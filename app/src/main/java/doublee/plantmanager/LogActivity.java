package doublee.plantmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import doublee.plantmanager.controller.ActivityController;
import doublee.plantmanager.entity.Image;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.ImageNotFoundException;
import doublee.plantmanager.view.LogVO;

public class LogActivity extends AppCompatActivity{
    private ActivityController activityController = new ActivityController();
    private String TAG = "LogActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logvo);

        onWeek();
        onMonth();
        onYear();
    }

    public void onWeek(){
        Button week = (Button) findViewById(R.id.week);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread mThread = null;//将MyHandler的引用传给处理线程。
                try {
                    mThread = new MyThread(new MyHandler("1week"));
                    Log.d(TAG, "onWeek mThread OK");
                } catch (IllegalTimeDeltaException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onWeek mThread Error1");
                } catch (ImageNotFoundException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onWeek mThread Error2");
                }
                Thread t = new Thread(mThread);
                t.start();
            }
        });
    }

    public void onMonth(){
        Button week = (Button) findViewById(R.id.month);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread mThread = null;//将MyHandler的引用传给处理线程。
                try {
                    mThread = new MyThread(new MyHandler("1month"));
                    Log.d(TAG, "onMonth mThread OK");
                } catch (IllegalTimeDeltaException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onMonth mThread Error1");
                } catch (ImageNotFoundException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onMonth mThread Error2");
                }
                Thread t = new Thread(mThread);
                t.start();
            }
        });
    }

    public void onYear(){
        Button week = (Button) findViewById(R.id.year);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread mThread = null;//将MyHandler的引用传给处理线程。
                try {
                    mThread = new MyThread(new MyHandler("1year"));
                    Log.d(TAG, "onYear mThread OK");
                } catch (IllegalTimeDeltaException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onYear mThread Error1");
                } catch (ImageNotFoundException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onYear mThread Error2");
                }
                Thread t = new Thread(mThread);
                t.start();
            }
        });
    }

    class MyHandler extends Handler {
        private String beforetime;

        public MyHandler(String beforetime) throws IllegalTimeDeltaException, ImageNotFoundException {
            this.beforetime = beforetime;
        }

        @Override
        //当处理线程中的MyHandler的引用调用sendMessage()将消息传
        //入UI线程中looper对象的中的消息队列后，
        //loop（）方法处理该消息时，会调用handleMessage（Messagemsg）方法。
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result=msg.getData().getString("message");

            LogVO logVO = new LogVO();
            try {
                logVO = activityController.resultLogVO(LogActivity.this, beforetime);
            } catch (IllegalTimeDeltaException e) {
                e.printStackTrace();
            } catch (ImageNotFoundException e) {
                e.printStackTrace();
            }

            List<Image> images = logVO.getImagepahths();
            for (int imageNum=0;imageNum<images.size();imageNum++) {
                String imagepath = images.get(imageNum).getImagePath();
                LinearLayout scroll = (LinearLayout) findViewById(R.id.showlinerlayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
//                ImageView imageView = new ImageView(LogActivity.this);
//                imageView.setLayoutParams(params);
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_black_24dp));
//                String path = Environment.getExternalStorageDirectory() + "/data/PlantClient/Image/" + imagepath;
//                Log.d(TAG, "showImage image1 path="+path);
//                Bitmap bm = BitmapFactory.decodeFile(path);
//                imageView.setImageBitmap(bm);
//                imageView.setImageResource(R.drawable.ic_dashboard_black_24dp);
                TextView textView = new TextView(LogActivity.this);
                textView.setText(imagepath);
                textView.setLayoutParams(params);
                scroll.addView(textView);
            }
        }

    }

    class MyThread implements Runnable{
        MyHandler mHandler;
        //获得UI线程Handler对象的引用
        public MyThread(MyHandler myHandler) {
            this.mHandler=myHandler;
        }

        @Override
        public void run() {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Message msg = prepareMessage("消息机制");
            //将消息传入UI线程的消息队列中
            mHandler.sendMessage(msg);
        }

        private Message prepareMessage(String str){
            Message result=Message.obtain();
            Bundle bundle=new Bundle();
            bundle.putString("message", str);
            result.setData(bundle);
            return result;
        }
    }


}
