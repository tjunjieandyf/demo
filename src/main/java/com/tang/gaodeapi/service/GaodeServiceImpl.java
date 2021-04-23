/******************************************************************************
* Copyright (C) 2020 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.tang.gaodeapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.tang.gaodeapi.dao.WryMapper;
import com.tang.gaodeapi.util.HttpClient;
import com.tang.gaodeapi.util.ResponseParseUtil;

/**
 * @Title:高德接口调用代码
 * @author 唐俊杰
 * @since JDK1.8
 * @history 2020年11月26日 唐俊杰 新建
 */
@Service
public class GaodeServiceImpl {

	@Autowired
	private WryMapper dao;

	private final String KEY = "c6cee7ceb33376c35d2f64d9850a340a";
	private final String LOCATION_URL = "https://restapi.amap.com/v3/geocode/geo";
	private final int SIZE = 20;

	/**
	 * 调用高德api，获取经纬度，更新经纬度
	 */
	public void getLocation() {
		List<Map<String, Object>> list = dao.listWry(null);
		if (!CollectionUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				// 优先使用地址
				Map<String, String> param = initMap(MapUtils.getString(map, "DZ"));
				Map<String, Object> responseMap = getResponse(param);
				if (responseMap == null) {
					// 使用地址无效，换成名称请求
					param = initMap(MapUtils.getString(map, "JSDW"));
					responseMap = getResponse(param);
				}

				if (responseMap != null) {
					responseMap.put("XH", MapUtils.getString(map, "XH"));
					dao.updateLocation(responseMap);
				}
			}
		}

	}

	/**
	 * 构造请求参数
	 * 
	 * @param address
	 * @return
	 */
	private Map<String, String> initMap(String address) {
		Map<String, String> result = new HashMap<>();
		result.put("key", KEY);
		if (!StringUtils.isEmpty(address)) {
			address = address.replace('#', '号');
			result.put("address", address);
		}
		result.put("city", "丽水");
		result.put("output", "JSON");
		return result;
	}

	/**
	 * 发起请求，获取返回值
	 * 
	 * @param param
	 * @return
	 */
	private Map<String, Object> getResponse(Map<String, String> param) {
		String url = HttpClient.getNewUrl(LOCATION_URL, param);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String response = HttpClient.get(url);
		// 解析结果
		Map<String, Object> responseMap = ResponseParseUtil.parseResponse(response);
		// 设置污染源编号
		return responseMap;
	}
	
}
