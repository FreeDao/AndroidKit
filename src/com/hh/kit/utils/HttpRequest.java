/**************************************************  
 * @ProjectName HttpRequest
 * @Package com.hehu.http
 * @FileName HttpRequest.java
 * @Description 网络请求

 * @author Mr.Gdp  
 * @date 2013-1-18 下午2:09:36
 * @version V1.0  
 * @Copyright Copyright (c) 2013
 **************************************************/
package com.hh.kit.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @ClassName HttpRequest
 * @Description 网络请求
 * @author Mr.Gdp
 * @date 2013-1-18 下午2:09:36
 * 
 */
public class HttpRequest {

	public static boolean sendGetRequest(String path,
			Map<String, String> params, String enc) throws Exception {

		StringBuilder sb = new StringBuilder(path);
		sb.append('?');
		// ?method=save&title=12345678&timelength=26&
		// 迭代Map拼接请求参数
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=')
					.append(URLEncoder.encode(entry.getValue(), enc))
					.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"

		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		if (conn.getResponseCode() == HttpStatus.SC_OK) {
			return true;
		}
		return false;
	}

	public static boolean sendPostRequest(String path,
			Map<String, String> params, String enc) throws Exception {

		// title=dsfdsf&timelength=23&method=save
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			// 迭代Map拼接请求参数
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=')
						.append(URLEncoder.encode(entry.getValue(), enc))
						.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
		}
		byte[] entitydata = sb.toString().getBytes();// 得到实体的二进制数据
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		// 如果通过post提交数据，必须设置允许对外输出数据
		conn.setDoOutput(true);
		// 此两参数必须设置
		// Content-Type: application/x-www-form-urlencoded
		// Content-Length: 38
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == HttpStatus.SC_OK) {
			return true;
		}
		return false;
	}

	// HttpClient组件 SSL HTTPS Cookie
	public static boolean sendRequestFromHttpClient(String path,
			Map<String, String> params, String enc) throws Exception {

		List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				paramPairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		// 得到经过编码过后的实体数据
		UrlEncodedFormEntity entitydata = new UrlEncodedFormEntity(paramPairs,
				enc);
		HttpPost post = new HttpPost(path); // form
		post.setEntity(entitydata);
		DefaultHttpClient client = new DefaultHttpClient(); // 浏览器
		HttpResponse response = client.execute(post);// 执行请求
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return true;
		}
		return false;
	}
}
