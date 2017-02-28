package org.jbonnet.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTest {

	public static void main(String[] args) throws IOException {
		Server server = new Server(8080, (t,e,c)->process(t,e,c), new ServerSocket(1234)
				, (s,is,os,dis,dos)->process(s,is,os,dis,dos));
		server.start();
		
		Socket socket = new Socket("localhost", 8080);
		try(DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());){
//			dataOutputStream.writeUTF("salut");
			ObjectIO.write("salut", dataOutputStream);
		}finally {
//			socket.close();
		}
		

	}

	private static void process(Socket s, InputStream is, OutputStream os, DataInputStream dis,
			DataOutputStream dos){
		String readUTF;
		try {
			readUTF = (String) ObjectIO.write(dis);
			System.out.println("requête client : "+readUTF);
		} catch (IOException e) {
			process("erreur reading", e, MainTest.class);
		} catch (ClassNotFoundException e) {
			process("erreur reading", e, MainTest.class);
		}
		
		
	}

	private static void process(String t, Exception e, Class<?> c) {
		System.err.println(c.getName());
		System.err.println(t);
		e.printStackTrace();
		
	}

}
