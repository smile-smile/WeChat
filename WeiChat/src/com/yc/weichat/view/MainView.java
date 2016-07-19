package com.yc.weichat.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import oracle.net.aso.g;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.weichat.entity.Account;
import com.yc.weichat.entity.Group;
import com.yc.weichat.server.Properties;
import com.yc.weichat.util.UIUtil;

import static com.yc.weichat.util.ClientUtil.*;
import static com.yc.weichat.util.UIUtil.*;

public class MainView {

	protected Shell shell;
	private Text txtfind;
	private Account acc, other;
	private Group group, addgroup;
	private List<Composite> list;
	private List<Composite> groupComposites;
	private List<Account> friends;
	private List<Group> groups;
	private Button btnChat, btnFriend, btnCollect, btnSetting;
	private Text textChatRecord;
	private Text textContent;
	StackLayout sLayout, sLayout2;
	Composite composite_5;
	Composite composite_6;
	Composite composite_12;
	Composite composite_13;
	Composite composite_14;
	Composite composite_15;
	Composite composite_16;
	Composite composite_17;
	Composite composite;
	ScrolledComposite scrolledComposite_3, scrolledComposite_4, scrolledComposite;
	
	Label lblOther;
	Label lbl_headPortait;
	Label lbl_name;
	Label lbl_sex;
	Label lbl_userId;
	Label lbl_remarkname;
	Label lbl__address;
	
	Label lab_friendSex;
	Label lab_friendAdress;
	Label lab_friendId;
	Label label_name;
	Label lab_Pic;
	Account addFriendAccount;
	
	boolean isPrivateChat = true;
	public static String chatWays;
	public static String otherId;
	public static String groupId;
	public static String sendMessage;
	public static String receiveMessage;
	private Text textGroupId;
	private Text textGroupName;
	private Text textGroupId2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainView window = new MainView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
	protected void createContents() {
		
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(MainView.class, "/images/icon.jpg"));
		shell.setSize(1035, 720);
		winCenter(shell);
		shell.setText("微信");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		loadData();
		
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		Image image2 = changeImage("images/press_message.png", 60, 60);
		Image image3 = changeImage("images/friend.png", 60, 60);
		Image image4 = changeImage("images/collect.png", 60, 60);
		Image image5 = changeImage("images/setting.png", 60, 60);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(new Color(shell.getDisplay(), 62,62,64));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(0, 0, 55, 55);
		btnNewButton.setImage(changeImage("images/not_pic.jpg", 60, 60));
		if(acc.getPic() != null) {
			btnNewButton.setImage(changeImage(acc.getPic(), 60, 60));
		}
		
		btnChat = new Button(composite_1, SWT.NONE);
		btnChat.setBounds(-1, 66, 55, 55);
		btnChat.setImage(image2);
		
		
		btnFriend = new Button(composite_1, SWT.NONE);
		btnFriend.setBounds(0, 136, 55, 55);
		btnFriend.setImage(image3);
		
		
		
