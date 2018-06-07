package doublee.plantmanager.service;

import android.content.Context;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.entity.Index;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.IndexNotFoundException;

public interface IndexService {
    /**
     * 按照需要的最新的取当前温度、湿度、质量三个指标数据
     * @author zyf
     * @param context
     * @return null 温度、质量、湿度指标
     * @exception doublee.plantmanager.exception.IllegalTimeDeltaException TimeDelta格式错误时抛出
     * @exception doublee.plantmanager.exception.IndexNotFoundException 未找到有相关指标
     */
    List<Index> listLatestIndexs(Context context) throws IllegalTimeDeltaException, IndexNotFoundException;

    /**
     * 按照时间顺序写入当前温度、湿度、质量三个指标数据
     * @author zyf
     * @param context time, humidity, temperature, quality
     * @return null 温度、质量、湿度指标
     * @exception doublee.plantmanager.exception.IllegalTimeDeltaException TimeDelta格式错误时抛出
     * @exception doublee.plantmanager.exception.IndexNotFoundException 未找到有相关指标
     */
    long insertIndexsByTime(Context context, Calendar time, String Humidity, String Temperature, String Quality) throws IllegalTimeDeltaException, IndexNotFoundException;
}
