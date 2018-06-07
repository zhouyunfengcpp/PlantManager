package doublee.plantmanager.service.impl;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.dao.GraphDao;
import doublee.plantmanager.entity.Graph;
import doublee.plantmanager.entity.Index;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.IndexNotFoundException;
import doublee.plantmanager.service.GraphService;

public class GraphServiceImpl implements GraphService{
    private GraphDao graphDao = new GraphDao();
    private String beforeTime;
    private String TAG = "GraphServiceImpl";

    public void setBeforeTime(String beforeTime){
        this.beforeTime = beforeTime;
    }

    @Override
    public Graph listGraphByTimeDelta(Context context) throws IllegalTimeDeltaException, IndexNotFoundException {
        Graph graph = new Graph();
        if (beforeTime == "latest"){
            graph = graphDao.listGraphDataByTimeDelta(context, -10);
        }else {
            Log.d(TAG, "Graph Error");
        }
        Log.d(TAG, "listGraphByTimeDelta");
        return graph;
    }
}
