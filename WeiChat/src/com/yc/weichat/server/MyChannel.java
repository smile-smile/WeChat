package com.yc.weichat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;





import com.yc.weichat.util.CloseUtil;


public class MyChannel implements Runnable {
	
	private DataInputStream dis;
	private DataOutputStream dos;
	private String myId, otherId;
	private boolean isRunning = true;
	private String chatWays;
	
    public MyChannel() {
	}

	public MyChannel(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			myId = dis.readUTF();
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dis, dos);
//			e.printStackTrace();
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
			System.out.println(msg);
		} catch (IOException e) {
			isRunning = false;
			Server.all.remove(this);
			CloseUtil.closeAll(dis);
			//e.printStackTrace();
		}
		return msg;
	}
	
	

	/**
	 * 发送信息
	 * @param msg
	 * @return
	 */
	private void sendMsg(String msg) {
		if(msg != null && msg != "") {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRunning = false;
				Server.all.remove(this);
				CloseUtil.closeAll(dos);
				//e.printStackTrace();
			}
		}
	}
	
//	private void sendOthers(String msg, String chatWays) {
//		if(chatWays.equals(Properties.PRIVATE_CHAT)) {
//			for(MyChannel other : Server.all) {
//				if(this.otherId.equals(other.myId)) {
//					other.sendMsg(msg);
//				}
//			}
//		} else {
//			
//		}
//		
//	}
	
	private void dealMsg(String msg) {
		if(msg.startsWith(Properties.OTHER_ID)) {
			otherId = msg.substring(Properties.OTHER_ID.length());
		} else if(msg.startsWith(Properties.PRIVATE_CHAT)) {
			privateChat(msg);
		} else if(msg.startsWith(Properties.GROUP_CHAT)) {
			groupChat(msg);
		} 
		
	}
	

	private void groupChat(String msg) {
	// TODO Auto-generated method stub
	
	}

	private void privateChat(String msg) {
		for(MyChannel other : Server.all) {
			if(this.otherId.equals(other.myId)) {
				other.sendMsg(msg.concat("#" + myId));
			}
		}
	}

	@Override
	public void run() {
		while(isRunning) {
			dealMsg(receiveMsg());
		}
	}

	

	

}
