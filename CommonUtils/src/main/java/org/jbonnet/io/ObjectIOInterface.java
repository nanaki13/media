package org.jbonnet.io;

import java.util.Collection;
import java.util.Iterator;

public interface ObjectIOInterface<O> {
	public static final Object DEFAULT_SEPARATOR = ", ";
	public Object getFrom(String field, O read) throws  Exception;
	public  void setTo(String field, O write, Object value) throws Exception;
	public Collection<String> getFields();
	public default String string(O o){
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
}
