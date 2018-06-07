package doublee.plantmanager.controller;

import android.content.Context;
import android.util.Log;

import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.ImageNotFoundException;
import doublee.plantmanager.exception.IndexNotFoundException;
import doublee.plantmanager.service.impl.GraphServiceImpl;
import doublee.plantmanager.service.impl.ImageServiceImpl;
import doublee.plantmanager.service.impl.IndexServiceImpl;
import doublee.plantmanager.view.LogVO;
import doublee.plantmanager.view.MainVO;

public class ActivityController {
    public GraphServiceImpl graphService = new GraphServiceImpl();
    public ImageServiceImpl imageService = new ImageServiceImpl();
    public IndexServiceImpl indexService = new IndexServiceImpl();

    private String TAG = "ActivityController";

    // 处理主界面的VO
    public MainVO resultMainVO(Context context) throws IllegalTimeDeltaException, IndexNotFoundException, ImageNotFoundException {
        MainVO mainVO = new MainVO();
        // 处理Graph，只需要latest
        graphService.setBeforeTime("latest");
        mainVO.setGraph(graphService.listGraphByTimeDelta(context));
        // 处理Image，只需要latest
        imageService.setBeforeTime("latest");
        mainVO.setImage(imageService.listImageByTimeDelta(context));
        Log.d(TAG, "resultMainVO");
        return mainVO;
    }

    // 处理图片日志页面
    public LogVO resultLogVO(Context context, String beforetime) throws IllegalTimeDeltaException, ImageNotFoundException {
        LogVO logVO = new LogVO();
        // 处理image, 按照不同的日期进行异步处理
        imageService.setBeforeTime(beforetime);
        logVO.setImagepahths(imageService.listImageByTimeDelta(context));
        return logVO;
    }
}
