package doublee.plantmanager.service.impl;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.dao.ImageDao;
import doublee.plantmanager.entity.Image;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.ImageNotFoundException;
import doublee.plantmanager.service.ImageService;

/**
 * @author zyf
 * @version 1.0
 */
public class ImageServiceImpl implements ImageService{

    private ImageDao imageDao = new ImageDao();
    private String beforeTime = "";
    private String TAG = "ImageServiceImpl";

    public void setBeforeTime(String beforeTime){
        this.beforeTime = beforeTime;
    }

    @Override
    public List<Image> listImageByTimeDelta(Context context) throws IllegalTimeDeltaException, ImageNotFoundException {
        List<Image> images = new ArrayList<>();
        if (beforeTime.equals("latest")){
            images = imageDao.listImageByTimeDelta(context,-3);
            Log.d(TAG, "listImageByTimeDelta latest");
        }else if (beforeTime.equals("1week")){
            images = imageDao.listImageByTimeDelta(context, -7);
            Log.d(TAG, "listImageByTimeDelta 1week");
        }else if (beforeTime.equals("1month")){
            images = imageDao.listImageByTimeDelta(context, -30);
            Log.d(TAG, "listImageByTimeDelta 1month");
        }else if (beforeTime.equals("1year")){
            images = imageDao.listImageByTimeDelta(context, -365);
            Log.d(TAG, "listImageByTimeDelta year");
        }else {
            Log.d(TAG, "listImageByTimeDelta timedelta error");
        }
        return images;
    }

    @Override
    public Long insertImageByTime(Context context, Calendar time, String imagefile) throws IllegalTimeDeltaException {
        long res = imageDao.insertImageByTime(context, time, imagefile);
        Log.d(TAG, "insertImageByTime");
        return res;
    }
}