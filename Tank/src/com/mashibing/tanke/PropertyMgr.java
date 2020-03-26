package com.mashibing.tanke;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	private static volatile Properties properties =null;
	
	private PropertyMgr(){};
	
	
	
	public static Object get(String key) {
		if(properties==null) {
			synchronized (properties) {
				if(properties==null) {
					properties = new Properties();
					try {
						properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		return properties.get(key);
	}
	
	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("intTankCount"));
	}

}
