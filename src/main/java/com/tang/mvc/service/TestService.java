package com.tang.mvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.mvc.dao.TestMapper;

@Service
public class TestService {
	@Autowired
	private TestMapper dao;
	
	public int insert(Map<String, Object> param){
		return dao.insert(param);
	}
	
	public int delete(String xh){
		return dao.delete(xh);
	}
	
	public List<Map<String, Object>> listData(){
		return dao.listData();
	}
}
