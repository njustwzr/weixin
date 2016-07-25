package com.imooc.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.ParseException;

import net.sf.json.JSONObject;

import com.imooc.po.AccessToken;
import com.imooc.util.AccessTokenUtil;
import com.imooc.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException, ParseException {
		String token=AccessTokenUtil.getTokenStr();
		System.out.println("票据:"+token);
		
		/*String path="D:/imooc1.jpg";
		String media_id=WeixinUtil.upload(path, token.getToken(), "thumb");//上传缩略图
		System.out.println(media_id);*/
		
		String menu=JSONObject.fromObject(WeixinUtil.initCzjlMenu()).toString();
		int result=WeixinUtil.createMenu(token, menu);
		if(result==0)
		{
			System.out.println("创建菜单成功");
		}
		else{
			System.out.println("错误码:"+result);
		}
		
		/*JSONObject jsonObject=WeixinUtil.queryMenu(token.getToken());
		System.out.println(jsonObject);
		*/
		/*int result=WeixinUtil.deleteMenu(token);
		if(result==0)
		{
			System.out.println("菜单删除成功！");
		}
		else{
			System.out.println(result);
		}*/
			
			
		
	}
}
