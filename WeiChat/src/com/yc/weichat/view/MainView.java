package com.yc.weichat.view;

import static com.yc.weichat.util.UIUtil.changeImage;
import static com.yc.weichat.util.UIUtil.winCenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.weichat.entity.Account;
import com.yc.weichat.service.AccountService;
import com.yc.weichat.util.UIUtil;

public class MainView {

	protected Shell shell;
	private Text text;
	private Account acc;

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
	Button btnChat, btnFriend, btnCollect, btnSetting;
	private Text text_1;
	private Text text_2;
	StackLayout sLayout;
	Composite composite_5;
	Composite composite_6;
	Composite composite_12;
	protected void createContents() {
		try {
			acc = ((Account)UIUtil.user).clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(MainView.class, "/images/icon.jpg"));
		shell.setSize(1150, 800);
		winCenter(shell);
		shell.setText("微信");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		Image image2 = changeImage("images/press_message.png", 60, 60);
		Image image3 = changeImage("images/friend.png", 60, 60);
		Image image4 = changeImage("images/collect.png", 60, 60);
		Image image5 = changeImage("images/setting.png", 60, 60);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(new Color(shell.getDisplay(), 62,62,64));
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(0, 0, 60, 60);
		btnNewButton.setImage(changeImage("images/not_pic.jpg", 60, 60));
		if(acc.getPic() != null) {
			btnNewButton.setImage(changeImage(acc.getPic(), 60, 60));
		}
		
		btnChat = new Button(composite_1, SWT.NONE);
		btnChat.setBounds(-1, 66, 60, 60);
		btnChat.setImage(image2);
		addPressEvent(btnChat, "images/press_message.png");
		
		btnFriend = new Button(composite_1, SWT.NONE);
		btnFriend.setBounds(0, 132, 60, 60);
		btnFriend.setImage(image3);
		addPressEvent(btnFriend, "images/press_friend.png");
		
		
		btnCollect = new Button(composite_1, SWT.NONE);
		btnCollect.setBounds(0, 198, 60, 60);
		btnCollect.setImage(image4);
		addPressEvent(btnCollect, "images/press_collect.png");
		
		btnSetting = new Button(composite_1, SWT.NONE);
		btnSetting.setBounds(0, 700, 60, 60);
		btnSetting.setImage(image5);
		//addPressEvent(btnFriend, "images/press_setting.png");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.VERTICAL);
		sashForm_1.setEnabled(true);
		
		Composite composite_21 = new Composite(sashForm_1, SWT.NONE);
		
		text = new Text(composite_21, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		text.setText("请输入关键字");
		text.setBounds(10, 20, 239, 30);
		
		//文本焦点事件  提示语显示与关闭
		text.addFocusListener(new FocusAdapter() {
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

		
		Button btnNewButton_5 = new Button(composite_21, SWT.NONE);
		btnNewButton_5.setBounds(260, 15, 40, 40);
		Image image6 = changeImage("images/add.png", 60, 60);
		btnNewButton_5.setImage(image6);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(sashForm_1, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		
//		Button btnNewButton_1 = new Button(composite, SWT.NONE);
//		btnNewButton_1.setLayoutData(new RowData(268, 80));
//		btnNewButton_1.setText("New Button");
		
		List<Button> list = new ArrayList<Button>();
		for(int i=0; i<100; i++) {
			Button l = new Button(composite, SWT.NONE);
			l.setText("AAA");
			
			list.add(l);
		}
		
		
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sashForm_1.setWeights(new int[] {1, 12});
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setEnabled(false);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite_3, SWT.SEPARATOR);
		lblNewLabel.setText("New Label");
		
		sLayout = new StackLayout();
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setLayout(sLayout);
		
		
		composite_5 = new Composite(composite_4, SWT.NONE);
		
		composite_6 = new Composite(composite_4, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_6, SWT.VERTICAL);
		
		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		
		
		Label lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setBounds(328, 10, 90, 24);
		lblNewLabel_1.setText("New Label");
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(sashForm_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		Composite composite_10 = new Composite(scrolledComposite_1, SWT.NONE);
		composite_10.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_1 = new Text(composite_10, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.CANCEL);
		text_1.setEditable(false);
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
		
		text_2 = new Text(composite_11, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.CANCEL);
		scrolledComposite_2.setContent(composite_11);
		scrolledComposite_2.setMinSize(composite_11.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Composite composite_9 = new Composite(sashForm_2, SWT.NONE);
		
		Button btnSend = new Button(composite_9, SWT.NONE);
		btnSend.setBounds(652, 0, 100, 40);
		btnSend.setImage(changeImage("images/send.png", 100, 40));
		sashForm_2.setWeights(new int[] {5, 60, 5, 25, 5});
		sashForm.setWeights(new int[] {5, 25, 1, 63});
		addSendEvent(btnSend);
		
		
		//堆栈布局第二块面板设计   采用绝对布局
		composite_12 = new Composite(composite_4, SWT.NONE);
		
		Label lbl_headportait = new Label(composite_12, SWT.NONE);
		lbl_headportait.setAlignment(SWT.CENTER);
		lbl_headportait.setImage(SWTResourceManager.getImage(MainView.class, "/images/people1.png"));
		lbl_headportait.setBounds(306, 196, 200,200);
		
		Label lbl_name = new Label(composite_12, SWT.NONE);
		lbl_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lbl_name.setAlignment(SWT.CENTER);
		lbl_name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 13, SWT.NORMAL));
		lbl_name.setBounds(287, 424, 250,30);
		lbl_name.setText("有识之士");
		
		Label lbl_motto = new Label(composite_12, SWT.NONE);
		lbl_motto.setAlignment(SWT.CENTER);
		lbl_motto.setEnabled(false);
		lbl_motto.setBounds(287, 474, 250,30);
		lbl_motto.setText("“慎独”");
		
		Label lbl_remark = new Label(composite_12, SWT.NONE);
		lbl_remark.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 11, SWT.NORMAL));
		lbl_remark.setAlignment(SWT.CENTER);
		lbl_remark.setEnabled(false);
		lbl_remark.setBounds(295, 510, 65, 30);
		lbl_remark.setText("备注");
		
		Label lbl_remarkname = new Label(composite_12, SWT.NONE);
		lbl_remarkname.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_remarkname.setAlignment(SWT.CENTER);
		lbl_remarkname.setBounds(366, 510,200,30);
		lbl_remarkname.setText("陈谭军");
		
		Label lbl_weixinnumber = new Label(composite_12, SWT.NONE);
		lbl_weixinnumber.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_weixinnumber.setAlignment(SWT.CENTER);
		lbl_weixinnumber.setEnabled(false);
		lbl_weixinnumber.setBounds(295, 546, 65, 30);
		lbl_weixinnumber.setText("微信号");
		
		Label lbl_number = new Label(composite_12, SWT.NONE);
		lbl_number.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_number.setAlignment(SWT.CENTER);
		lbl_number.setBounds(366, 546,200,30);
		lbl_number.setText("clj22222jcl");
		
		Label lbl_district = new Label(composite_12, SWT.NONE);
		lbl_district.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl_district.setAlignment(SWT.CENTER);
		lbl_district.setEnabled(false);
		lbl_district.setBounds(295, 582,65, 30);
		lbl_district.setText("地区");
		
		Label lbl__districtname = new Label(composite_12, SWT.NONE);
		lbl__districtname.setFont(SWTResourceManager.getFont("Microsoft Tai Le", 11, SWT.NORMAL));
		lbl__districtname.setAlignment(SWT.CENTER);
		lbl__districtname.setBounds(366, 582, 200,30);
		lbl__districtname.setText("湖南 衡阳");
		
		Button btn_startchat = new Button(composite_12, SWT.NONE);
		btn_startchat.setBounds(287, 640, 250, 46);
		btn_startchat.setText("发消息");
		
		
		showComposite(composite_6);
	}

	private void showComposite(Composite composite) {
		sLayout.topControl=composite;
		composite_5.setVisible(false);
		composite_6.setVisible(false);
		composite_12.setVisible(false);
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

	private void addPressEvent(Button button, String path) {
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Button btn = (Button)e.widget;
				setGrayBtn();
				btn.setImage(changeImage(path, 60, 60));
			}
			
		});
		
	}
	
	private void setGrayBtn() {
		btnChat.setImage(changeImage("images/message.png", 60, 60));
		btnFriend.setImage(changeImage("images/friend.png", 60, 60));
		btnCollect.setImage(changeImage("images/collect.png", 60, 60));
		btnSetting.setImage(changeImage("images/setting.png", 60, 60));
	}
}
