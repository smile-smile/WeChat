package com.yc.weichat.view;

import static com.yc.weichat.util.UIUtil.changeImage;
import static com.yc.weichat.util.UIUtil.winCenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainView {

	protected Shell shell;
	private Text text;

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
	protected void createContents() {
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(MainView.class, "/images/icon.jpg"));
		shell.setSize(1200, 900);
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
		btnNewButton.setText("New Button");
		
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
		btnSetting.setBounds(0, 784, 60, 60);
		btnSetting.setImage(image5);
		//addPressEvent(btnFriend, "images/press_setting.png");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.VERTICAL);
		sashForm_1.setEnabled(true);
		
		Composite composite_21 = new Composite(sashForm_1, SWT.NONE);
		
		text = new Text(composite_21, SWT.BORDER);
		text.setBounds(10, 20, 239, 30);
		
		Button btnNewButton_5 = new Button(composite_21, SWT.NONE);
		btnNewButton_5.setBounds(260, 15, 40, 40);
		Image image6 = changeImage("images/add.png", 60, 60);
		btnNewButton_5.setImage(image6);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(sashForm_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		
		List<Label> list = new ArrayList<Label>();
		for(int i=0; i<100; i++) {
			Label l = new Label(composite, SWT.NONE);
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
		
		StackLayout sLayout = new StackLayout();
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setLayout(sLayout);
		
		
		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		
		Composite composite_6 = new Composite(composite_4, SWT.NONE);
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
		
		ScrolledComposite scrolledComposite_2 = new ScrolledComposite(sashForm_2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_2.setExpandHorizontal(true);
		scrolledComposite_2.setExpandVertical(true);
		
		Composite composite_11 = new Composite(scrolledComposite_2, SWT.H_SCROLL);
		composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_2 = new Text(composite_11, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.CANCEL);
		scrolledComposite_2.setContent(composite_11);
		scrolledComposite_2.setMinSize(composite_11.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Composite composite_9 = new Composite(sashForm_2, SWT.NONE);
		
		Button btnSend = new Button(composite_9, SWT.NONE);
		btnSend.setBounds(671, 10, 114, 34);
		btnSend.setText("New Button");
		sashForm_2.setWeights(new int[] {5, 60, 5, 25, 5});
		sashForm.setWeights(new int[] {5, 25, 1, 63});
//		btnSend.setImage(changeImage(", width, height));

		sLayout.topControl = composite_6;
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
