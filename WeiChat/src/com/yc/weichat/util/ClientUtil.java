package com.yc.weichat.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.yc.weichat.server.Properties;

public class ClientUtil {
	
	public static DataInputStream dis;
	public static DataOutputStream dos;
	public static boolean isRunning = true;

	public static void connect() {
		Socket client;
		try {
			client = new Socket(Properties.IP, Properties.PORT);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMsg(String msg) {
		if(msg != null && msg != "") {
			try {
				System.out.println("msg:" + msg);
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(dos);
				//e.printStackTrace();
			}
		}
	}
	
	public static String receiveMsg() {
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dis);
			//e.printStackTrace();
		}
		return msg;
	}
	
	
}
