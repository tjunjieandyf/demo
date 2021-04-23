package com.tang.tianyancha;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
* <p>天眼查接口调用代码</p>  
* <p>Description: </p>  
* @author tangjj
* @date 2021年4月23日  
*/  
public class HttpClientUtillsByTYC {  
    private static Logger logger = LoggerFactory  
            .getLogger(HttpClientUtillsByTYC.class); // 日志记录  
  
      
    /** 
     * post请求传输json参数 
     *  
     * @param url 
     *            url地址 
     *            参数
     * @return 
     */  
    public static JSONObject httpPost(String url, JSONObject jsonParam) {  
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        JSONObject jsonResult = null;  
        HttpPost httpPost = new HttpPost(url);  
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(2000).setConnectTimeout(2000).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            if (null != jsonParam) {  
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(jsonParam.toString(),  
                        "utf-8");  
                entity.setContentEncoding("UTF-8");  
                entity.setContentType("application/json");  
                httpPost.setEntity(entity);  
            }  
            CloseableHttpResponse result = httpClient.execute(httpPost);  
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                String str = "";  
                try {  
                    //读取服务器返回过来的json字符串数据   
                    str = EntityUtils.toString(result.getEntity(), "utf-8");  
                    //把json字符串转换成json对象   
                    jsonResult = JSONObject.parseObject(str);  
                } catch (Exception e) {  
                    logger.error("post请求提交失败:" + url, e);  
                }  
            }  
        } catch (IOException e) {  
            logger.error("post请求提交失败:" + url, e);  
        } finally {  
            httpPost.releaseConnection();  
        }  
        return jsonResult;  
    }  
      
      
    /** 
     * post请求传输String参数 
     * 例如：name=Jack&sex=1&type=2 
     * Content-type:application/x-www-form-urlencoded 
     * @param url 
     *            url地址 
     * @param strParam 
     *            参数 
     *            不需要返回结果
     * @return 
     */  
    public static String httpPost(String url, String strParam) {  
        // post请求返回结果  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String res = "";
        HttpPost httpPost = new HttpPost(url);  
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(200000).setConnectTimeout(200000).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            if (null != strParam) {  
                // 解决中文乱码问题  
                StringEntity entity = new StringEntity(strParam,"utf-8");  
                entity.setContentEncoding("UTF-8");  
                entity.setContentType("application/json");  
                httpPost.setEntity(entity);  
            }  
            CloseableHttpResponse result = httpClient.execute(httpPost);  
            //请求发送成功，并得到响应  
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                String str = "";  
                try {  
                    //读取服务器返回过来的json字符串数据  
                    str = EntityUtils.toString(result.getEntity(), "utf-8");  
                    //把json字符串转换成json对象  
                    res = str;
                } catch (Exception e) {  
                    logger.error("post请求提交失败:" + url, e);  
                }  
            }  
        } catch (IOException e) {  
            logger.error("post请求提交失败:" + url, e);  
        } finally {  
            httpPost.releaseConnection();  
        }  
        return res;  
    }  
  
    /** 
     * 发送get请求 
     *  
     * @param url 
     *            路径 
     * @return 
     */  
    public static JSONObject httpGet(String url) {  
        // get请求返回结果  
        JSONObject jsonResult = null;  
        CloseableHttpClient client = HttpClients.createDefault();  
        // 发送get请求  
        HttpGet request = new HttpGet(url);  
        // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(6000).setConnectTimeout(6000).build();  
        request.setConfig(requestConfig); 
         request.setHeader("Authorization","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");//token值
        try {  
            CloseableHttpResponse response = client.execute(request);  
  
            //请求发送成功，并得到响应  
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                //读取服务器返回过来的json字符串数据  
                HttpEntity entity = response.getEntity();  
                String strResult = EntityUtils.toString(entity, "utf-8");  
                //把json字符串转换成json对象  
                jsonResult = JSONObject.parseObject(strResult);  
            } else {  
                logger.error("get请求提交失败:" + url);  
            }  
        } catch (IOException e) {  
            logger.error("get请求提交失败:" + url, e);  
        } finally {  
            request.releaseConnection();  
        }  
        return jsonResult;  
    }  
      
    public static void main(String[] args) {
        JSONObject httpGet = httpGet("http://open.api.tianyancha.com/services/v4/open/baseinfo?name=平安银行股份有限公司");
        System.out.println(httpGet);
    }  
}