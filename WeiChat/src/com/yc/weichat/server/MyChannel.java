package com.yc.weichat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Magenta;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.internal.tweaklets.AllowGrabFocus;


public class MyChannel implements Runnable {
	
	private DataInputStream dis;
	private DataOutputStream dos;
	private String id;
	private boolean isRunning = true;
	private boolean isConnect = true;
	private boolean isGroup;
	
    public MyChannel() {
	}

	public MyChannel(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			isGroup = dis.readBoolean();
			id = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收信息
	 * @return
	 */
	private String receiveMsg() {
		String msg = "";
		try {
			msg = dis.readUTF();
			isConnect = dis.readBoolean();
			if(isConnect == false) {
				dis.close();
				dos.close();
				Server.all.remove(this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	private String sendMsg(String msg) {
		if(msg != null && msg != "") {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	private void sendOthers(String msg, boolean isGroup) {
		if(isGroup) {
			for(MyChannel other : Server.all) {
				other.sendMsg(msg);
			}
		} else {
			for(MyChannel other : Server.all) {
				
			}
		}
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {
			if(isConnect) {
				sendOthers(receiveMsg(), isGroup);
			} else {
				
			}
			
		}
	}

	

	

}
