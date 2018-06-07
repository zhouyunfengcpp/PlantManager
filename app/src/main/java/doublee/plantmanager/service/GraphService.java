package doublee.plantmanager.service;

import android.content.Context;

import java.util.Calendar;
import java.util.List;

import doublee.plantmanager.entity.Graph;
import doublee.plantmanager.exception.IllegalTimeDeltaException;
import doublee.plantmanager.exception.IndexNotFoundException;

public interface GraphService {
    /**
     * 按照需要的时间周期获取多组温度、湿度、质量三个指标数据
     * @author zyf
     * @param context
     * @return null 温度、质量、湿度指标
     * @exception doublee.plantmanager.exception.IllegalTimeDeltaException TimeDelta格式错误时抛出
     * @exception doublee.plantmanager.exception.IndexNotFoundException 未找到有相关指标
     */
    Graph listGraphByTimeDelta(Context context)throws IllegalTimeDeltaException, IndexNotFoundException;
}
