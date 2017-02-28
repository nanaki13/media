package org.jbonnet.bean;

public interface SetterGetterStringAccess {
	public default Object readField(String field){
		return ObjectIOInterface.Factory.getInstance(getClass()).getFrom(field, this);
	}
	public default void writeField(String field, Object value){
		ObjectIOInterface.Factory.getInstance(getClass()).setTo(field, this,value);
	}
}
