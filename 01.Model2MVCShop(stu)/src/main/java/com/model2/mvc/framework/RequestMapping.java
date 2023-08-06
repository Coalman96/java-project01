package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RequestMapping {
	
	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	private RequestMapping(String resources) {
		//HashMap�� �� ������ ���� ������ ã�������̴�.
		//List�����ԵǸ� ��ü�� ��ȯ�ϱ⶧���� �ӵ��� �ʰ� ������� �޸𸮰� �þ��.
		map = new HashMap<String, Action>();
		InputStream in = null;
		try{
			//resources�� �о���̴� in
			//getClass�� RequestMapping�� meta-data�� ������
			//getClassLoader�� �ش�Ŭ������ ����
			//getResourceAsStream�� �޼ҵ带 ����Ͽ� ���ҽ��� �о����
			in = getClass().getClassLoader().getResourceAsStream(resources);
			
			//1. properties Ŭ������ load�޼ҵ带 ���
			//2. load�޼ҵ�� InputStream inStream ���ڸ� �޴´�.
			//3. ������ ������ in�� actionmapping.properties�� ���Ѵ�.
			//4. �ᱹ actionmapping�� �� key�� value�� ���ΰ����´�.
			//5. InputStream�� ��������� �ʼ��� �ݾ�����Ѵ�. ���ʿ��Ѹ޸𸮻�����
			properties = new Properties();
			properties.load(in);
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties ���� �ε� ���� :"  + ex);
		}finally{
			if(in != null){
				try{ in.close(); } catch(Exception ex){}
			}
		}
	}
	
	public synchronized static RequestMapping getInstance(String resources){
		//1. ������ �ν��Ͻ��� ��ȯ�Ѵ�.
		//2. ���� requestMapping�� null�� �����Ѵٸ�...
		//3. RequestMapping(String resources)�޼ҵ带 ����Ͽ� properties.load(in)
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	public Action getAction(String path){
		Action action = map.get(path);
		if(action == null){
			String className = properties.getProperty(path);
			System.out.println("1�ܰ� : prop �������� : " + properties);
			System.out.println("2�ܰ� : path �Ľ� : " + path);			
			System.out.println("3�ܰ� : acionServlet�������� className : " + className);
			className = className.trim();
			try{
				Class c = Class.forName(className);
				Object obj = c.newInstance();
				if(obj instanceof Action){
					map.put(path, (Action)obj);
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class����ȯ�� ���� �߻�  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action������ ���ϴ� ���� ���� �߻� : " + ex);
			}
		}
		return action;
	}
}