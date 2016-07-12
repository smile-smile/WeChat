package com.yc.weichat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
	
	public static List<MyChannel> all = new ArrayList<>();
	
	public static void main(String[] args) {
		new Server().connect();;
	}

	private void connect() {
		try {
			ServerSocket server = new ServerSocket(Properties.PORT);
			while(true) {
				Socket socket = server.accept();
				MyChannel channel = new MyChannel(socket);
				all.add(channel);
				new Thread(channel).start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
