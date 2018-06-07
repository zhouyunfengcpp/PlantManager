package doublee.plantmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import doublee.plantmanager.controller.ActivityController;
import doublee.plantmanager.entity.Graph;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.ImageNotFoundException;
import doublee.plantmanager.exception.IndexNotFoundException;
import doublee.plantmanager.view.MainVO;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {
    private ActivityController activityController = new ActivityController();
    private String TAG = "MainActivity";

//    private PlantManagerHelper indexHelper = new PlantManagerHelper(this, "PlantManager.db", null, 1);
//    private SQLiteDatabase tpdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainvo);

//        tpdb = indexHelper.getReadableDatabase();
//        try {
//            tpdb.execSQL("DROP TABLE indexs");
//        }catch (SQLException e){ }
//        try {
//            tpdb.execSQL("DROP TABLE images");
//        }catch (SQLException e){ }
//        tpdb.close();


        Log.d(TAG, "Start MainActivity");
        MainVO mainVo = new MainVO();

        try {
            mainVo = activityController.resultMainVO(this);
            if (mainVo.getGraph().getTimes()==null){
                Log.d(TAG, "activityController.resultMainVO null");
            }else {
                Log.d(TAG, "activityController.resultMainVO succeed");
            }
        } catch (IllegalTimeDeltaException e) {
            Log.d(TAG, "IllegalTimeDeltaException");
            e.printStackTrace();
        } catch (IndexNotFoundException e) {
            Log.d(TAG, "IndexNotFoundException");
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "mainVo OK");

        showGraph(mainVo);
        showImage(mainVo);

        onHelp();
        onSetting();
        onLog();
        onBaike();
        onOther();
        onTempData();
    }

    public void onHelp(){
        Button Help = (Button) findViewById(R.id.help);
        Help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onHelp OK");
    }

    public void onSetting(){
        Button Setting = (Button) findViewById(R.id.setting);
        Setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onSetting OK");
    }

    public void onLog(){
        Button Logg = (Button) findViewById(R.id.log);
        Logg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onLog OK");
    }

    public void onBaike(){
        Button Baike = (Button) findViewById(R.id.baike);
        Baike.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaikeActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onBaike OK");
    }

    public void onOther(){
        Button Other = (Button) findViewById(R.id.other);
        Other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onOther OK");
    }

    public void onTempData(){
        Button TempData = (Button) findViewById(R.id.temp);
        TempData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TempDataActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "onTempData OK");
    }

    // Graph 生成函数
    public void lineChartViewFunction(List<String> attrs, LineChartView attrChart, String chartName) {
        if (attrs != null) {
            Log.d(TAG, String.valueOf(attrs.get(0)));
            List<PointValue> valuesHumiditys = new ArrayList<PointValue>();
            for (int i = 0; i <= attrs.size() - 1; i++) {
                PointValue pointValueTemp = new PointValue(i, Integer.valueOf(attrs.get(i)));
                valuesHumiditys.add(pointValueTemp);
            }
            Line lineHumiditys = new Line(valuesHumiditys);
            //        lineHumiditys.setColor(getColor());
            lineHumiditys.setCubic(false);
            lineHumiditys.setFilled(false);
            lineHumiditys.setShape(ValueShape.CIRCLE);
            lineHumiditys.setColor(Color.RED);
            //        lineHumiditys.hasLines(true);
            //        lineHumiditys.hasPoints(true);
            //        lineHumiditys.hasLabelsOnlyForSelected(true);
            //        lineHumiditys.hasLabels(true);
            List<Line> linesHumiditys = new ArrayList<Line>();
            linesHumiditys.add(lineHumiditys);
            // 绘图
            LineChartData linesHumiditysChartData = new LineChartData(linesHumiditys);
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            axisX.setName(chartName);
            axisY.setName("");
            //        axisX.setTextColor(ContextCompat.getColor(MainActivity.this, Color.RED));
            //        axisY.setTextColor(whiteC);
            //        axisY.setLineColor(whiteColor);
            //        axisX.setLineColor(whiteColor);
            linesHumiditysChartData.setAxisXBottom(axisX);
            linesHumiditysChartData.setAxisYLeft(axisY);
            linesHumiditysChartData.setBaseValue(Float.NEGATIVE_INFINITY);
            attrChart.setLineChartData(linesHumiditysChartData);
            Log.d(TAG, chartName+" OK");
        } else {
            Log.d(TAG, chartName+" Data error");
        }
    }

    // 展示湿度、温度、质量的方法
    public void showGraph(MainVO mainVO){
        Log.d(TAG, "showGraph");

        Graph graph = mainVO.getGraph();
        List<String> Humiditys = graph.getHumiditys();
        List<String> Temperatures = graph.getTemperatures();
        List<String> Qualitys = graph.getQualitys();

        LineChartView chartHumiditys = (LineChartView) findViewById(R.id.humidity);
        LineChartView chartTemperatures = (LineChartView) findViewById(R.id.temperature);
        LineChartView chartQualitys = (LineChartView) findViewById(R.id.quality);


        lineChartViewFunction(Humiditys, chartHumiditys, "近10天湿度");
        lineChartViewFunction(Temperatures, chartTemperatures, "近10天温度");
        lineChartViewFunction(Qualitys, chartQualitys, "近10天质量");
    }

    public void showImage(MainVO mainVO){
        ImageView image1 = new ImageView(this);
        ImageView image2 = new ImageView(this);
        ImageView image3 = new ImageView(this);
//        if (mainVO.getImages().get(0) != null){
        if (mainVO.getImages().size() != 0){
            image1.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_black_24dp));
            String path = Environment.getExternalStorageDirectory() + "/data/PlantClient/Image/" + mainVO.getImages().get(0);
            Log.d(TAG, "showImage image1 path="+path);
            Bitmap bm = BitmapFactory.decodeFile(path);
            image1.setImageBitmap(bm);
            image1.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }else {
            Log.d(TAG, "Image1 Data error");
        }
    }
}
