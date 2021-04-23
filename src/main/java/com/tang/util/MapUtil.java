package com.tang.util;

import java.util.Date;
import java.util.Map;

public abstract class MapUtil {

    /**
     * 获取整型值
     * 
     * @param map
     * @param key
     * @return
     */
    public static int getInt(Map<String, Object> map, String key) {
        int result;
        Object value = map.get(key);
        if (value instanceof String) {
            result = Integer.valueOf((String) value);
        } else {
            result = value == null ? 0 : (Integer) value;
        }
        return result;
    }

    /**
     * 获取浮点数值
     * 
     * @param map
     * @param key
     * @return
     */
    public static float getFloat(Map<String, Object> map, String key) {
        float result;
        Object value = map.get(key);
        if (value instanceof String) {
            result = Float.valueOf((String) value);
        } else {
            result = value == null ? 0F : (Float) value;
        }
        return result;
    }

    /**
     * 获取浮点数值
     * 
     * @param map
     * @param key
     * @return
     */
    public static double getDouble(Map<String, Object> map, String key) {
        double result;
        Object value = map.get(key);
        if (value instanceof String) {
            result = Double.valueOf((String) value);
        } else {
            result = value == null ? 0D : (Double) value;
        }
        return result;
    }


    /**
     * 获取string值
     * 
     * @param map
     * @param key
     * @return
     */
    public static String getStr(Map<String, Object> map, String key) {
        return map.get(key) == null ? "" : "" + map.get(key);
    }

    /**
     * 获取布尔值
     * 
     * @param map
     * @param key
     * @return
     */
    public static boolean getBoolean(Map<String, Object> map, String key) {
        boolean result;
        Object value = map.get(key);
        if (value instanceof String) {
            result = Boolean.valueOf((String) value);
        } else {
            result = value == null ? false : (Boolean) value;
        }
        return result;
    }

    /**
     * 获取布尔值
     * 
     * @param map
     * @param key
     * @return
     */
    public static char getChar(Map<String, Object> map, String key) {
        return map.get(key) == null ? null : (Character) map.get(key);
    }

    /**
     * 获取java.utils.date值
     * 
     * @param map
     * @param key
     * @return
     */
    public static Date getUtilDate(Map<String, Object> map, String key) {
        return map.get(key) == null ? null : (Date) map.get(key);
    }

    /**
     * 获取java.utils.date值
     * 
     * @param map
     * @param key
     * @return
     */
    public static java.sql.Timestamp getTimestamp(Map<String, Object> map, String key) {
        return map.get(key) == null ? null : (java.sql.Timestamp) map.get(key);
    }
    
}
