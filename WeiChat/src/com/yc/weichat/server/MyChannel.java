package com.yc.weichat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.yc.weichat.entity.Account;
import com.yc.weichat.service.AccountService;
import com.yc.weichat.service.FriendsService;
import com.yc.weichat.service.impl.AccountServiceimpl;
import com.yc.weichat.service.impl.FriendsServiceimpl;
import com.yc.weichat.util.CloseUtil;


public class MyChannel implements Runnable {
	
	private DataInputStream dis;
	private DataOutputStream dos;
	private String myId, otherId;
	private boolean isRunning = true;
	
    public MyChannel() {
	}

	public MyChannel(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
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

	private void dealMsg(String msg) {
		if(msg.startsWith(Properties.MY_ID)) {
			myId = msg.substring(Properties.MY_ID.length());
		} else if(msg.startsWith(Properties.OTHER_ID)) {
			otherId = msg.substring(Properties.OTHER_ID.length());
		} else if(msg.startsWith(Properties.PRIVATE_CHAT)) {
			privateChat(msg);
		} else if(msg.startsWith(Properties.GROUP_CHAT)) {
			groupChat(msg);
		} else if(msg.startsWith(Properties.ACCOUNT_ID_PASSWORD)) {
			doLogin(msg);
		} else if(msg.startsWith(Properties.REGISTER)) {
			doRegister(msg);
		} else if(msg.equals(Properties.REQUEST_FRIEND_LIST)) {
			doListFriendInfo();
		} else if(msg.startsWith(Properties.ADD_FRIEND)) {
			doAddFriend(msg);
		} else if(msg.startsWith(Properties.FIND_ACCOUNT)) {
			doFindAccount(msg);
		} 
		
	}
	

	private void doFindAccount(String msg) {
		String[] str = msg.split("#");
		AccountService as = new AccountServiceimpl();
		Account ac = as.findAccount(str[1]);
		if(ac == null) {
			sendMsg(Properties.NULL_ACCOUNT);
		} else {
			sendMsg(setAccountInfo(ac));
		}
		
		
	}

	private void doAddFriend(String msg) {
		String[] str = msg.split("#");
		FriendsService fs = new FriendsServiceimpl();
		boolean isSuccess = (fs.addFriend(str[1], str[2], str[3]) && fs.addFriend(str[2], str[1], str[4]));
		if(isSuccess) {
			sendMsg(Properties.ADD_FRIEND_SUCCESS);
		} else {
			sendMsg(Properties.ADD_FRIEND_FAIL);
		}
	}

	private void doListFriendInfo() {
		FriendsService fs = new FriendsServiceimpl();
		List<Account> list = fs.listFriendsInfo(myId);
		sendMsg(String.valueOf(list.size()));
		for(int i=0; i<list.size(); i++) {
			Account acc = list.get(i);
			sendMsg(setAccountInfo(acc));
		}
	}

	private void doRegister(String msg) {
		String[] str = msg.split("#");
		String userId = str[1];
		String password = str[2];
		String phone = str[3];
		String email = str[4];
		String name = str[5];
		if(str[5].equals(Properties.NULL)) {
			name = null;
		}
		String sex = str[6];
		if(sex.equals(Properties.NULL)) {
			sex = null;
		}
		String address = str[7];
		if(address.equals(Properties.NULL)) {
			address = null;
		}
		AccountService as = new AccountServiceimpl();
		Account acc = new Account();
		acc.setUserId(userId);
		acc.setPassword(password);
		acc.setPhone(phone);
		acc.setEmail(email);
		acc.setName(name);
		acc.setSex(sex);
		acc.setAddress(address);
		boolean isSuccess = as.register(acc);
		if(isSuccess) {
			sendMsg(Properties.RETURN_TURE);
		} else {
			sendMsg(Properties.RETURN_FALSE);
		}
	}

	private void doLogin(String msg) {
		String[] str = msg.split("#");
		myId = str[1];
		AccountService as = new AccountServiceimpl();
		Account acc = as.login(str[1], str[2]);
		if(acc == null) {
			sendMsg(Properties.NULL_ACCOUNT);
		} else {
			String message = setAccountInfo(acc);
			sendMsg(message);
		}
		
		
	}

	private String setAccountInfo(Account acc) {
		StringBuilder sb = new StringBuilder();
		sb.append(Properties.ACCOUNT + "#");
		sb.append(acc.getUserId() + "#");
		sb.append(acc.getPassword() + "#");
		sb.append(acc.getPhone() + "#");
		sb.append(acc.getEmail() + "#");
		if(acc.getName() != null) {
			sb.append(acc.getName() + "#");
		} else {
			sb.append(Properties.NULL + "#");
		}
		if(acc.getSex() != null) {
			sb.append(acc.getSex() + "#");
		} else {
			sb.append(Properties.NULL + "#");
		}
		if(acc.getAddress() != null) {
			sb.append(acc.getAddress());
		} else {
			sb.append(Properties.NULL);
		}
		return sb.toString();
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
			synchronized (this) {
				dealMsg(receiveMsg());
			}
			
		}
	}


}
