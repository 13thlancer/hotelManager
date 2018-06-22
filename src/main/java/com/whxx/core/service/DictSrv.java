package com.whxx.core.service;

import com.whxx.core.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DictSrv {
	Dict selectById(String dictId);

	Dict selectByPidAndNum(@Param("pid") String pid, @Param("num") String num);

	void addDict(String dictName, String dictValues);

	List<Map<String, Object>> list(String condition);

	void editDict(String dictId, String dictName, String dictValues);

	void delteDict(String dictId);

	List<Dict> selectListByPid(String dictId);
}
