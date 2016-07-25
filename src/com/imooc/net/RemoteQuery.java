package com.imooc.net;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.imooc.util.ConstantsUtil;
import com.imooc.util.MessageUtil;

import net.sf.json.JSONObject;

public class RemoteQuery {
	
	public static String getCommissionsheetStautsMessage(String toUserName,String fromUserName,String content)
	{
		 String[] contentStr=content.split("#");
		 Map<String, String> parameters = new HashMap<String, String>();  
	     parameters.put("Code", contentStr[1].trim());  
	     parameters.put("Pwd", contentStr[2].trim());  
	     String result = NetUtil.sendPost(ConstantsUtil.COMMISSIONSHEET_QUERY_URL,  parameters);
	     JSONObject jsonObject=JSONObject.fromObject(result);
	     String message="";
	     if(jsonObject.getBoolean("IsOK"))
	    	 message=MessageUtil.init_commissionSheetQuery_NewsMessage(toUserName, fromUserName, jsonObject);
	     else {
			message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.commissionSheetQueryErrorText(jsonObject));
		 }
	     return message;
	     
	}
	
	public static String getCertificateStautsMessage(String toUserName,String fromUserName,String content) throws UnsupportedEncodingException
	{
		String[] contentStr=content.split("#");
		Map<String, String> parameters = new HashMap<String, String>();  
		parameters.put("Code", contentStr[1].trim());  
		String result = NetUtil.sendPost(ConstantsUtil.CERTIFICATE_QUERY_URL,  parameters);
		JSONObject jsonObject=JSONObject.fromObject(result);
		String message="";
		if(jsonObject.getBoolean("IsOK"))
			message=MessageUtil.init_certificateQuery_NewsMessage(toUserName, fromUserName, jsonObject);
		else {
			message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.certificateQueryErrorText(jsonObject));
		}
		return message;
	}

}
