package com.imooc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.imooc.po.Image;
import com.imooc.po.ImageMessage;
import com.imooc.po.Music;
import com.imooc.po.MusicMessage;
import com.imooc.po.News;
import com.imooc.po.NewsMessage;
import com.imooc.po.TextMessage;
import com.thoughtworks.xstream.XStream;


public class MessageUtil {
	
	/**
	 * 将xml类型转换成map集合
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest req) throws IOException, DocumentException
	{
		Map<String, String> map=new HashMap<String, String>();
		SAXReader reader=new SAXReader();
		InputStream ins=req.getInputStream();
		Document doc=reader.read(ins);
		
		Element root=doc.getRootElement();
		
		List<Element> list= root.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		ins.close();
		return map;
	}
	/**
	 * 将普通的文本消息转换成xml
	 * @param textMessage
	 * @return
	 */
	public static String textToXml(TextMessage textMessage)
	{
		XStream xStream=new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
	}
	
	/**
	 * 主菜单的拼接，用于关注事件
	 * @return
	 */
	public static String menuText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("国计民生，'常"+"量'在心。您好，欢迎关注常州计量！\n\n");
		sb.append("提示：点击右下角 '更多服务'"+" 或者回复 '?'查看帮助\n");
		return sb.toString();
	}
	
	/**
	 * 主菜单的拼接，用于帮助事件
	 * @return
	 */
	public static String helpText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("小智为您进行快捷功能介绍，回复数字进行操作:\n");
		sb.append("1、服务介绍\n");
		sb.append("2、收费标准查询\n");
		sb.append("3、付款方式\n");
		sb.append("4、 服务承诺期\n");
		sb.append("5、部门介绍\n");
		sb.append("6、委托检测进度查询\n");
		sb.append("7、证书真伪鉴定\n");
		sb.append("回复?查看帮助\n");
		return sb.toString();
	}
	/**
	 * 检测进度查询的提示信息
	 * @return
	 */
	public static String detectionProcessText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("请回复'cs+#+委托单号+#+密码'进行检测进度查询！\n");
		sb.append("例如:单号为201601234委托单，其密码是1234，则回复:\ncs#201601234#1234\n");
		return sb.toString();
	}
	/**
	 * 检测进度查询的提示信息
	 * @return
	 */
	public static String certificateIdentifyText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("请回复'cert#证书号'进行证书真伪鉴定！例如:证书号为\n");
		sb.append("201610020123001的证书，则回复:cert#201610020123001\n");
		return sb.toString();
	}
	
	public static String initText(String toUserName,String fromUserName,String content)
	{
		TextMessage textMessage=new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setMsgType(ConstantsUtil.MESSAGE_TEXT);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setContent(content);
		return MessageUtil.textToXml(textMessage);
	}
	
	/**
	 * 用户回复1时候回复“服务介绍”的图文消息
	 * @return
	 */
	public static String initServiceIntroduction_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("我所提供服务介绍");
		news.setDescription("常州计量所为您提供科学、公正、准确、满意的服务！");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/service.jpg");
		news.setUrl("http://www.czjl.net/khzx/");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	/**
	 * 用户回复2时候回复“检测费用查询”的图文消息
	 * @return
	 */
	public static String initQueryDetectionFee_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("收费标准查询");
		news.setDescription("计量检定收费标准查询！");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/fee.png");
		news.setUrl("http://www.czjl.net/sfbz/");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	
	/**
	 * 用户回复3时候回复“付款方式”的图文消息
	 * @return
	 */
	public static String initPayMethod_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("付款方式");
		news.setDescription("付款方式查询");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		news.setUrl("http://www.czjl.net/khzx/fkfs/");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	/**
	 * 用户回复4时候回复“服务承诺期”的图文消息
	 * @return
	 */
	public static String initServicePromise_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("服务承诺期");
		news.setDescription("服务承诺期介绍");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		news.setUrl("http://www.czjl.net/khzx/fwcnq/");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	/**
	 * 用户回复5时候回复“部门介绍”的图文消息
	 * @return
	 */
	public static String initDepartmentIntroduction_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("专业部门介绍");
		news.setDescription("专业部门介绍");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		news.setUrl("http://www.czjl.net/kzxm/index.html");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	/**
	 * 用户回复2 时候回复的消息
	 * @return
	 */
	public static String secondMenu()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
		sb.append("慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。以纯干货、短视频的形式为平台特点，为在校学生、职场白领提供了一个迅速提升技能、共同分享进步的学习平台。\n");
		return sb.toString();
	}
	
	/**
	 * 用户回复其他字符 时候回复的消息
	 * @return
	 */
	public static String otherTextReply(String content)
	{
		StringBuffer sb=new StringBuffer();
		sb.append("您的输入不合法，请输入'?'查看帮助！");
		return sb.toString();
	}
	
	/**
	 * 将图文消息转换成xml类型
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage)
	{
		XStream xStream=new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new News().getClass());
		return xStream.toXML(newsMessage);
	}
/*	*//**
	 * 将图片消息转换成xml类型
	 * @param ImageMessage
	 * @return
	 *//*
	public static String imageMessageToXml(ImageMessage imageMessage)
	{
		XStream xStream=new XStream();
		xStream.alias("xml", imageMessage.getClass());
		//xStream.alias("image", new Image().getClass());
		return xStream.toXML(imageMessage);
	}*/
