package org.jbonnet.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO {
	public static void write(Object o,DataOutputStream dos) throws IOException{
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		(new ObjectOutputStream(dos)).writeObject(o);
		
		
	}
	
	public static Object write(DataInputStream dis) throws IOException, ClassNotFoundException{
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		return (new ObjectInputStream(dis)).readObject();
		
		
	}
}
