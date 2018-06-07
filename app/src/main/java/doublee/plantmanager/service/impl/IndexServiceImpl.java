package doublee.plantmanager.service.impl;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.dao.IndexDao;
import doublee.plantmanager.entity.Index;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.IndexNotFoundException;
import doublee.plantmanager.service.IndexService;

public class IndexServiceImpl implements IndexService{
    private IndexDao indexDao;
    private String TAG = "IndexServiceImpl";

    @Override
    public List<Index> listLatestIndexs(Context context) throws IllegalTimeDeltaException, IndexNotFoundException {
        List<Index> indexs = null;
        indexs = indexDao.listLatestIndexs(context);
        Log.d(TAG, "listLatestIndexs");
        return indexs;
    }

    @Override
    public long insertIndexsByTime(Context context, Calendar time, String Humidity, String Temperature, String Quality) throws IllegalTimeDeltaException, IndexNotFoundException {
        long res = indexDao.insertIndexByTime(context, time, Humidity, Temperature, Quality);
        Log.d(TAG, "insertIndexByTime");
        return res;
    }
}
