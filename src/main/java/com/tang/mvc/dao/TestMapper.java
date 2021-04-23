package com.tang.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
	int insert(Map<String, Object> param);
	int delete(@Param("XH")String xh);
	List<Map<String, Object>> listData();
}
