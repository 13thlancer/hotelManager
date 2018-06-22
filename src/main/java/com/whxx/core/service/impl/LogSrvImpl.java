package com.whxx.core.service.impl;

import com.whxx.core.dao.OperationLogMapper;
import com.whxx.core.model.OperationLog;
import com.whxx.core.service.LogSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogSrvImpl implements LogSrv {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public List<Map<String, Object>> getOperationLogs(String beginTime, String endTime, String logName, String s) {
        return operationLogMapper.getOperationLogs(beginTime,endTime,logName,s);
    }

    @Override
    public OperationLog selectById(String id) {
        return operationLogMapper.selectById(id);
    }

    @Override
    public void delete() {
        operationLogMapper.delete();
    }
}
