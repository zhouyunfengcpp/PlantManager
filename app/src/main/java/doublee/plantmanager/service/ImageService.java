package doublee.plantmanager.service;

import android.content.Context;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.entity.Image;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.ImageNotFoundException;

public interface ImageService {
    /**
     * 按照需要的时间周期获取当前的照片路径
     * @author zyf
     * @param context timedelta
     * @return null 照片路径
     * @exception doublee.plantmanager.exception.IllegalTimeDeltaException TimeDelta格式错误时抛出
     * @exception doublee.plantmanager.exception.ImageNotFoundException 未找到有照片
     */
    List<Image> listImageByTimeDelta(Context context) throws IllegalTimeDeltaException, ImageNotFoundException;

    /**
     * 获取到图片就进行写入
     * @author zyf
     * @param context time
     * @return null 照片路径
     * @exception doublee.plantmanager.exception.IllegalTimeDeltaException Time格式错误时抛出
     */
    Long insertImageByTime(Context context, Calendar time, String imagefile) throws IllegalTimeDeltaException, ImageNotFoundException;
}
