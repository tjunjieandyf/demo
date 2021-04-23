package com.tang.gaodeapi.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.gaodeapi.dao.WryMapper;

/**
* <p>增加主键</p>  
* <p>Description: </p>  
* @author tangjj
* @date 2021年4月23日  
*/  
@Service
public class AddKeyServiceImpl {
	 @Autowired
	 private WryMapper dao;
	 
	 public void addKey(){
		 List<Map<String, Object>> list = dao.listWry(null);
		 for(Map<String, Object> map:list){
			 map.put("XH", UUID.randomUUID().toString());
			 dao.insert(map);
		 }
	 }
	 
	 public void print() {
		System.out.println(dao.getCount());
	}
}
