import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.geekcattle.util.HttpClientUtil;


public class test {
	public static void main(String[] args) {
		String url="http://localhost:9966/global_task_service/global/service/task/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("server","192.168.23.216");
		params.put("time", "2014999999999");
		params.put("heartInfo", "[{\"name\":\"bylls71236\",\"flowStatus\":1,\"userNumber\":0}]");
		//params.put("heartInfo", "[]");
		params.put("remark","test");
		params.put("isHeart", "2");	
		// 发送请求
		System.out.println("srs心跳请求="+url + " 我srsheart");
		String doGet=null;
		try {
			doGet = HttpClientUtil.doPost(url + "srsheart", params);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("后台服务心跳返回的结果=" + doGet);
	}
}
