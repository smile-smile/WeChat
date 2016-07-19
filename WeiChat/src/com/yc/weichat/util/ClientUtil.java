package com.yc.weichat.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.yc.weichat.entity.Account;
import com.yc.weichat.entity.Group;
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
	
	public static Account getAccount(String message) {
		String[] str = message.split("#");
		Account ac = new Account();
		ac.setUserId(str[1]);
		ac.setPassword(str[2]);
		ac.setPhone(str[3]);
		ac.setEmail(str[4]);
		if(str[5].equals(Properties.NULL)) {
			ac.setName(null);
		} else {
			ac.setName(str[5]);
		}
		if(str[6].equals(Properties.NULL)) {
			ac.setSex(null);
		} else {
			ac.setSex(str[6]);
		}
		if(str[7].equals(Properties.NULL)) {
			ac.setAddress(null);
		} else {
			ac.setAddress(str[7]);
		}
		return ac;
	}
	
	public static Group getGroup(String message) {
		String[] str = message.split("#");
		Group group = new Group();
		group.setId(str[1]);
		group.setName(str[2]);
		group.setAdmin(str[3]);
		return group;
	}
}
