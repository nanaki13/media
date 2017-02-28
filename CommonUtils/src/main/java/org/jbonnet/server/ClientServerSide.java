package org.jbonnet.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public  class ClientServerSide extends Thread  {
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	private SocketProcessor soc;
	
	public ClientServerSide(Socket psocket,SocketProcessor psoc) throws IOException{
		socket = psocket;
		is = psocket.getInputStream();
		os = psocket.getOutputStream();
		dis = new DataInputStream(is);
		dos = new DataOutputStream(os);
		soc = psoc;
	}
	public void run(){
		soc.process(socket,is,os,dis,dos);
		
	}
	
	
	public void close() throws IOException {
		socket.close();
		
	}
	
}
