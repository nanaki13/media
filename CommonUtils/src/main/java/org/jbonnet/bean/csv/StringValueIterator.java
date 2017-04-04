package org.jbonnet.bean.csv;

import java.util.Iterator;

public class StringValueIterator implements Iterable<String> {

	private String inner;
	private int index = 0; 
	
	/**
	 * @param inner
	 */
	public StringValueIterator(String inner) {
		super();
		this.inner = inner;
	}


	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>(){

			@Override
			public boolean hasNext() {
				return index+1 < inner.length() && inner.charAt(inner.length()-1)!=';';
			}

			@Override
			public String next() {
				StringBuilder builder = new StringBuilder();
				while(notEndValue()){
					builder.append(readedChar());
				}
				return builder.toString();
			}};
	}


	protected char readedChar() {
		return null;
	}


	protected boolean notEndValue() {
		if(index+1 < inner.length()){
			char charAt = inner.charAt(index+1);
			
			return index+1 < inner.length() && charAt!=';'&&charAt!='"';
		}
		return false;
		
	}

}
