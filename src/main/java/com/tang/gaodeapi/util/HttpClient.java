package com.tang.gaodeapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.springframework.util.CollectionUtils;



public class HttpClient {
	
	/**  
	 * <p>Title: getNewUrl</p>  
	 * <p>Description:组装url </p>  
	 * @param url
	 * @param record
	 * @return  
	 */  
	public static String getNewUrl(String url,Map<String, String> record){
		StringBuffer sBuffer = new StringBuffer(url);
		if(record!=null&&!record.isEmpty()){
			sBuffer.append("?");
			for(Entry<String, String> entry:record.entrySet()){
				sBuffer.append(entry.getKey()+"="+entry.getValue()+"&");
			}
			sBuffer.deleteCharAt(sBuffer.length()-1);
		}
		return sBuffer.toString();
	}
    
    /**  
     * <p>Title: get</p>  
     * <p>Description: get请求 </p>  
     * @param headerMap 请求头
     * @param httpurl 请求地址
     * @return  
     */  
    public static String get(Map<String, String> headerMap,String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        // 返回结果字符串
        String result = null;
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");  
            //设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000); 
            //设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000); 
            //设置请求头
            if(!CollectionUtils.isEmpty(headerMap)){
            	for(Entry<String, String> entry:headerMap.entrySet()){
            		connection.setRequestProperty(entry.getKey(), entry.getValue());
            	}
            }
            
            connection.connect();// 发送请求
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer(); 
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return result;
    }
    
    
    /**  
     * <p>Title: get</p>  
     * <p>Description: </p>  
     * @param httpurl 请求地址
     * @return  
     */  
    public static String get(String httpurl){
    	return get(null, httpurl);
    }
    
    
    /**  
     * <p>Title: post</p>  
     * <p>Description: </p>  
     * @param httpUrl 请求地址
     * @param param 请求参数
     * @return  
     */  
    public static String post(Map<String, String> headerMap,String httpUrl, Map<String, Object> paramMap) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            if(CollectionUtils.isEmpty(headerMap)){
            	for(Entry<String, String> entry:headerMap.entrySet()){
            		connection.setRequestProperty(entry.getKey(),entry.getValue());
            	}
            	
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            if(CollectionUtils.isEmpty(paramMap)){
            	StringBuilder sBuilder = new StringBuilder("{");
            	for(String keyStr:paramMap.keySet()){
            		if(paramMap.get(keyStr)!=null&&paramMap.get(keyStr) instanceof String){
            			sBuilder.append("\""+keyStr+"\":\""+MapUtils.getString(paramMap, keyStr)+"\",");
            		}else{
            			sBuilder.append("\""+keyStr+"\":"+MapUtils.getString(paramMap, keyStr)+",");
            		}
            		
            	}
            	sBuilder.deleteCharAt(sBuilder.length()-1);
            	sBuilder.append("}");
            	System.out.println(sBuilder.toString());
            	os.write(sBuilder.toString().getBytes());
            }
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
    
    public static String post(String httpUrl, Map<String, Object> paramMap) {
    	Map<String, String> headerMap = new HashMap<String, String>();
    	headerMap.put("Content-Type", "application/json");
    	return post(headerMap, httpUrl, paramMap);
    }
}