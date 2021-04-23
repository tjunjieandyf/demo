/******************************************************************************
* Copyright (C) 2020 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.tang.gaodeapi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



/**
* @Title:
* @author  唐俊杰
* @since   JDK1.8
* @history 2020年11月26日 唐俊杰 新建
*/
public class ResponseParseUtil {
    /**
     * 高德location api调用结果解析
     * @param response
     * @return
     */
    public static Map<String, Object> parseResponse(String response){
        Map<String, Object> result = null;
        if(StringUtils.isEmpty(response)){
            return result;
        }
        JSONObject responseObj = JSONObject.parseObject(response);
        if("1".equals(responseObj.getString("status"))){
            //请求成功
            String geocodeStr = responseObj.getString("geocodes");
            if(StringUtils.isEmpty(geocodeStr)){
                return result;
            }
            JSONArray geocodes = JSONArray.parseArray(geocodeStr);
            if(geocodes.size()==0){
                return result;
            }
            JSONObject fristObj = geocodes.getJSONObject(0);
            if(fristObj!=null){
                String locationStr = fristObj.getString("location");
                if(StringUtils.isEmpty(locationStr)){
                    return result;
                }
                String [] locationArr = locationStr.split(",");
                if(locationArr.length == 2){
                    result = new HashMap<String, Object>();
                    result.put("JD", locationArr[0]);
                    result.put("WD", locationArr[1]);
                    result.put("MARK", "1");
                }
            }
        }
        
        return result;
    }
}
