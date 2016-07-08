package com.yc.weichat.view;


import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import static com.yc.weichat.util.UIUtil.*;

import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.weichat.util.UIUtil;

public class Login {

	protected Shell shell;
	private Label lblClose;
	private Text txtAccount;
	private Text txtPassword;
	private Label lblNewLabel;
	private Label btnLogin;
	private Label labRegister;
	private Label label;
	private Label label_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		shell = new Shell(Display.getDefault(), SWT.NONE);
		addMoveEvent(shell);
		shell.setSize(420, 600);
		winCenter(shell);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 60, 30);
		lblNewLabel.setText("微信");
		
		lblClose = new Label(shell, SWT.NONE);
		lblClose.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));
		addCloseEvent(lblClose);
		lblClose.setBounds(369, 10, 39, 20);
		
		Label lblImage = new Label(shell, SWT.NONE);
		Image image = changeImage("images/icon2.jpg", 150, 150);
		lblImage.setImage(image);
		lblImage.setAlignment(SWT.CENTER);
		lblImage.setBounds(135, 115, 150, 150);
		
		label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setBounds(45, 300, 80, 40);
		label.setText("手机号");
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_1.setBounds(45, 370, 80, 40);
		label_1.setText("密   码");
		
		txtAccount = new Text(shell, SWT.BORDER|SWT.CENTER);
		txtAccount.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		txtAccount.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		txtAccount.setBounds(142, 300, 235, 40);
		
		txtPassword = new Text(shell, SWT.BORDER|SWT.PASSWORD|SWT.CENTER);
		txtPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		txtPassword.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		txtPassword.setBounds(142, 370, 235, 40);
		
		btnLogin = new Label(shell, SWT.NONE);
		btnLogin.setImage(SWTResourceManager.getImage(Login.class, "/images/login.png"));
		btnLogin.setBounds(52, 455, 315, 58);
		addLoginEvent(btnLogin);
		
		labRegister = new Label(shell, SWT.NONE);
		labRegister.setBounds(190, 548, 40, 25);
		labRegister.setText("注册");
		labRegister.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
		addRegisterEvent(labRegister);
		
	}
	
	/**
	 * 登录
	 * @param lblLogin
	 */
	public void addLoginEvent(final Label lblLogin) {
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/login_press.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				Label label = (Label)e.widget;
				Rectangle r = label.getBounds();
				if(e.x >=0 && e.x <= r.width && e.y >=0 && e.y <= r.height) {
					lblLogin.getShell().dispose();
					new MainView().open();
				}
			}
		});
		lblLogin.addMouseTrackListener(new MouseTrackAdapter() {
			@Override//放上
			public void mouseHover(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/login_press.png"));
			}
			@Override//移开
			public void mouseExit(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/login.png"));
			}
			
		});
	}
	
	/**
	 * 注册
	 * @param lblRegister
	 */
	public void addRegisterEvent(final Label lblRegister) {
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_BLUE));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				Register window = new Register();
				window.open();
			}
			
			
			
		});
		lblRegister.addMouseTrackListener(new MouseTrackAdapter() {
			@Override//放上
			public void mouseHover(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_BLUE));
			}
			@Override//移开
			public void mouseExit(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
			}
			
		});
	}
}
