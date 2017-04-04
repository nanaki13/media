package org.jbonnet.bean.csv;

import java.io.BufferedReader;
import java.io.Reader;

public class CsvToObject {
	
	private static enum Status{ IN_STRING  };
	private Status status;
	private Reader reader;
	private BufferedReader bufferedReader;
	public CsvToObject(Reader reader){
		this.reader = reader;
		bufferedReader = (reader instanceof BufferedReader) ?(BufferedReader) reader : new BufferedReader(reader); 
	}
	
	public readHeader(){
		String readLine = bufferedReader.readLine();
		
	}
}
