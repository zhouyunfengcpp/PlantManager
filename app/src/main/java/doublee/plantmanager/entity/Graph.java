package doublee.plantmanager.entity;

import android.util.Log;

import java.util.Date;
import java.util.List;

public class Graph {
    public List<Date> Times;
    public List<String> Humiditys;
    public List<String> Temperatures;
    public List<String> Qualitys;

    public List<Date> getTimes() {
//        Log.d("getTimes", Times.get(0).toString());
        return Times;
    }

    public void setTimes(List<Date> times) {
        this.Times = times;
    }

    public List<String> getHumiditys() {
        return Humiditys;
    }

    public void setHumiditys(List<String> humiditys) {
        this.Humiditys = humiditys;
    }

    public List<String> getTemperatures() {
        return Temperatures;
    }

    public void setTemperatures(List<String> temperatures) {
        this.Temperatures = temperatures;
    }

    public List<String> getQualitys() {
        return Qualitys;
    }

    public void setQualitys(List<String> qualitys) {
        this.Qualitys = qualitys;
    }
}
