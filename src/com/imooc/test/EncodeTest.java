package com.imooc.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.imooc.util.ConstantsUtil;

public class EncodeTest {
	public static void main(String[] args) {
		StringBuffer url=new StringBuffer(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/pages/certificateQueryShow.jsp?");
		url.append("certificateCode="+"2016567890");
		url.append("&customerName="+URLEncoder.encode("常州市计量测试技术研究所"));
		url.append("&applianceName="+URLEncoder.encode("一般压力表"));
		url.append("&workDate="+"2016-07-01");
		url.append("&securityCode="+"1234 7868 0987 0987");
		System.out.println(url.toString());
		System.out.println(URLDecoder.decode("%D2%BB%B0%E3%D1%B9%C1%A6%B1%ED"));
	}
	

}
