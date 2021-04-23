package com.tang.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tang.application.DemoApplication;
import com.tang.gaodeapi.service.AddKeyServiceImpl;

/**
* <p>Title: 测试类</p>  
* <p>Description: </p>  
* @author tangjj
* @date 2021年4月23日  
*/  
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class DemoApplicationTests {
	
	@Autowired
	private AddKeyServiceImpl serviceImpl;
	
	public DemoApplicationTests(){
		
	}
	
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void addKey() {
		serviceImpl.print();
	}
}
