package com.imooc.util;

import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.imooc.po.AccessToken;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class AccessTokenUtil {
	
	static Object obj=new Object();
	/**
	 * 从微信服务器处获取access_token
	 * @return
	 */
    private static AccessToken getAccessToken()
    {
    	AccessToken accessToken=new AccessToken();
    	String url=ConstantsUtil.ACCESS_TOKEN_URL.replace("APPID", ConstantsUtil.APPID).replace("APPSECRET", ConstantsUtil.APPSECRET);
    	JSONObject jsonObject=WeixinUtil.doGetStr(url);
    	if(jsonObject!=null)
    	{
    		accessToken.setToken(jsonObject.getString("access_token"));
    		accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
    	}
    	return accessToken;
    }
    
    private static boolean checkExpire()
    {
    	if(!ConstantsUtil.tokenMap.containsKey(ConstantsUtil.KEY2_OF_MAP))
    		return false;
    	long difference=(new Date().getTime()/1000)-Long.parseLong(ConstantsUtil.tokenMap.get(ConstantsUtil.KEY2_OF_MAP));
    	if(difference>ConstantsUtil.DIFFERENCE_TIME)
    		return false;
    	return true;
    	
    }
    
    
    /**
     * 向用户开放的token获取方法
     * @return  获取token的字符串
     */
    public static String getTokenStr()
    {
    	String token=null; 
    	if(ConstantsUtil.tokenMap.containsKey(ConstantsUtil.KEY_OF_MAP) && checkExpire())
    	{
    		return  ConstantsUtil.tokenMap.get(ConstantsUtil.KEY_OF_MAP);
    	}
    	synchronized (obj) {
    		if(ConstantsUtil.tokenMap==null)
        	{
        		ConstantsUtil.tokenMap=new HashMap<String, String>();
        	}
        	else if(!ConstantsUtil.tokenMap.containsKey(ConstantsUtil.KEY_OF_MAP) || !checkExpire())
        	{
        		AccessToken accessToken=getAccessToken();
        	    token=accessToken.getToken();
        	    ConstantsUtil.tokenMap.put(ConstantsUtil.KEY_OF_MAP, token);
        		ConstantsUtil.tokenMap.put(ConstantsUtil.KEY2_OF_MAP, (new Date().getTime()/1000)+"");
        	}
        	else {
    			token=ConstantsUtil.tokenMap.get(ConstantsUtil.KEY_OF_MAP);
    		}
		}
    	
    	return token;
    }
    
    
    
    
    
}
