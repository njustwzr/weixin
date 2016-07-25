package com.imooc.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantsUtil {
	
	
	//Ϊ�˺��ڼ����������ܣ����ڶ�apptoken���й���
	public static Map<String, String> tokenMap=new HashMap<String, String>();
	public static final String KEY_OF_MAP="token";
	public static final String KEY2_OF_MAP="expire";
	public static final long DIFFERENCE_TIME=6800;
	
	
	//APPID �� APPSecret
	public static final String APPID="your appid";
	public static final String APPSECRET="your appsecret";
	//��ȡaccess_token��url��ַ
	public static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
   
	
	/*//΢�ű�������servlet�ĵ�ַ
	public static final String CERTIFICATE_QUERY_URL="http://localhost:8080/jlyw/CommissionSheetServlet.do?method=22";
	public static final String COMMISSIONSHEET_QUERY_URL="http://localhost:8080/jlyw/QueryServlet.do?method=11";*/
	
	//΢��Զ������servlet�ĵ�ַ
	public static final String COMMISSIONSHEET_QUERY_URL="http://127.0.0.1:8080/jlyw/CommissionSheetServlet.do?method=22";
	public static final String CERTIFICATE_QUERY_URL="http://127.0.0.1:8080/jlyw/QueryServlet.do?method=11";
	
	//��̨�������ĵ�ַ
	public static final String SERVER_DOMAIN_NAME="http://127.0.0.1";
	
	//��Ϣ���͵ľ�̬����
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_MUSIC="music";
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	public static final String MESSAGE_SCANCODE="scancode_push";
}
