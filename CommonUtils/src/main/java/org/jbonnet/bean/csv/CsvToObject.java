package org.jbonnet.bean.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbonnet.bean.ObjectIOInterface;
import org.jbonnet.test.A;

public class CsvToObject<T> implements Iterable<T>,AutoCloseable {
	
	

	private Reader reader;
	private boolean end = false;
	private BufferedReader bufferedReader;
	private String currentLine;
	private List<String> header;
	private ObjectIOInterface objectIOInterface;
	private Class<T> outClass;
	private StringValueIterator svi;
	public CsvToObject(Reader reader,Class<T> outClass) throws IOException{
		this.reader = reader;
		bufferedReader = (reader instanceof BufferedReader) ?(BufferedReader) reader : new BufferedReader(reader);
		objectIOInterface = ObjectIOInterface.Factory.getInstance(outClass);
		this.outClass = outClass;
		svi = new StringValueIterator();
		header = readHeader();
		
		
	}
	
	private void readLine() throws IOException{
		currentLine = bufferedReader.readLine();
		if(currentLine == null){
			end = true;
		}
		
	}
	
	public List<String> readHeader() throws IOException{
		readLine();
		svi.reset(currentLine);
		return svi.toList();
		
		
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				try {
					readLine();
					return !end;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}

			@Override
			public T next() {
				T newInstance = null;
				try {
					newInstance = outClass.newInstance();
					svi.reset(currentLine);
					int count = 0;
					for(String value : svi){
						objectIOInterface.setToFromString(header.get(count), newInstance, value);
						count++;
					}
					
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return newInstance;
				
			}
		};
	}

	@Override
	public void close() throws IOException {
		bufferedReader.close();
		reader.close();
		
	}

	public List<String> getHeader() {
		return header;
	}

	public List<T> toList() {
		List<T> ret = new ArrayList<>();
		this.iterator().forEachRemaining((e) -> ret.add(e));
		return ret;		
	}
}
