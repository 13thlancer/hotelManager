package com.whxx.core.service;

import com.whxx.core.model.OperationLog;

import java.util.List;
import java.util.Map;

public interface LogSrv {
    List<Map<String,Object>> getOperationLogs(String beginTime, String endTime, String logName, String s);

    OperationLog selectById(String id);

    void delete();
}
