package org.jbonnet.bean;

import java.util.Collection;
import java.util.Iterator;

public interface ObjectIOInterface {
	public static final Object DEFAULT_SEPARATOR = ", ";
	public Object getFrom(String field, Object read) ;
	public  void setTo(String field, Object write, Object value) ;
	public Collection<String> getFields();
	public default String string(Object o){
		Collection<String> fields = getFields();
		Iterator<String> iterator = fields.iterator();
		
		StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			String string =  iterator.next();
			try {
				builder.append(String.valueOf(getFrom(string, o)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(iterator.hasNext()){
				builder.append(getSeparator());
			}
			
		}
		return builder.toString();
	}
	public default Object getSeparator(){
		return DEFAULT_SEPARATOR;
	}
	public static class Factory{
		public static ObjectIOInterface getInstance(Class<?> c){
			return ReflectIOInterface.getInstance(c);
		}
	}
}
