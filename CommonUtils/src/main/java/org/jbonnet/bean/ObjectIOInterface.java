package org.jbonnet.bean;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import org.jbonnet.lang.Strings;

public interface ObjectIOInterface {
	public static final Object DEFAULT_SEPARATOR = ", ";
	public Object getFrom(String field, Object readed) ;
	public  void setTo(String field, Object writed, Object value) ;
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
	public default void setToFromString(String field, Object o, String text){
		Class<?> type = getType(field);
		Object converted = Strings.convertTo(type,text);
		setTo(field, o, converted);
	}
	public Class<?> getType(String field);
	public default void setToFromLocalDate(String field, Object o, LocalDate value){
		if(value != null){
			Class<?> type = getType(field);
			if(Calendar.class.isAssignableFrom(type )){
				Calendar instance = Calendar.getInstance();
				instance.setTimeInMillis(value.toEpochDay()*24*3600000);
				setTo(field, o, instance);
			}else if(LocalDate.class.isAssignableFrom(type )){
				setTo(field, o, value);
			}
		}
		
//		Object converted = Strings.convertTo(type,text);
//		setTo(field, o, converted);
	}
}
