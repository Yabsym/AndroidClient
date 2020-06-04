package com.riskm.androidclient.entity;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Log {
    private Integer logID;
    private String context;
    private String operator;
    private Date time;
    private String type;

    public Log(Integer logID, String context, String operator, Date time, String type) {
        this.logID = logID;
        this.context = context;
        this.operator = operator;
        this.time = time;
        this.type = type;
    }

    public static List<Log> covertLog(List<String> data) {
        List<Log> retValue = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (String dat :
                data) {
            Map map = JSON.parseObject(dat);
            Date date = new Date(Long.parseLong(map.get("time").toString()));
            retValue.add(new Log(Integer.parseInt(map.get("logID").toString()),
                    map.get("context").toString(), map.get("operator").toString(),
                    date, map.get("type").toString()));
        }
        return retValue;
    }

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
