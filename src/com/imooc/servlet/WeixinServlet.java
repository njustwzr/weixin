package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.imooc.net.RemoteQuery;
import com.imooc.util.CheckUtil;
import com.imooc.util.ConstantsUtil;
import com.imooc.util.MessageUtil;

public class WeixinServlet extends HttpServlet {
	
	private static final long serialVersionUID=1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
		
		PrintWriter out=resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce))
		{
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		try {
			Map<String, String> map=MessageUtil.xmlToMap(req);  //将接收到的xml消息转换成map
			String fromUserName=map.get("FromUserName");
			String toUserName=map.get("ToUserName");
			String msgType=map.get("MsgType");
			String content=map.get("Content");
			
			//根据msgType来判断消息类型，除了文本消息，还有图片消息、视频消息、语音消息、
			//地理位置消息，还有事件推送：关注、取消关注、菜单点击（click、view）
			String message=null;    //用于最终消息的返回
			if(ConstantsUtil.MESSAGE_TEXT.equals(msgType))
			{
				if("1".equals(content.trim()))    //回复“服务介绍”的图文消息
				{
					message=MessageUtil.initServiceIntroduction_NewsMessage(fromUserName, toUserName);
				}else if ("2".equals(content.trim())) {  //回复“收费标准查询”的图文消息
					message=MessageUtil.initQueryDetectionFee_NewsMessage(fromUserName, toUserName);
				}else if ("3".equals(content.trim())) {  //回复“付款方式”图文消息
					message=MessageUtil.initPayMethod_NewsMessage(fromUserName, toUserName);
				}else if ("4".equals(content.trim())) {  //回复“服务承诺期”图文消息
					message=MessageUtil.initServicePromise_NewsMessage(fromUserName, toUserName);
				}else if ("5".equals(content.trim())) {  //回复图文消息
					message=MessageUtil.initDepartmentIntroduction_NewsMessage(fromUserName, toUserName);
				}else if ("6".equals(content.trim())) {  //回复"检测进度查询帮助"消息
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.detectionProcessText());
				}else if ("7".equals(content.trim())) {  //回复“证书真伪鉴定帮助”消息
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.certificateIdentifyText());
				}else if ("?".equals(content.trim()) || "？".equals(content.trim())) {
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.helpText());
				}else if (content!=null && content.trim().startsWith("cs")) { //查询委托单的检测进度
					message=RemoteQuery.getCommissionsheetStautsMessage(fromUserName, toUserName, content);
				}else if (content!=null && content.trim().startsWith("cert")) { //进行证书的真伪鉴定查询
					message=RemoteQuery.getCertificateStautsMessage(fromUserName, toUserName,content);
				}else {
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.otherTextReply(content));
				}
			}else if(ConstantsUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType=map.get("Event");
				if(ConstantsUtil.MESSAGE_SUBSCRIBE.equals(eventType))
				{
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if (ConstantsUtil.MESSAGE_CLICK.equals(eventType)) {
					String key=map.get("EventKey");
					if(key.equals("21"))
					{
						message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.detectionProcessText());
					}else if (key.equals("22")) {
						message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.certificateIdentifyText());
					}else if (key.equals("31")) {
						message=MessageUtil.initJlKnowledge_NewsMessage(fromUserName, toUserName);
					}else {
						message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.helpText());
					}
				}else if (ConstantsUtil.MESSAGE_VIEW.equals(eventType)) {
					String url=map.get("EventKey");
					message=MessageUtil.initText(toUserName, fromUserName, url);
				}
			}else if (ConstantsUtil.MESSAGE_LOCATION.equals(msgType)) {
				String label=map.get("Label");
				message=MessageUtil.initText(toUserName, fromUserName, label);
			}
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
