package org.jbonnet.bean.csv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringValueIterator implements Iterable<String> {
	private static enum Status{ IN_STRING,NORMAL  };
	private String inner;
	private int index = 0; 
	private int valueIndex = 0;
	private Status status = Status.NORMAL;
	private char sep = ';'; 
	
	/**
	 * @param inner
	 */
	public StringValueIterator(String inner) {
		super();
		this.inner = inner;
	}


	public StringValueIterator() {
	}


	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>(){

			@Override
			public boolean hasNext() {
				return index < inner.length() ;
			}

			@Override
			public String next() {
				StringBuilder builder = new StringBuilder();
				while(notEndValue()){
					int readedChar = readedChar();
					if(readedChar!=-1){
						builder.append((char)readedChar);
					}
					index++;
					valueIndex++;
				}
				index++;
				valueIndex=0;
				return builder.toString();
			}};
	}


	protected int readedChar() {
		int charAt = inner.charAt(index);
		if(charAt == '"' && status == Status.NORMAL && valueIndex == 0){
			status = Status.IN_STRING;
			index++;
			charAt = inner.charAt(index);
		}else if(charAt == '"' && status == Status.IN_STRING){
			status = Status.NORMAL;
			charAt = -1;
		}else if(charAt == '\\' && status == Status.IN_STRING){
			index++;
			charAt = inner.charAt(index);
		}
		return charAt;
	}


	protected boolean notEndValue() {
		if(index < inner.length()){
			char charAt = inner.charAt(index);
			
			return charAt!=sep ;
		}
		return false;
		
	}


	public List<String> toList() {
		List<String> ret = new ArrayList<>();
		for(String s : this){
			ret.add(s);
		}
		return ret;
		
	}
	
	public void reset(String newInner){
		inner = newInner;
		index = 0;
		valueIndex = 0;
		status = Status.NORMAL;
	}

}
