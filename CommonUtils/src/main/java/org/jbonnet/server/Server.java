package org.jbonnet.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread {
	private Map<String, ClientServerSide> clients = new HashMap<>();
	private int port;
	private HandleError handleError;
	private ServerSocket serverSocket;
	private SocketProcessor socketProcessor;

	/**
	 * @param port
	 * @param handleError
	 * @param serverSocket
	 * @param socketProcessor
	 */
	public Server(int port, HandleError handleError, ServerSocket serverSocket, SocketProcessor socketProcessor) {
		super();
		this.port = port;
		this.handleError = handleError;
		this.serverSocket = serverSocket;
		this.socketProcessor = socketProcessor;
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			handleError.catchMe("error when init server ", e, Server.class);
		}
		while (true) {
			try {
				Socket accept = serverSocket.accept();
				ClientServerSide clientServerSide = new ClientServerSide(accept, socketProcessor);
				clientServerSide.start();

			} catch (IOException e) {
				handleError.catchMe("erreur accept", e, Server.class);
			}
		}
	}
}
