package com.geekcattle.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;


public class HttpClientUtil {
	private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

	public static String charset = "UTF-8";
	public static final String SunX509 = "SunX509";
	public static final String JKS = "JKS";
	public static final String PKCS12 = "PKCS12";
	public static final String TLS = "TLS";
	
	public static String doGet(String url) throws UnsupportedEncodingException {
		return doGet(url,null);
	}
	
	public static String doGet(String url, Map<String, String> params) throws UnsupportedEncodingException {
		if(params != null) {
			int i = 1;
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					if( i == 1){
						url +="?"+s+"="+params.get(s);
					}else{
						url +="&"+s+"="+params.get(s);
					}
					i++;
				}
			}
		}
		//url = URLEncoder.encode(url, "utf-8");
		System.out.println("send url="+url);
		HttpMethod method = new GetMethod(url);
		
		return send(method, null);
		
	}
	
	public static String doGetNew(String url, Map<String, String> params) throws UnsupportedEncodingException {
		if(params != null) {
			int i = 1;
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					if( i == 1){
						url +="?"+s+"="+params.get(s);
					}else{
						url +="&"+s+"="+params.get(s);
					}
					i++;
				}
			}
		}
		System.out.println("send url="+url);	
		return sendGet(url);
	}
	
	 /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url ;
            //System.out.println("url="+url + "?" + param);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	public static String doGet(String url, Map<String, String> params, String contentCharset) throws UnsupportedEncodingException {
		if(params != null) {
			int i = 1;
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					if( i == 1){
						url +="?"+s+"="+params.get(s);
					}else{
						url +="&"+s+"="+params.get(s);
					}
					i++;
				}
			}
		}
		HttpMethod method = new GetMethod(url);
		return send(method, contentCharset);
	}
	
	public static String doGetNoRe(String url, Map<String, String> params) throws UnsupportedEncodingException {
		if(params != null) {
			int i = 1;
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					if( i == 1){
						url +="?"+s+"="+params.get(s);
					}else{
						url +="&"+s+"="+params.get(s);
					}
					i++;
				}
			}
		}
		
		return url;
	}
	
	public static String doPost(String url) throws UnsupportedEncodingException {
		return doPost(url,null);
	}
	
	public static String doPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
		PostMethod method = new PostMethod(url);
		if(params != null) {
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					method.addParameter(s, value);
				}
			}
		}
		return send(method, null);
	}
	
	public static String doPost(String url, Map<String, String> params, String contentCharset) throws UnsupportedEncodingException {
		PostMethod method = new PostMethod(url);
		if(params != null) {
			for (String s : params.keySet()) {
				String value = params.get(s);
				if (s!=null && value!=null) {
					method.addParameter(s, value);
				}
			}
		}
		return send(method, contentCharset);
	}
	
	public static String doXmlPost(String urlStr, String xmlStr) {
		byte[] xmlData = xmlStr.getBytes();
		BufferedReader br = null;
		HttpURLConnection urlCon = null;
		try {
			// 获得URL链接
			URL url = new URL(urlStr);
			urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);
			urlCon.setRequestMethod("POST");  
			urlCon.setRequestProperty("Accept-Charset", charset);
			urlCon.setRequestProperty("Content-Type", "text/xml");
			urlCon.setRequestProperty("Content-length",String.valueOf(xmlData.length));
			DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream()); 
			printout.write(xmlData);
			printout.flush();
			printout.close();
			 //读取响应  
            br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String lines;  
            StringBuffer sb = new StringBuffer("");  
            while ((lines = br.readLine()) != null) {  
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines + "\r\n");
            }
            return sb.toString();
		} catch (Exception e) {
			logger.error("HttpClientUtil.doXmlPost exception:", e);
		}
		finally
		{
			try{
				br.close();
			}
			catch(Exception e){}
		}
		return null;
	}
	
	private static String send(HttpMethod method, String contentCharset) {
		if(contentCharset == null || contentCharset.trim().equals("")){
			contentCharset = "UTF-8";
		}
		String rs = "";
        try{
            HttpClient client = new HttpClient();  
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, contentCharset);//设置请求参数编码
            method.getParams().setParameter(HttpMethodParams.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
            client.executeMethod(method);
//            rs = method.getResponseBodyAsString();
            InputStream inputStream = method.getResponseBodyAsStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));  
            StringBuffer sb = new StringBuffer();  
            String str= "";  
            while((str = br.readLine()) != null){  
            	sb.append(str);
            }
            rs = sb.toString();
        }catch(Exception e){
        	e.printStackTrace();
            //logger.error("HttpClientUtil.send exception:", e);
        }finally{
            if(method != null){
                method.releaseConnection();
            }
        }
        return rs;
	}
	
	public static String jsonPost(String url, Map<String, String> params){
		
		try{
			//创建连接  
	        URL addUrl = new URL(url);  
	        HttpURLConnection connection = (HttpURLConnection) addUrl  
	                .openConnection();  
	        connection.setDoOutput(true);  
	        connection.setDoInput(true);  
	        connection.setRequestMethod("POST");  
	        connection.setUseCaches(false);  
	        connection.setInstanceFollowRedirects(true);  
	        connection.setRequestProperty("Content-Type",  
	                "application/x-www-form-urlencoded");  

	        connection.connect();
	        
	        //POST请求  
            DataOutputStream out = new DataOutputStream(  
                    connection.getOutputStream());  
            JSONObject obj = new JSONObject();
            
            for (String key : params.keySet()) {
				String val = params.get(key);
				if (key!=null && val!=null) {
					obj.element(key, val);
				}
			}
            
            out.writeBytes(obj.toString());  
            out.flush();  
            out.close();
            
            //读取响应  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            String lines;  
            StringBuffer sb = new StringBuffer("");  
            while ((lines = reader.readLine()) != null) {  
                lines = new String(lines.getBytes(), "utf-8");  
                sb.append(lines);  
            }
            
            reader.close();  
            // 断开连接  
            connection.disconnect(); 
            return sb.toString();
            
		}catch(Exception ex){
			logger.error("jsonPost error["+url+"_"+params.toString()+"]===>",ex);
		}
		
		return null;
		
	}
	/**
	 * get HttpURLConnection
	 * @param strUrl url地址
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpURLConnection(String strUrl)
			throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		return httpURLConnection;
	}
	
	/**
	 * get HttpsURLConnection
	 * @param strUrl url地址
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String strUrl)
			throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url
				.openConnection();
		return httpsURLConnection;
	}
	
	/**
	 * 获取不带查询串的url
	 * @param strUrl
	 * @return String
	 */
	public static String getURL(String strUrl) {

		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(0, indexOf);
			} 
			
			return strUrl;
		}
		
		return strUrl;
		
	}
	
	/**
	 * 获取查询串
	 * @param strUrl
	 * @return String
	 */
	public static String getQueryString(String strUrl) {
		
		if(null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if(-1 != indexOf) {
				return strUrl.substring(indexOf+1, strUrl.length());
			} 
			
			return "";
		}
		
		return strUrl;
	}
	
	/**
	 * 查询字符串转换成Map<br/>
	 * name1=key1&name2=key2&...
	 * @param queryString
	 * @return
	 */
	public static Map<String, String> queryString2Map(String queryString) {
		if(null == queryString || "".equals(queryString)) {
			return null;
		}
		
		Map<String, String> m = new HashMap<String, String>();
		String[] strArray = queryString.split("&");
		for(int index = 0; index < strArray.length; index++) {
			String pair = strArray[index];
			HttpClientUtil.putMapByPair(pair, m);
		}
		
		return m;
		
	}
	
	/**
	 * 把键值添加至Map<br/>
	 * pair:name=value
	 * @param pair name=value
	 * @param m
	 */
	public static void putMapByPair(String pair, Map<String, String> m) {
		
		if(null == pair || "".equals(pair)) {
			return;
		}
		
		int indexOf = pair.indexOf("=");
		if(-1 != indexOf) {
			String k = pair.substring(0, indexOf);
			String v = pair.substring(indexOf+1, pair.length());
			if(null != k && !"".equals(k)) {
				m.put(k, v);
			}
		} else {
			m.put(pair, "");
		}
	}
	
	/**
	 * BufferedReader转换成String<br/>
	 * 注意:流关闭需要自行处理
	 * @param reader
	 * @return String
	 * @throws IOException
	 */
	public static String bufferedReader2String(BufferedReader reader) throws IOException {
		StringBuffer buf = new StringBuffer();
		String line = null;
		while( (line = reader.readLine()) != null) {
			buf.append(line);
			buf.append("\r\n"); 
		}
				
		return buf.toString();
	}
	
	/**
	 * 处理输出<br/>
	 * 注意:流关闭需要自行处理
	 * @param out
	 * @param data
	 * @param len
	 * @throws IOException
	 */
	public static void doOutput(OutputStream out, byte[] data, int len)
			throws IOException {
		int dataLen = data.length;
		int off = 0;
		while (off < data.length) {
			if (len >= dataLen) {
				out.write(data, off, dataLen);
				off += dataLen;
			} else {
				out.write(data, off, len);
				off += len;
				dataLen -= len;
			}

			// 刷新缓冲区
			out.flush();
		}

	}
	
	/**
	 * 获取SSLContext
	 * @param trustFile 
	 * @param trustPasswd
	 * @param keyFile
	 * @param keyPasswd
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws UnrecoverableKeyException 
	 * @throws KeyManagementException 
	 */
	public static SSLContext getSSLContext(
			FileInputStream trustFileInputStream, String trustPasswd,
			FileInputStream keyFileInputStream, String keyPasswd)
			throws NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException, UnrecoverableKeyException,
			KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.JKS);
		trustKeyStore.load(trustFileInputStream, HttpClientUtil
				.str2CharArray(trustPasswd));
		tmf.init(trustKeyStore);

		final char[] kp = HttpClientUtil.str2CharArray(keyPasswd);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
		ks.load(keyFileInputStream, kp);
		kmf.init(ks, kp);

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}
	
	/**
	 * 获取CA证书信息
	 * @param cafile CA证书文件
	 * @return Certificate
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static Certificate getCertificate(File cafile)
			throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(cafile);
		Certificate cert = cf.generateCertificate(in);
		in.close();
		return cert;
	}
	
	/**
	 * 字符串转换成char数组
	 * @param str
	 * @return char[]
	 */
	public static char[] str2CharArray(String str) {
		if(null == str) return null;
		
		return str.toCharArray();
	}
	
	/**
	 * 存储ca证书成JKS格式
	 * @param cert
	 * @param alias
	 * @param password
	 * @param out
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static void storeCACert(Certificate cert, String alias,
			String password, OutputStream out) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		// store keystore
		ks.store(out, HttpClientUtil.str2CharArray(password));

	}
	
	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	/**
	 * InputStream转换成Byte
	 * 注意:流关闭需要自行处理
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException{  
		
		int BUFFER_SIZE = 4096;  
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        byte[] outByte = outStream.toByteArray();
        outStream.close();
        
        return outByte;  
    } 
	
	/**
	 * InputStream转换成String
	 * 注意:流关闭需要自行处理
	 * @param in
	 * @param encoding 编码
	 * @return String
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in,String encoding) throws IOException{
        return new String(InputStreamTOByte(in),encoding);
    }
	
//	public static void main(String args[]){
//		String url="http://192.168.40.126:9107/bmspay/outnet/funsionNotify.action";
//		Map<String, String> params=new HashMap<String, String>();
//		params.put("mobile", "15821211085");
//		// 合作方订单号
//		params.put("orderno", "20150120002");
//		// 视讯订单号
//		params.put("logno", "201501203200523488");
//		// 操作类型 1:订购  3:退订
//		params.put("state", "3");
//		// 运营商类型:1:移动  2:电信  3:联通
//		params.put("optype", "1");
//		
//		params.put("result", "DELIVRD");
//		params.put("sendtime", "20150120110000");
//		params.put("identityid", "");
//		params.put("pid", "59450");
//		params.put("amount", "1500");		
//		
////		String url="http://127.0.0.1/bms_paymentservice/service/BmsTradeBankOrderUnsub.do?";
////		Map<String, String> params=new HashMap<String, String>();
////		params.put("txnid", "201411170100010857"); // 视讯订单号
////		params.put("parenttxnid", "00000129799900001");  // SDK平台订单唯一标识
////		params.put("balancedate", "20141117"); // 交易日期
////		params.put("banktype", BmsPayConstant.BANK_TYPE_OUTNET_CM);
////		params.put("payway", BmsPayConstant.PAY_WAY_OUTNET_PAY);
////		params.put("ip", "127.0.0.1");
////		params.put("plat", "PC");
////		params.put("area", "00");
//		
////		String url="http://127.0.0.1/bms_pay/tenpay/weixinNotify.action";
////		Map<String, String> params=new HashMap<String, String>();
////		params.put("transport_fee", "0");
////		params.put("trade_state", "0");
////		params.put("trade_mode", "1");
////		params.put("sign_type", "MD5");
////		params.put("input_charset", "UTF-8");
////		params.put("fee_type", "1");
////		params.put("out_trade_no", "201411250100010916");
////		params.put("transaction_id", "1222088901201411256087838437");
////		params.put("discount", "0");
////		params.put("sign", "D16E6DFCEE1E37C6769212E96F87D1BC");
////		params.put("total_fee", "1");
////		params.put("time_end", "20141125112516");
////		params.put("partner", "1222088901");
////		params.put("notify_id", "RXsEnuCXdx1dnfbxGUXgwE1SW-uoGp0wmqiym_F5ooaDgc4nmiZcU_2IfNBOlWZwdeLAvVfICh_21tPJLUmrYSu1iLIcXhI8");
////		params.put("bank_type", "2011");
////		params.put("product_fee", "1");
//		
//		String resp=null;
//		try {
////			url=HttpClientUtil.generationPaymentUrl(url,params);
//			resp = HttpClientUtil.doPost(url, params);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(resp);
//	}
	
//	private static final String APPLICATION_JSON = "application/json";
    
//    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

//    public static void httpPostWithJSON(String url, String json) throws Exception {
//        // 将JSON进行UTF-8编码,以便传输中文
//        String encoderJson = URLEncoder.encode(json, "UTF-8");
//        
//        DefaultHttpClient  httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
//        
//        StringEntity se = new StringEntity(encoderJson);
//        se.setContentType(CONTENT_TYPE_TEXT_JSON);
//        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
//        httpPost.setEntity(se);
//        httpClient.execute( httpPost);
//    }
	
//	public static void main(String[] args) {
//		try{
//			OrientOrderSyncRequest req = new OrientOrderSyncRequest();
//			req.setCode("0");
//			req.setMsg("成功");
//			BmsOrientOrder temp = new BmsOrientOrder();
//			temp.setMobile("18964831085");
//			temp.setOrderType("1");
//			temp.setProdOfferNbr("7360110000100018");
//			req.getObj().add(temp);
//			for(int i =0; i <1000;i++)
//			{
//				temp = new BmsOrientOrder();
//				temp.setMobile("18964831086");
//				temp.setOrderType("2");
//				temp.setProdOfferNbr("7360110000100018");
//				req.getObj().add(temp);
//			}
//			System.out.println(1);
//			JSONObject aaa = JSONObject.fromObject(req);
//			httpPostWithJSON("http://127.0.0.1:8080/bms_open/orientOrderSyn.action", aaa.toString());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
}
