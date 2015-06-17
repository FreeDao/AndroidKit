package com.hh.kit.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * HTTP通讯工具
 * 
 * @author
 */
public class HttpChannel {

	private static final String $req_method_post = "POST";
	private static final String $req_method_get = "GET";
	private static final String $property_name_contentType = "Content-Type";
	private static final String $property_value_contentType = "application/x-www-form-urlencoded";
	private static final String $property_name_cache = "Cache-Control";
	private static final String $property_value_cache = "no-cache";

	private static final int _minute = 1000 * 60;
	private static final String $line_feed = System
			.getProperty("line.separator");

	/**
	 * post方式提交请求
	 * 
	 * @param strURL
	 *            . 请求地址
	 * @param argsMap
	 *            . 参数键值对
	 * @return 服务器返回
	 */
	public String doPost(String strURL, Map<String, String> argsMap) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;

		try {
			url = new URL(strURL);
			httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setRequestMethod($req_method_post);
			httpConnection.setRequestProperty($property_name_contentType,
					$property_value_contentType);
			httpConnection.setRequestProperty($property_name_cache,
					$property_value_cache);
			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(_minute);
			httpConnection.setReadTimeout(_minute);

			// 发送请求
			out = httpConnection.getOutputStream();
			byte[] b = this.parseParamMap(argsMap).getBytes();
			out.write(b, 0, b.length);
			out.flush();
			out.close();

			// 接收返回
			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

	public String doPostMethod(String strURL, String requestStr) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;

		try {
			url = new URL(strURL);
			httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setRequestMethod($req_method_post);
			httpConnection.setRequestProperty($property_name_contentType,
					$property_value_contentType);
			httpConnection.setRequestProperty($property_name_cache,
					$property_value_cache);
			httpConnection.setRequestProperty("Content-Length",
					String.valueOf(requestStr.length()));

			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(_minute);
			httpConnection.setReadTimeout(_minute);

			// 发送请求��������
			out = httpConnection.getOutputStream();
			byte[] b = requestStr.getBytes();
			out.write(b, 0, b.length);
			out.flush();
			out.close();

			// 接收回来���շ���
			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
			// Log.error("",strURL + "  无法访问   " + ex.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

	/**
	 * 解析请求参数键值对
	 * 
	 * @param argsMap
	 *            . 请求参数键值对
	 * @return http post请求参数字符串
	 */
	private String parseParamMap(Map<String, String> argsMap) throws Exception {
		StringBuffer sbParams = new StringBuffer("");
		String param = "";
		boolean nofirst = false;

		Iterator<String> itor = argsMap.keySet().iterator();
		while (itor.hasNext()) {
			param = itor.next();
			if (nofirst)
				sbParams.append("&");

			sbParams.append(param);
			sbParams.append("=");
			sbParams.append(argsMap.get(param));

			nofirst = true;
		}
		System.out.println("parseParamMap++" + sbParams.toString());
		return sbParams.toString();
	}

	/**
	 * post方式提交请求
	 * 
	 * @param strURL
	 *            . 请求地址
	 * @param argsMap
	 *            . 参数键值对
	 * @return 服务器返回
	 */
	public String doPostGetRespInfo(String strURL, Map<String, String> argsMap) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		try {
			url = new URL(strURL);
			httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("Content-Length",
					String.valueOf(this.parseParamMap(argsMap).length()));
			httpConnection.setRequestProperty("Cache-Control", "no-cache");
			httpConnection.setRequestProperty("accept", "*/*");

			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(60000);
			httpConnection.setReadTimeout(60000);

			out = httpConnection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			osw.write(this.parseParamMap(argsMap));
			osw.flush();
			osw.close();

			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
			// logger.info(strURL + "  无法访问   " + ex.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null)
					in.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null)
					br.close();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null)
					httpConnection.disconnect();
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

	// 用get方式得到页面html
	// public static String getResponseGet(HashMap <String,String> map,String
	// lasturl,String url,String code,int port){
	// HttpClient client = new HttpClient(new HttpClientParams(),new
	// SimpleHttpConnectionManager(true));
	// HttpMethod method = null;
	// try {
	// client.getHostConfiguration().setHost(url, port, "http");
	// method = getGetMethod(map,lasturl);// 使用POST方式提交数据
	// //
	// client.getHttpConnectionManager().getParams().setConnectionTimeout(Constants.TIMEOUT);
	// client.executeMethod(method);
	// String response =
	// new String(method.getResponseBodyAsString().getBytes(code));
	// method.releaseConnection();
	// return response;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return "none";
	// }finally{
	// try{
	// method.releaseConnection();
	// }catch(Exception fx){
	// }
	// try{
	// ((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown();
	// }catch(Exception fx){
	// }
	// }
	// }

	/**
	 * 
	 * 使用GET方式提交数据
	 * 
	 * @return
	 */

	// private static HttpMethod getGetMethod(HashMap <String,String> map,String
	// lasturl){
	// StringBuffer url=new StringBuffer(lasturl);
	// int i=0;
	// for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
	// String key = (String) keys.next();
	// String value=map.get(key);
	// String fen="&";
	// if(i==0)
	// fen="?";
	// url.append(fen).append(key).append("=").append(value);
	// i++;
	// }
	// GetMethod getMethod=new GetMethod(url.toString());
	// getMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gb2312");
	// return getMethod;
	// }

	public String doDrugsMethod(String strURL, Map<String, String> argsMap) {
		StringBuffer sbReturn = new StringBuffer("");
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;

		try {
			String requestStr = this.parseParamMap(argsMap);
			url = new URL(strURL + "?" + requestStr);
			System.out.println("URL" + strURL + "?"
					+ this.parseParamMap(argsMap));
			httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setRequestMethod($req_method_get);
			httpConnection.setRequestProperty($property_name_contentType,
					$property_value_contentType);
			httpConnection.setRequestProperty($property_name_cache,
					$property_value_cache);
			httpConnection.setRequestProperty("Content-Length",
					String.valueOf(requestStr.length()));

			httpConnection.setDoInput(true);
			httpConnection.setDoOutput(true);
			httpConnection.setConnectTimeout(_minute * 10);
			httpConnection.setReadTimeout(_minute * 10);

			// 发送请求��������
			httpConnection.connect();

			// 接收回来���շ���
			in = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String strRead = "";
			while ((strRead = br.readLine()) != null) {
				sbReturn.append(strRead);
				sbReturn.append($line_feed);
			}
		} catch (Exception ex) {
			// logger.error(strURL + "  无法访问   " + ex.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
			try {
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			} catch (Exception fx) {
				fx.printStackTrace();
			}
		}
		return sbReturn.toString();
	}

}