		btnCollect = new Button(composite_1, SWT.NONE);
		btnCollect.setBounds(0, 210, 55, 55);
		btnCollect.setImage(image4);
		
		
		btnSetting = new Button(composite_1, SWT.NONE);
		btnSetting.setBounds(0, 609, 55, 55);
		btnSetting.setImage(image5);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.VERTICAL);
		sashForm_1.setEnabled(true);
		
		Composite composite_21 = new Composite(sashForm_1, SWT.NONE);
		
		txtfind = new Text(composite_21, SWT.BORDER);
		txtfind.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtfind.setText("请输入关键字");
		txtfind.setBounds(10, 20, 172, 30);
		
		//文本焦点事件  提示语显示与关闭
		txtfind.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				 Text text=(Text)e.widget;
				 if("请输入关键字".equals(text.getText()))
				   {
					 text.setText("");
				   }
				 text.setForeground(new Color(shell.getDisplay(), 0,0,0));
			}
			@Override
			public void focusLost(FocusEvent e) {
				 Text text=(Text)e.widget;
				 if("".equals(text.getText().trim()))
				 {
					 text.setText("请输入关键字");
					 text.setForeground(new Color(shell.getDisplay(), 200,200,200));
				 }
			}
		});

		
		Button btnGroupManager = new Button(composite_21, SWT.NONE);
		btnGroupManager.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showRightComposite(composite_17);
			}
		});
		btnGroupManager.setBounds(224, 15, 40, 40);
		Image image6 = changeImage("images/add.png", 60, 60);
		btnGroupManager.setImage(image6);
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setEnabled(false);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite_3, SWT.SEPARATOR);
		lblNewLabel.setText("New Label");
		
		sLayout = new StackLayout();
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setLayout(sLayout);
		
		composite_5 = new Composite(composite_4, SWT.NONE);
		sLayout.topControl = composite_5;
		
		composite_6 = new Composite(composite_4, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_6, SWT.VERTICAL);
		
		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		
		
		lblOther = new Label(composite_7, SWT.NONE);
		lblOther.setAlignment(SWT.CENTER);
		lblOther.setBounds(328, 10, 90, 24);
		
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(sashForm_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		Composite composite_10 = new Composite(scrolledComposite_1, SWT.NONE);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textChatRecord = new Text(composite_10, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textChatRecord.setEditable(false);
		scrolledComposite_1.setContent(composite_10);
		scrolledComposite_1.setMinSize(composite_10.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		
		
		
		Composite composite_8 = new Composite(sashForm_2, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(composite_8, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(MainView.class, "/images/face.png"));
		lblNewLabel_2.setBounds(4, 6, 30, 30);
		
		Label lblCollect = new Label(composite_8, SWT.NONE);
		lblCollect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog fd = new FileDialog(shell);
				fd.setText("选择文件");
				fd.setFilterExtensions(new String[]{"*.*"});
				fd.setFilterNames(new String[]{"*.*"});
				String path = fd.open();
			}
		});
		lblCollect.setImage(SWTResourceManager.getImage(MainView.class, "/images/222.png"));
		lblCollect.setBounds(36, 6, 30, 30);
		
		Label lblNewLabel_4 = new Label(composite_8, SWT.NONE);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(MainView.class, "/images/cut.png"));
		lblNewLabel_4.setBounds(68, 6, 30, 30);
		
		Label lblNewLabel_5 = new Label(composite_8, SWT.NONE);
		lblNewLabel_5.setImage(SWTResourceManager.getImage(MainView.class, "/images/video.png"));
		lblNewLabel_5.setBounds(100, 6, 30, 30);
		
		ScrolledComposite scrolledComposite_2 = new ScrolledComposite(sashForm_2, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite_2.setExpandHorizontal(true);
		scrolledComposite_2.setExpandVertical(true);
		
		Composite composite_11 = new Composite(scrolledComposite_2, SWT.NONE);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textContent = new Text(composite_11, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.CANCEL);
		scrolledComposite_2.setContent(composite_11);
		scrolledComposite_2.setMinSize(composite_11.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Composite composite_9 = new Composite(sashForm_2, SWT.NONE);
		
		Button btnSend = new Button(composite_9, SWT.NONE);
		btnSend.setBounds(560, 0, 92, 30);
		btnSend.setImage(changeImage("images/send.png", 100, 40));
		sashForm.setWeights(new int[] {5, 25, 1, 63});
		
		sashForm_2.setWeights(new int[] {37, 448, 37, 195, 31});
		addSendEvent(btnSend);
		
		
		
		//堆栈布局第二块面板设计   采用绝对布局
		composite_12 = new Composite(composite_4, SWT.NONE);
		composite_12.setLayout(null);
		
		composite_13 = new Composite(composite_4, SWT.NONE);
		composite_13.setVisible(false);
		
		composite = new Composite(sashForm_1, SWT.NONE);
		sLayout2 = new StackLayout();
		composite.setLayout(sLayout2);
		
		
		scrolledComposite_3 = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_3.setExpandHorizontal(true);
		scrolledComposite_3.setExpandVertical(true);
		
		scrolledComposite_4 = new ScrolledComposite(composite, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite_4.setExpandHorizontal(true);
		scrolledComposite_4.setExpandVertical(true);
		
		addPressEvent(btnChat, "images/press_message.png", scrolledComposite_3);
		
		composite_15 = new Composite(scrolledComposite_4, SWT.NONE);
		composite_15.setLayout(new RowLayout(SWT.HORIZONTAL));
		scrolledComposite_4.setContent(composite_15);
		scrolledComposite_4.setMinHeight((friends.size()+1) * 70);
		
		lbl_headPortait = new Label(composite_12, SWT.NONE);
		lbl_headPortait.setBounds(291, 139, 200, 200);
		lbl_headPortait.setAlignment(SWT.CENTER);
		
		lbl_name = new Label(composite_12, SWT.NONE);
		lbl_name.setBounds(272, 365, 250, 30);
		lbl_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lbl_name.setAlignment(SWT.CENTER);
		lbl_name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		
		lbl_userId = new Label(composite_12, SWT.NONE);
		lbl_userId.setBounds(351, 456, 200, 30);
		lbl_userId.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_userId.setAlignment(SWT.CENTER);
		
		lbl_sex = new Label(composite_12, SWT.NONE);
		lbl_sex.setBounds(351, 492, 200, 30);
		lbl_sex.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_sex.setAlignment(SWT.CENTER);
		
		lbl_remarkname = new Label(composite_12, SWT.NONE);
		lbl_remarkname.setBounds(351, 420, 200, 30);
		lbl_remarkname.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_remarkname.setAlignment(SWT.CENTER);
		
		lbl__address = new Label(composite_12, SWT.NONE);
		lbl__address.setBounds(351, 525, 200, 30);
		lbl__address.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl__address.setAlignment(SWT.CENTER);
		
		Label label1 = new Label(composite_12, SWT.NONE);
		label1.setBounds(272, 420, 73, 30);
		label1.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.NORMAL));
		label1.setAlignment(SWT.CENTER);
		label1.setEnabled(false);
		label1.setText("备注");
		
		Label label2 = new Label(composite_12, SWT.NONE);
		label2.setBounds(272, 456, 73, 30);
		label2.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label2.setAlignment(SWT.CENTER);
		label2.setEnabled(false);
		label2.setText("微信号");
		
		Label label3 = new Label(composite_12, SWT.NONE);
		label3.setAlignment(SWT.CENTER);
		label3.setBounds(272, 492, 73, 30);
		label3.setText("性别");
		label3.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label3.setEnabled(false);
		
		Label label4 = new Label(composite_12, SWT.NONE);
		label4.setBounds(272, 525, 73, 30);
		label4.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label4.setAlignment(SWT.CENTER);
		label4.setEnabled(false);
		label4.setText("地区");
		
		
		
		
		lab_Pic = new Label(composite_13, SWT.NONE);
		lab_Pic.setAlignment(SWT.CENTER);
		lab_Pic.setBounds(248, 97, 200, 200);
		lab_Pic.setImage(changeImage("images/not_pic.jpg", 200, 200));
		
		label_name = new Label(composite_13, SWT.NONE);
		label_name.setText("未设置");
		label_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		label_name.setAlignment(SWT.CENTER);
		label_name.setBounds(229, 325, 250, 30);
		
		Label label_5 = new Label(composite_13, SWT.NONE);
		label_5.setText("微信号");
		label_5.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label_5.setEnabled(false);
		label_5.setAlignment(SWT.CENTER);
		label_5.setBounds(237, 389, 65, 30);
		
		lab_friendId = new Label(composite_13, SWT.NONE);
		lab_friendId.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lab_friendId.setAlignment(SWT.CENTER);
		lab_friendId.setBounds(308, 389, 200, 30);
		
		Label label_7 = new Label(composite_13, SWT.NONE);
		label_7.setText("地区");
		label_7.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label_7.setEnabled(false);
		label_7.setBounds(248, 480, 65, 30);
		
		lab_friendAdress = new Label(composite_13, SWT.NONE);
		lab_friendAdress.setText("未设置");
		lab_friendAdress.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lab_friendAdress.setAlignment(SWT.CENTER);
		lab_friendAdress.setBounds(308, 480, 200, 30);
		
		Button but_addfriend = new Button(composite_13, SWT.NONE);
		but_addfriend.setText("加好友");
		but_addfriend.setBounds(229, 541, 250, 46);
		addFriends(but_addfriend);
		
		Label label_2 = new Label(composite_13, SWT.NONE);
		label_2.setText("性别");
		label_2.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		label_2.setEnabled(false);
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(237, 434, 65, 30);
		
		lab_friendSex = new Label(composite_13, SWT.NONE);
		lab_friendSex.setText("未设置");
		lab_friendSex.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lab_friendSex.setAlignment(SWT.CENTER);
		lab_friendSex.setBounds(308, 434, 200, 30);
		

		Button btn_find = new Button(composite_21, SWT.NONE);
		btn_find.setImage(SWTResourceManager.getImage(MainView.class, "/images/find.png"));
		btn_find.setBounds(188, 20, 30, 30);
		addFriends_find(btn_find);
		
		
		addPressEvent(btnFriend, "images/press_friend.png", scrolledComposite_4);
			
		composite_14 = new Composite(scrolledComposite_3, SWT.NONE);
		
		scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(composite_16);
		addPressEvent(btnCollect, "images/press_collect.png", scrolledComposite);
		
		showCenterComposite(scrolledComposite);
		
		composite_16 = new Composite(scrolledComposite, SWT.NONE);
		
		
		scrolledComposite_3.setContent(composite_14);
		scrolledComposite_3.setMinHeight((groups.size()+1)*70);
		composite_14.setLayout(new RowLayout(SWT.HORIZONTAL));

		
		
		
		sashForm_1.setWeights(new int[] {68, 593});
		
		Button btn_startchat = new Button(composite_12, SWT.NONE);
		btn_startchat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showRightComposite(composite_6);
				textChatRecord.setText("");
				lblOther.setText(other.getUserId());
				otherId = Properties.OTHER_ID.concat(lblOther.getText().trim());
				sendMsg(otherId);
				isPrivateChat = true;
			}
		});
		btn_startchat.setBounds(272, 583, 250, 46);
		btn_startchat.setText("发消息");
		
		composite_17 = new Composite(composite_4, SWT.NONE);
		
		
		Label label = new Label(composite_17, SWT.NONE);
		label.setText("创建讨论组");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		label.setBounds(10, 10, 186, 47);
		
		Label label_1 = new Label(composite_17, SWT.NONE);
		label_1.setText("id");
		label_1.setBounds(31, 130, 48, 24);
		
		Label label_3 = new Label(composite_17, SWT.NONE);
		label_3.setText("name");
		label_3.setBounds(31, 212, 48, 24);
		
		Label label_4 = new Label(composite_17, SWT.SEPARATOR);
		label_4.setText("New Label");
		label_4.setEnabled(false);
		label_4.setBounds(312, 10, 30, 750);
		
		Label label_6 = new Label(composite_17, SWT.NONE);
		label_6.setText("加入讨论组");
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		label_6.setBounds(348, 10, 186, 47);
		
		textGroupId = new Text(composite_17, SWT.BORDER);
		textGroupId.setBounds(101, 130, 195, 30);
		
		textGroupName = new Text(composite_17, SWT.BORDER);
		textGroupName.setBounds(99, 212, 195, 30);
		
		Button btnCreate = new Button(composite_17, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String groupId = textGroupId.getText().trim();
				String groupName = textGroupName.getText().trim();
				sendMsg(Properties.CREATE_GROUP + "#" + groupId + "#" + groupName + "#" + acc.getUserId());
			}
		});
		btnCreate.setBounds(182, 288, 114, 34);
		btnCreate.setText("创建");
		
		textGroupId2 = new Text(composite_17, SWT.BORDER);
		textGroupId2.setBounds(436, 127, 195, 30);
		
		Label label_8 = new Label(composite_17, SWT.NONE);
		label_8.setText("id");
		label_8.setBounds(372, 126, 48, 24);
		
		Button btnJoin = new Button(composite_17, SWT.NONE);
		btnJoin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendMsg(Properties.JOIN_GROUP + "#" + textGroupId2.getText().trim() + "#" + acc.getUserId());
			}
		});
		btnJoin.setText("加入");
		btnJoin.setBounds(517, 207, 114, 34);
		
		
		listFriendInfo();
		showFriendInfo();
		listGroupInfo();
		showGroupInfo();
		
		receive();
	}

	

	private void receive() {
		new Thread(){
			@Override
			public void run() {
				while(isRunning) {
					receiveMessage = receiveMsg();
					System.out.println("receiveMessage:" + receiveMessage);
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							dealMessage(receiveMessage);
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	private void dealMessage(String receiveMessage) {
		if(receiveMessage.startsWith(Properties.PRIVATE_CHAT)) {
			privateChat(receiveMessage);
		} else if(receiveMessage.startsWith(Properties.ACCOUNT)) {
			findAccount(receiveMessage);
		} else if(receiveMessage.startsWith(Properties.NULL_ACCOUNT)) {
			notFindAccount(receiveMessage);
		} else if(receiveMessage.startsWith(Properties.ADD_FRIEND_SUCCESS)) {
			addFriendSuccess();
		} else if(receiveMessage.startsWith(Properties.ADD_FRIEND_FAIL)) {
			addFriendFail();
		} else if(receiveMessage.startsWith(Properties.CREATE_GROUP_SUCCESS)) {
			createGroup();
		} else if(receiveMessage.startsWith(Properties.CREATE_GROUP_FAIL)) {
			createGroupFail();
		} else if(receiveMessage.startsWith(Properties.JOIN_GROUP_SUCCESS)) {
			joinGroupSuccess(receiveMessage);
		} else if(receiveMessage.startsWith(Properties.JOIN_GROUP_FAIL)) {
			joinGroupFail();
		} else if(receiveMessage.startsWith(Properties.GROUP_CHAT)) {
			groupChat(receiveMessage);
		}
	}
	
	private void groupChat(String msg) {
		String[] str = msg.split("#");
		String a = groupId;
		if(a == null) {
			a = "";
		}
		groupId = Properties.GROUP_ID.concat(str[1]);
		sendMsg(groupId);
		lblOther.setText(str[1]);
		showRightComposite(composite_6);
		if(a.equals(groupId)) {
			textChatRecord.append(str[2] + ":  " + str[0].substring(Properties.GROUP_CHAT.length()) + "\r\n\r\n");
		} else {
			textChatRecord.setText(str[2] + ":  " + str[0].substring(Properties.GROUP_CHAT.length()) + "\r\n\r\n");
		}
		isPrivateChat = false;
	}

	private void joinGroupFail() {
		MessageBox mb = new MessageBox(shell);
		mb.setText("提示");
		mb.setMessage("加入讨论组失败");
		mb.open();
		textGroupId2.setText("");
	}

	private void joinGroupSuccess(String msg) {
		MessageBox mb = new MessageBox(shell);
		mb.setText("提示");
		mb.setMessage("加入讨论组成功");
		mb.open();
		String[] str = msg.split("#");
		Group g = new Group();
		g.setId(str[2]);
		g.setName(str[3]);
		g.setAdmin(str[4]);
		groupList.add(g);
		groups.add(g);
		
		Composite c = new Composite(composite_14, SWT.BORDER);
		c.setLayout(null);
		Label name = new Label(c, SWT.LEFT);
		name.setBounds(0, 0, 225, 40);
		name.setText(g.getName());
		name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		Label id = new Label(c, SWT.LEFT);
		id.setBounds(0, 45, 225, 25);
		id.setText(g.getId());
		groupComposites.add(c);
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = g;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
				
			}
		});
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = g;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
			}

		});
		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = g;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
			}

		});
		scrolledComposite_3.setMinHeight((groups.size()+1) * 70);
		composite_14.layout(true);
	}

	private void createGroupFail() {
		MessageBox mb = new MessageBox(shell);
		mb.setText("提示");
		mb.setMessage("创建讨论组失败");
		mb.open();
	}

	private void createGroup() {
		String groupId = textGroupId.getText().trim();
		String groupName = textGroupName.getText().trim();
		addgroup = new Group(groupId, groupName, acc.getUserId());
		groupList.add(addgroup);
		groups.add(addgroup);
		MessageBox mb = new MessageBox(shell);
		mb.setText("提示");
		mb.setMessage("创建讨论组成功");
		mb.open();
		addGroupComposite();
	}

	private void addFriendFail() {
		MessageBox mBox=new MessageBox(shell);
		mBox.setText("添加好友");
		mBox.setMessage("已经是您的好友");
		mBox.open();
	}

	private void addFriendSuccess() {
		MessageBox mBox=new MessageBox(shell);
		mBox.setText("添加好友");
		mBox.setMessage("添加好友成功");
		friends.add(addFriendAccount);
		mBox.open();
		addFriendComposite();
	}
	
	private void addGroupComposite() {
		Composite c = new Composite(composite_14, SWT.BORDER);
		c.setLayout(null);
		Label name = new Label(c, SWT.LEFT);
		name.setBounds(0, 0, 225, 40);
		name.setText(addgroup.getName());
		name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		Label id = new Label(c, SWT.LEFT);
		id.setBounds(0, 45, 225, 25);
		id.setText(addgroup.getId());
		groupComposites.add(c);
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = addgroup;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
			}
		});
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = addgroup;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
			}

		});
		id.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				group = addgroup;
				showRightComposite(composite_6);
				sendMsg(Properties.GROUP_ID .concat(group.getId()));
				isPrivateChat = false;
				lblOther.setText(group.getId());
			}

		});
		scrolledComposite_3.setMinHeight((groups.size()+1) * 70);
		composite_14.layout(true);
	}

	private void addFriendComposite() {
		
		Composite c = new Composite(composite_15, SWT.BORDER);
		c.setLayout(null);
		Label pic = new Label(c, SWT.NONE);
		pic.setBounds(0, 0, 70, 70);
		if(addFriendAccount.getPic() != null) {
			try {
				pic.setImage(changeImage(new FileInputStream(System.getProperty("user.dir") + 
						"\\src\\images\\" + addFriendAccount.getUserId() + ".jpg"), 70, 70));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pic.setImage(changeImage("images/not_pic.jpg", 70, 70));
		}
		
		Label name = new Label(c, SWT.NONE);
		name.setBounds(90, 0, 165, 70);
		name.setText(addFriendAccount.getUserId());
		list.add(c);
		friends.add(addFriendAccount);
		
		Label image = (Label) c.getChildren()[0];
		Label userid = (Label)c.getChildren()[1];
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				other = addFriendAccount;
				flushFriendInfo();
			}
		});
		image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				other = addFriendAccount;
				flushFriendInfo();
			}

		});
		userid.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				other = addFriendAccount;
				flushFriendInfo();
			}

		});
		scrolledComposite_4.setMinHeight((friends.size()+1) * 70);
		composite_15.layout(true);
		
	}

	private void findAccount(String message) {
		addFriendAccount = getAccount(message);
		if(addFriendAccount.getName() != null) {
			label_name.setText(addFriendAccount.getName());
		} else {
			label_name.setText("未设置");
		}
		lab_friendId.setText(addFriendAccount.getUserId());
		if(addFriendAccount.getSex() != null) {
			lab_friendSex.setText(addFriendAccount.getSex());
		} else {
			lab_friendSex.setText("未设置");
		}
		if(addFriendAccount.getAddress() != null) {
			lab_friendAdress.setText(addFriendAccount.getAddress());
		} else{
			lab_friendAdress.setText("未设置");
		}
		if(addFriendAccount.getPic()!=null)
		{
			lab_Pic.setImage(changeImage(addFriendAccount.getPic(), 200, 200));
		} else {
			lab_Pic.setImage(changeImage("images/not_pic.jpg", 200, 200));
		}
		showRightComposite(composite_13);
	}
	
	private void notFindAccount(String receiveMessage) {
		MessageBox mBox = new MessageBox(shell,SWT.YES|SWT.NO|SWT.ICON_INFORMATION);
		mBox.setText("加好友");
		mBox.setMessage("该账户不存在");
		mBox.open();
//		if(mBox.open()==SWT.YES)
//		{
//			InputDialog id=new InputDialog(shell, "邀请你的朋友注册微信","输入邮箱","",null);
//			id.open();
//		}
	}
	
	
	private void privateChat(String msg) {
		String[] str = msg.split("#");
		String a = otherId;
		if(a == null) {
			a = "";
		}
		System.out.println(a);
		otherId = Properties.OTHER_ID.concat(str[1]);
		System.out.println(otherId);
		sendMsg(otherId);
		lblOther.setText(str[1]);
		showRightComposite(composite_6);
		if(a.equals(otherId)) {
			textChatRecord.append(str[1] + ":  " + str[0].substring(Properties.PRIVATE_CHAT.length()) + "\r\n\r\n");
		} else {
			textChatRecord.setText(str[1] + ":  " + str[0].substring(Properties.PRIVATE_CHAT.length()) + "\r\n\r\n");
		}
		isPrivateChat = true;
	}
	
	private void showFriendInfo() {
		for(int i=0; i<list.size(); i++) {
			Composite c = list.get(i);
			Label image = (Label) c.getChildren()[0];
			Label name = (Label)c.getChildren()[1];
			int count = i;
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					other = friends.get(count);
					flushFriendInfo();
				}
			});
			image.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					other = friends.get(count);
					flushFriendInfo();
				}

			});
			name.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					other = friends.get(count);
					flushFriendInfo();
				}

			});
		}
	}
	
	private void flushFriendInfo() {
		if(other.getPic() != null) {
			lbl_headPortait.setImage(changeImage("images/" + other.getUserId() + ".jpg", 200, 200));
		} else {
			lbl_headPortait.setImage(changeImage("images/not_pic.jpg", 200, 200));
		}
		if(other.getName() != null) {
			lbl_name.setText(other.getName());
		} else {
			lbl_name.setText("未设置");
		}
		lbl_remarkname.setText("未设置");
		lbl_userId.setText(other.getUserId());
		if(other.getSex() != null) {
			lbl_sex.setText(other.getSex());
		} else {
			lbl_sex.setText("未设置");
		}
		if(other.getAddress() != null) {
			lbl__address.setText(other.getAddress());
		} else {
			lbl__address.setText("未设置");
		}
		showRightComposite(composite_12);
	}

	/**
	 * 加载数据
	 */
	private void loadData() {
		try {
			acc = ((Account)UIUtil.user).clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * 获取好友信息
		 */
		sendMsg(Properties.REQUEST_FRIEND_LIST);
		friendList = new ArrayList<Account>();
		int friendSize = Integer.parseInt(receiveMsg());
		for(int i=0; i<friendSize; i++) {
			String msg = receiveMsg();
			friendList.add(getAccount(msg));
		}
		
//		FriendsService fs = new FriendsServiceimpl();
//		UIUtil.friendList = fs.listFriendsInfo(acc.getUserId());
		
		friends = new ArrayList<Account>();
		for(Account account : friendList) {
			if(account.getPic() != null) {
				File file = new File(System.getProperty("user.dir") + "\\src\\images\\" + account.getUserId() + ".jpg");
				if(!file.exists()) {
					UIUtil.saveBit(account.getPic(), account.getUserId());
				}
			}
			friends.add(account);
		}
		
		/*
		 * 获取群讨论组
		 */
		sendMsg(Properties.REQUEST_GROUP_LIST);
		groupList = new ArrayList<Group>();
		int groupSize = Integer.parseInt(receiveMsg());
		for(int i=0; i<groupSize; i++) {
			String msg = receiveMsg();
			groupList.add(getGroup(msg));
		}
		groups = new ArrayList<Group>();
		for(Group g : groupList) {
			groups.add(g);
		}
	}

	/**
	 * 列出朋友列表
	 */
	private void listFriendInfo() {
		scrolledComposite_4.setMinHeight((friends.size()+1) * 70);
		list = new ArrayList<Composite>();
		for(int i=0; i<friends.size(); i++) {
			other = friends.get(i);
			Composite c = new Composite(composite_15, SWT.BORDER);
			c.setLayout(null);
			Label pic = new Label(c, SWT.NONE);
			pic.setBounds(0, 0, 70, 70);
			if(other.getPic() != null) {
				try {
					pic.setImage(changeImage(new FileInputStream(System.getProperty("user.dir") + 
							"\\src\\images\\" + other.getUserId() + ".jpg"), 70, 70));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				pic.setImage(changeImage("images/not_pic.jpg", 70, 70));
			}
			
			Label name = new Label(c, SWT.NONE);
			name.setBounds(90, 0, 135, 70);
			name.setText(other.getUserId());
	
			list.add(c);
		}		
	}
	
	/**
	 * 列出群信息
	 */
	private void listGroupInfo() {
		scrolledComposite_3.setMinHeight((groups.size()+1)*70);
		groupComposites = new ArrayList<Composite>();
		for(int i=0; i<groups.size(); i++) {
			group = groups.get(i);
			Composite c = new Composite(composite_14, SWT.BORDER);
			c.setLayout(null);
			Label name = new Label(c, SWT.LEFT);
			name.setBounds(0, 0, 225, 40);
			name.setText(group.getName());
			name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
			Label id = new Label(c, SWT.LEFT);
			id.setBounds(0, 45, 225, 25);
			id.setText(group.getId());
			groupComposites.add(c);
			
		}		
	}
	
	/**
	 * 展示群信息
	 */
	private void showGroupInfo() {
		for(int i=0; i<groups.size(); i++) {
			Composite c = groupComposites.get(i);
			Label name = (Label) c.getChildren()[0];
			Label id = (Label)c.getChildren()[1];
			int count = i;
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					group = groups.get(count);
					showRightComposite(composite_6);
					sendMsg(Properties.GROUP_ID .concat(group.getId()));
					isPrivateChat = false;
					lblOther.setText(group.getId());
				}
			});
			id.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					group = groups.get(count);
					showRightComposite(composite_6);
					sendMsg(Properties.GROUP_ID .concat(group.getId()));
					isPrivateChat = false;
					lblOther.setText(group.getId());
				}

			});
			name.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					group = groups.get(count);
					showRightComposite(composite_6);
					sendMsg(Properties.GROUP_ID .concat(group.getId()));
					isPrivateChat = false;
					lblOther.setText(group.getId());
				}

			});
		}
	}
	
	

	private void showCenterComposite(ScrolledComposite sc) {
		sLayout2.topControl = sc;
		scrolledComposite_3.setVisible(false);
		scrolledComposite_4.setVisible(false);
		scrolledComposite.setVisible(false);
		sc.setVisible(true);
	}
	

	private void showRightComposite(Composite composite) {
		sLayout.topControl = composite;
		composite_5.setVisible(false);
		composite_6.setVisible(false);
		composite_12.setVisible(false);
		composite_13.setVisible(false);
		composite_17.setVisible(false);
		composite.setVisible(true);
	}
	
	public void addSendEvent(final Button btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Button b = (Button)e.widget;
				b.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/press_send.png"));
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				Button b = (Button)e.widget;
				b.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/send.png"));
				if(textContent.getText().trim().equals("")) {
					MessageBox mb = new MessageBox(shell);
					mb.setText("提示");
					mb.setMessage("发送消息不能为空");
					return;
				}
				sendMessage = textContent.getText().trim();
				if(isPrivateChat) {
					sendMsg(Properties.PRIVATE_CHAT.concat(sendMessage));
				} else {
					sendMsg(Properties.GROUP_CHAT.concat(sendMessage));
				}
				
				textChatRecord.append("我:  " +sendMessage + "\r\n\r\n");
				textContent.setText("");
			}
		});
		btn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override//放上
			public void mouseHover(MouseEvent e) {
				Button b = (Button)e.widget;
				b.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/press_send.png"));
			}
			@Override//移开
			public void mouseExit(MouseEvent e) {
				Button b = (Button)e.widget;
				b.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/send.png"));
			}
			
		});
	}

	private void addPressEvent(Button button, String path, ScrolledComposite sc) {
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Button btn = (Button)e.widget;
				setGrayBtn();
				btn.setImage(changeImage(path, 60, 60));
				showCenterComposite(sc);
				showRightComposite(composite_5);
			}
			
		});
		
	}
	
	private void setGrayBtn() {
		btnChat.setImage(changeImage("images/message.png", 60, 60));
		btnFriend.setImage(changeImage("images/friend.png", 60, 60));
		btnCollect.setImage(changeImage("images/collect.png", 60, 60));
	}
	
	private void addFriends_find(Button btn_find) {
		btn_find.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String userId=txtfind.getText();
				sendMsg(Properties.FIND_ACCOUNT + "#" + userId);
			}
		});
	}
	
	private void addFriends(Button but_addfriend) {
		but_addfriend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String friendId=lab_friendId.getText();
				String friendName=label_name.getText();
				if(friendName.equals("未设置")) {
					friendName = null;
				}
				String userId=acc.getUserId();
				StringBuilder sb = new StringBuilder();
				sb.append(Properties.ADD_FRIEND + "#");
				sb.append(userId + "#");
				sb.append(friendId + "#");
				sb.append(friendName + "#");
				sb.append(acc.getName());
				String message = sb.toString();
				sendMsg(message);
				
			}
		});
	}
}