/*	*//**
	 * 将音乐消息转换成xml类型
	 * @param MusicMessage
	 * @return
	 *//*
	public static String musicMessageToXml(MusicMessage musicMessage)
	{
		XStream xStream=new XStream();
		xStream.alias("xml", musicMessage.getClass());
		//xStream.alias("image", new Image().getClass());
		return xStream.toXML(musicMessage);
	}*/
	
/*	*//**
	 * 组装图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 *//*
	public static String initNewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("慕课网介绍");
		news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/imooc.jpg");
		news.setUrl("http://www.imooc.com");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}*/
	/**
	 * 组装计量知识库图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initJlKnowledge_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("民生计量小常识，不看你会后悔的！");
		news.setDescription("民生计量直接与人民群众的安全、健康和切身利益相关，我们在这里刊登计量知识，旨在让全社会了解计量、关心计量、享受计量惠民成果，支持计量事业发展！");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		news.setUrl("http://www.czjl.net/msfw/jlzs/");
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	
	/**
	 * 检测进度查询的错误返回信息
	 * @return
	 */
	public static String commissionSheetQueryErrorText(JSONObject result)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(result.get("msg"));
		return sb.toString();
	}
	
	/**
	 * 证书真伪鉴定的时候的返回信息
	 * @return
	 */
	public static String certificateQueryErrorText(JSONObject result)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(result.get("msg"));
		return sb.toString();
	}
    /**
     * 组装检测进度查询消息
     * @param toUserName
     * @param fromUserName
     * @param result
     * @return
     */
	public static String init_commissionSheetQuery_NewsMessage(String toUserName,String fromUserName,JSONObject result)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("检测进度查询结果");
		news.setDescription("本查询结果仅提供参考，最终解释权归常州市计量测试技术研究所所有！");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		
		//组装url
		StringBuffer url=new StringBuffer(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/pages/commissionSheetShow.jsp?");
		url.append("commissionCode="+result.get("commissionCode").toString());
		url.append("&customerName="+result.get("customerName").toString());
		url.append("&applianceName="+result.get("applianceName").toString());
		url.append("&commissionDate="+result.get("commissionDate").toString());
		url.append("&quantity="+result.get("quantity").toString());
		url.append("&status="+result.get("status").toString());
		news.setUrl(url.toString());
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	/**
	 * 组装证书查询结果的图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @param result
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String init_certificateQuery_NewsMessage(String toUserName,String fromUserName,JSONObject result) throws UnsupportedEncodingException
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("证书信息查询");
		news.setDescription("本查询结果仅提供参考，最终解释权归常州市计量测试技术研究所所有！");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		
		//组装url
		StringBuffer url=new StringBuffer(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/pages/certificateQueryShow.jsp?");
		url.append("certificateCode="+result.get("certificateCode").toString());
		url.append("&customerName="+result.get("customerName").toString());
		url.append("&applianceName="+result.get("applianceName").toString());
		url.append("&workDate="+result.get("workDate").toString());
		url.append("&securityCode="+result.get("securityCode").toString());
		news.setUrl(url.toString());
		
		newList.add(news);
		newsMessage.setToUserName(toUserName);
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(ConstantsUtil.MESSAGE_NEWS);
		newsMessage.setArticles(newList);
		newsMessage.setArticleCount(newList.size());
		return newsMessageToXml(newsMessage);
	}
	
	
	/**
	 * 组装图片消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 *//*
	public static String initImageMessage(String toUserName,String fromUserName)
	{
		String message=null;
		
		Image image=new Image();
		image.setMediaId("bE-h49vxk_Wd7dT4wPJQ4kSwkbbP3pbU7u595HPNKh22NHKgCs1ApTN6T8IrmvcR");
		ImageMessage imageMessage=new ImageMessage();
		imageMessage.setFromUserName(fromUserName);
		imageMessage.setToUserName(toUserName);
		imageMessage.setMsgType(ConstantsUtil.MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		return imageMessageToXml(imageMessage);
	}*/
/*	*//**
	 * 组装音乐消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 *//*
	public static String initMusicMessage(String toUserName,String fromUserName)
	{
		String message=null;
		
		Music music=new Music();
		music.setThumbMediaId("9XItqkwSK8VRqzR8fz0uLmUQQuL-TteemYUpFJZFzj0PV4HWI5uxAqNFyLykXxt7");
		music.setTitle("see you again");
		music.setDescription("速7片尾曲");
		music.setMusicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/resources/See You Again.mp3");
		music.setHQMusicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/resources/See You Again.mp3");
		
		MusicMessage musicMessage=new MusicMessage();
		musicMessage.setFromUserName(fromUserName);
		musicMessage.setToUserName(toUserName);
		musicMessage.setMsgType(ConstantsUtil.MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message=musicMessageToXml(musicMessage);
		System.out.println(message);
		return message;
	}*/

}
