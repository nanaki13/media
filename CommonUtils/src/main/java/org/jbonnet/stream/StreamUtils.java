package org.jbonnet.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
	public static File inputStreamToFile(InputStream inputStream,String file) throws IOException{
		InputStream locIn = null;
		try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));){
			
			if(!(inputStream instanceof BufferedInputStream)){
				locIn = new BufferedInputStream(inputStream);
			}else{
				locIn = inputStream;
			}
			int b;
			while(( b = locIn.read()) != -1){
				outputStream.write(b);
			}
		}finally {
			if(locIn != null){
				locIn.close();
			}
		}
		return new File(file);
		
	}
}
