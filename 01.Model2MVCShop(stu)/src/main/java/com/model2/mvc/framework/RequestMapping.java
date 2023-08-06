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
		//HashMap을 쓴 이유는 값을 빠르게 찾기위함이다.
		//List를쓰게되면 전체를 순환하기때문에 속도가 늦고 쓸모없는 메모리가 늘어난다.
		map = new HashMap<String, Action>();
		InputStream in = null;
		try{
			//resources를 읽어들이는 in
			//getClass로 RequestMapping의 meta-data를 가져옴
			//getClassLoader로 해당클래스에 접근
			//getResourceAsStream의 메소드를 사용하여 리소스를 읽어들임
			in = getClass().getClassLoader().getResourceAsStream(resources);
			
			//1. properties 클래스의 load메소드를 사용
			//2. load메소드는 InputStream inStream 인자를 받는다.
			//3. 위에서 정의한 in은 actionmapping.properties을 뜻한다.
			//4. 결국 actionmapping내 의 key와 value를 전부가져온다.
			//5. InputStream을 사용했으면 필수로 닫아줘야한다. 불필요한메모리사용방지
			properties = new Properties();
			properties.load(in);
		}catch(Exception ex){
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :"  + ex);
		}finally{
			if(in != null){
				try{ in.close(); } catch(Exception ex){}
			}
		}
	}
	
	public synchronized static RequestMapping getInstance(String resources){
		//1. 유일한 인스턴스를 반환한다.
		//2. 만약 requestMapping이 null을 참조한다면...
		//3. RequestMapping(String resources)메소드를 사용하여 properties.load(in)
		if(requestMapping == null){
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}
	
	public Action getAction(String path){
		Action action = map.get(path);
		if(action == null){
			String className = properties.getProperty(path);
			System.out.println("1단계 : prop 가져오기 : " + properties);
			System.out.println("2단계 : path 파싱 : " + path);			
			System.out.println("3단계 : acionServlet가져오기 className : " + className);
			className = className.trim();
			try{
				Class c = Class.forName(className);
				Object obj = c.newInstance();
				if(obj instanceof Action){
					map.put(path, (Action)obj);
					action = (Action)obj;
				}else{
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			}catch(Exception ex){
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action;
	}
}