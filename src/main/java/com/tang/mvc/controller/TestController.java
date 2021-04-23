package com.tang.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tang.gaodeapi.service.AddKeyServiceImpl;
import com.tang.gaodeapi.service.GaodeServiceImpl;
import com.tang.mvc.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestService service;
	
	@Autowired
	private AddKeyServiceImpl addService;
	
	@Autowired
	private GaodeServiceImpl gaoService;
	
	@RequestMapping("/index")
	public String getUser(){
		System.out.println("dataservice/");
		return "html/index";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public void add(){
		addService.addKey();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public void update(){
		gaoService.getLocation();
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insert(@RequestBody Map<String, Object> param){
		service.insert(param);
	}
	
	@RequestMapping(value="/delete/{xh}",method=RequestMethod.GET)
	public void delete(@PathVariable(value="xh")String xh){
		service.delete(xh);
	}
	
	@RequestMapping(value="/listData",method=RequestMethod.GET)
	public Map<String, Object> listData(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = service.listData();
		result.put("list", list);
		return result;
	}
}
