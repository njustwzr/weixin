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
	 * ��xml����ת����map����
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
	 * ����ͨ���ı���Ϣת����xml
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
	 * ���˵���ƴ�ӣ����ڹ�ע�¼�
	 * @return
	 */
	public static String menuText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("����������'��"+"��'���ġ����ã���ӭ��ע���ݼ�����\n\n");
		sb.append("��ʾ��������½� '�������'"+" ���߻ظ� '?'�鿴����\n");
		return sb.toString();
	}
	
	/**
	 * ���˵���ƴ�ӣ����ڰ����¼�
	 * @return
	 */
	public static String helpText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("С��Ϊ�����п�ݹ��ܽ��ܣ��ظ����ֽ��в���:\n");
		sb.append("1���������\n");
		sb.append("2���շѱ�׼��ѯ\n");
		sb.append("3�����ʽ\n");
		sb.append("4�� �����ŵ��\n");
		sb.append("5�����Ž���\n");
		sb.append("6��ί�м����Ȳ�ѯ\n");
		sb.append("7��֤����α����\n");
		sb.append("�ظ�?�鿴����\n");
		return sb.toString();
	}
	/**
	 * �����Ȳ�ѯ����ʾ��Ϣ
	 * @return
	 */
	public static String detectionProcessText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("��ظ�'cs+#+ί�е���+#+����'���м����Ȳ�ѯ��\n");
		sb.append("����:����Ϊ201601234ί�е�����������1234����ظ�:\ncs#201601234#1234\n");
		return sb.toString();
	}
	/**
	 * �����Ȳ�ѯ����ʾ��Ϣ
	 * @return
	 */
	public static String certificateIdentifyText()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("��ظ�'cert#֤���'����֤����α����������:֤���Ϊ\n");
		sb.append("201610020123001��֤�飬��ظ�:cert#201610020123001\n");
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
	 * �û��ظ�1ʱ��ظ���������ܡ���ͼ����Ϣ
	 * @return
	 */
	public static String initServiceIntroduction_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("�����ṩ�������");
		news.setDescription("���ݼ�����Ϊ���ṩ��ѧ��������׼ȷ������ķ���");
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
	 * �û��ظ�2ʱ��ظ��������ò�ѯ����ͼ����Ϣ
	 * @return
	 */
	public static String initQueryDetectionFee_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("�շѱ�׼��ѯ");
		news.setDescription("�����춨�շѱ�׼��ѯ��");
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
	 * �û��ظ�3ʱ��ظ������ʽ����ͼ����Ϣ
	 * @return
	 */
	public static String initPayMethod_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("���ʽ");
		news.setDescription("���ʽ��ѯ");
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
	 * �û��ظ�4ʱ��ظ��������ŵ�ڡ���ͼ����Ϣ
	 * @return
	 */
	public static String initServicePromise_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("�����ŵ��");
		news.setDescription("�����ŵ�ڽ���");
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
	 * �û��ظ�5ʱ��ظ������Ž��ܡ���ͼ����Ϣ
	 * @return
	 */
	public static String initDepartmentIntroduction_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("רҵ���Ž���");
		news.setDescription("רҵ���Ž���");
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
	 * �û��ظ�2 ʱ��ظ�����Ϣ
	 * @return
	 */
	public static String secondMenu()
	{
		StringBuffer sb=new StringBuffer();
		sb.append("Ľ�����Ǵ�ֱ�Ļ�����IT�������ѧϰ��վ���Զ�����Ƶ�̡̳����߱�̹��ߡ�ѧϰ�ƻ����ʴ�����Ϊ������ɫ�������������ҵ���õĻ���������ţ�ˣ�Ҳ����ͨ����ѵ����߹�����Ƶ�γ�ѧϰ�������ȵĻ�����IT������");
		sb.append("Ľ�����γ̺���ǰ�˿�����PHP��Html5��Android��iOS��Swift��ITǰ�ؼ������ԣ����������γ̡�ʵ�ð������߼������������ͣ��ʺϲ�ͬ�׶ε�ѧϰ��Ⱥ���Դ��ɻ�������Ƶ����ʽΪƽ̨�ص㣬Ϊ��Уѧ����ְ�������ṩ��һ��Ѹ���������ܡ���ͬ���������ѧϰƽ̨��\n");
		return sb.toString();
	}
	
	/**
	 * �û��ظ������ַ� ʱ��ظ�����Ϣ
	 * @return
	 */
	public static String otherTextReply(String content)
	{
		StringBuffer sb=new StringBuffer();
		sb.append("�������벻�Ϸ���������'?'�鿴������");
		return sb.toString();
	}
	
	/**
	 * ��ͼ����Ϣת����xml����
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
	 * ��ͼƬ��Ϣת����xml����
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
	 * ��������Ϣת����xml����
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
	 * ��װͼ����Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 *//*
	public static String initNewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("Ľ��������");
		news.setDescription("Ľ�����Ǵ�ֱ�Ļ�����IT�������ѧϰ��վ���Զ�����Ƶ�̡̳����߱�̹��ߡ�ѧϰ�ƻ����ʴ�����Ϊ������ɫ�������������ҵ���õĻ���������ţ�ˣ�Ҳ����ͨ����ѵ����߹�����Ƶ�γ�ѧϰ�������ȵĻ�����IT������");
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
	 * ��װ����֪ʶ��ͼ����Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initJlKnowledge_NewsMessage(String toUserName,String fromUserName)
	{
		List<News> newList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("��������С��ʶ����������ڵģ�");
		news.setDescription("��������ֱ��������Ⱥ�ڵİ�ȫ������������������أ����������￯�Ǽ���֪ʶ��ּ����ȫ����˽���������ļ��������ܼ�������ɹ���֧�ּ�����ҵ��չ��");
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
	 * �����Ȳ�ѯ�Ĵ��󷵻���Ϣ
	 * @return
	 */
	public static String commissionSheetQueryErrorText(JSONObject result)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(result.get("msg"));
		return sb.toString();
	}
	
	/**
	 * ֤����α������ʱ��ķ�����Ϣ
	 * @return
	 */
	public static String certificateQueryErrorText(JSONObject result)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(result.get("msg"));
		return sb.toString();
	}
    /**
     * ��װ�����Ȳ�ѯ��Ϣ
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
		news.setTitle("�����Ȳ�ѯ���");
		news.setDescription("����ѯ������ṩ�ο������ս���Ȩ�鳣���м������Լ����о������У�");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		
		//��װurl
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
	 * ��װ֤���ѯ�����ͼ����Ϣ
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
		news.setTitle("֤����Ϣ��ѯ");
		news.setDescription("����ѯ������ṩ�ο������ս���Ȩ�鳣���м������Լ����о������У�");
		news.setPicUrl(ConstantsUtil.SERVER_DOMAIN_NAME+"/Weixin/images/jl_knowledge.jpg");
		
		//��װurl
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
	 * ��װͼƬ��Ϣ
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
	 * ��װ������Ϣ
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
		music.setDescription("��7Ƭβ��");
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
