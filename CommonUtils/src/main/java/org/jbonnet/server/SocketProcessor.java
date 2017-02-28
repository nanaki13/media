package org.jbonnet.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface SocketProcessor {

	public void process(Socket socket, InputStream is, OutputStream os, DataInputStream dis, DataOutputStream dos);

}
