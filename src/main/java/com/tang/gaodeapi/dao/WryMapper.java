/******************************************************************************
* Copyright (C) 2020 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.tang.gaodeapi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
* @Title:
* @author  唐俊杰
* @since   JDK1.8
* @history 2020年11月26日 唐俊杰 新建
*/
@Repository
public interface WryMapper {
    int getCount();
    List<Map<String, Object>> listWry(Map<String, Object> param);
    int updateLocation(Map<String, Object> param);
    int insert(Map<String, Object> param); 
}
