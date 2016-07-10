package com.yc.weichat.view;

import static com.yc.weichat.util.UIUtil.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.rmi.CORBA.Stub;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.weichat.entity.Account;
import com.yc.weichat.service.AccountService;
import com.yc.weichat.service.impl.AccountServiceimpl;

public class Register {

	protected Shell shell;
	private Text textAccount;
	private Text textPassword;
	private Text textEmail;
	private Text textName;
	private Text textAddress;
	private Account acc;
	Button btnMan, btnWoman;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Register window = new Register();
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
	Composite composite21, composite22;
	private Text textPhone;
	protected void createContents() {
		acc = new Account();
		shell = new Shell(Display.getDefault(), SWT.NONE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		addMoveEvent(shell);
		shell.setSize(495, 600);
		winCenter(shell);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		Composite composite1 = new Composite(sashForm, SWT.NONE);
		composite1.setLayout(null);
		
		Label label_4 = new Label(composite1, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.BOLD));
		label_4.setBounds(10, 10, 59, 39);
		label_4.setText("注册");
		
		Label lblClose = new Label(composite1, SWT.NONE);
		lblClose.setBounds(454, 0, 39, 24);
		lblClose.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));
		addCloseEvent(lblClose);
		
		StackLayout sLayout = new StackLayout();
		Composite composite2 = new Composite(sashForm, SWT.NONE);
		composite2.setLayout(sLayout);
		
		composite21 = new Composite(composite2, SWT.NONE);
		
		Label label = new Label(composite21, SWT.NONE);
		label.setText("必填信息");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(220, 67, 90, 24);
		
		Label label1 = new Label(composite21, SWT.NONE);
		label1.setText("微信号");
		label1.setBounds(66, 145, 90, 24);
		
		textAccount = new Text(composite21, SWT.BORDER);
		textAccount.setBounds(162, 142, 246, 30);
		
		Label label_2 = new Label(composite21, SWT.NONE);
		label_2.setText("密   码");
		label_2.setBounds(66, 215, 90, 24);
		
		textPassword = new Text(composite21, SWT.BORDER | SWT.PASSWORD);
		textPassword.setBounds(162, 212, 246, 30);
		
		Label label_9 = new Label(composite21, SWT.NONE);
		label_9.setBounds(66, 285, 90, 24);
		label_9.setText("手机号");
		
		Label label_3 = new Label(composite21, SWT.NONE);
		label_3.setText("邮   箱");
		label_3.setBounds(66, 355, 90, 24);
		
		textEmail = new Text(composite21, SWT.BORDER);
		textEmail.setBounds(162, 352, 246, 30);
		
		Label lblNext = new Label(composite21, SWT.CENTER);
		lblNext.setText("下一步");
		lblNext.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNext.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblNext.setAlignment(SWT.CENTER);
		lblNext.setBounds(155, 434, 220, 40);
		addNextEvent(lblNext);
		
		sLayout.topControl = composite21;
		composite21.setVisible(true);
		
		
		
		textPhone = new Text(composite21, SWT.BORDER);
		textPhone.setBounds(162, 282, 246, 30);
		
		composite22 = new Composite(composite2, SWT.NONE);
		
		Label label_5 = new Label(composite22, SWT.NONE);
		label_5.setText("选填信息");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setBounds(215, 22, 90, 24);
		
		Label label_6 = new Label(composite22, SWT.NONE);
		label_6.setText("名字");
		label_6.setBounds(67, 258, 90, 24);
		
		textName = new Text(composite22, SWT.BORDER);
		textName.setBounds(163, 252, 246, 30);
		
		Label label_7 = new Label(composite22, SWT.NONE);
		label_7.setText("地区");
		label_7.setBounds(67, 309, 90, 24);
		
		textAddress = new Text(composite22, SWT.BORDER);
		textAddress.setBounds(163, 306, 246, 30);
		
		Label label_8 = new Label(composite22, SWT.NONE);
		label_8.setText("性别");
		label_8.setBounds(67, 358, 90, 24);
		
		Label lblRigister = new Label(composite22, SWT.CENTER);
		lblRigister.setText("注册");
		lblRigister.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblRigister.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblRigister.setAlignment(SWT.CENTER);
		lblRigister.setBounds(155, 434, 220, 40);
		addRegisterEvent(lblRigister);
		
		btnMan = new Button(composite22, SWT.RADIO);
		btnMan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				acc.setSex("男");
			}
		});
		btnMan.setBounds(194, 358, 80, 24);
		btnMan.setText("男");
		
		btnWoman = new Button(composite22, SWT.RADIO);
		btnWoman.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				acc.setSex("女");
			}
		});
		btnWoman.setText("女");
		btnWoman.setBounds(305, 358, 80, 24);
		
		Label lblPic = new Label(composite22, SWT.NONE);
		lblPic.setAlignment(SWT.CENTER);
		lblPic.setBounds(205, 68, 100, 100);
		
		lblPic.setImage(changeImage("images/not_pic.jpg", 100, 100));
		
		
		
		Button btnImportPic = new Button(composite22, SWT.NONE);
		btnImportPic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(shell);
				fileDialog.setText("打开图片");
				fileDialog.setFilterExtensions(new String[]{"*.jpg", "*.png", "*.gif"});
				fileDialog.setFilterNames(new String[]{"*.jpg", "*.png", "*.gif"});
				String picPath = fileDialog.open();
				if(picPath != null) {
					try {
						InputStream in = new FileInputStream(picPath);
						acc.setPic(in);
						lblPic.setImage(changeImage(picPath, 100, 100));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}  
			}
		});
		btnImportPic.setBounds(205, 189, 100, 34);
		btnImportPic.setText("上传图片");
		sashForm.setWeights(new int[] {1, 9});

	}

	private void addNextEvent(Label lblNext) {
		
		lblNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(textAccount.getText().trim() == "" || textPassword.getText().trim() == "" ||
						textPhone.getText().trim() == "" ||textEmail.getText().trim() == "") {
					MessageBox mb = new MessageBox(lblNext.getShell());
					mb.setText("提示");
					mb.setMessage("信息不能为空");
					mb.open();
				} else {
					acc.setUserId(textAccount.getText().trim());
					acc.setPassword(textPassword.getText().trim());
					acc.setPhone(textPhone.getText().trim());
					acc.setEmail(textEmail.getText().trim());
				}
			}

			@Override
			public void mouseUp(MouseEvent e) {
				Label label = (Label)e.widget;
				Rectangle r = label.getBounds();
				if(e.x >=0 && e.x <= r.width && e.y >=0 && e.y <= r.height) {
					composite21.setVisible(false);
					composite22.setVisible(true);
				}
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
			public void mouseUp(MouseEvent e) {
				Label label = (Label)e.widget;
				Rectangle r = label.getBounds();
				if(e.x >=0 && e.x <= r.width && e.y >=0 && e.y <= r.height) {
					AccountService as = new AccountServiceimpl();
					MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
					mb.setText("提示");
					if(as.register(acc)) {
						mb.setMessage("注册成功");
						mb.open();
					} else {
						mb.setMessage("注册失败，该微信号已存在或该手机号已被注册");
						mb.open();
					}
					lblRegister.getShell().dispose();
					
				}
			}

			@Override
			public void mouseDown(MouseEvent e) {
				String name = textName.getText().trim();
				String address = textAddress.getText().trim();
				if(name == "") {
					acc.setName(null);
				} else {
					acc.setName(name);
				}
				if(address == "") {
					acc.setAddress(null);
				} else {
					acc.setAddress(address);
				}
				if(btnMan.getSelection() == false && btnWoman.getSelection() == false) {
					acc.setSex(null);
				}
				
			}
		});
	}
}
