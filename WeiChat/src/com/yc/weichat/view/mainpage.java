package com.yc.weichat.view;

import static com.yc.weichat.util.UIUtil.*;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class mainpage {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainpage window = new mainpage();
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
		shell.setSize(1050,750);
		shell.setLocation(0,0);
		shell.setText("微信");	
		addContent(shell);	
		winCenter(shell);
		
		composite3 = new Composite(shell, SWT.NONE);
		composite3.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite3 = new FormData();
		fd_composite3.bottom = new FormAttachment(composite1_1, 0, SWT.BOTTOM);
		fd_composite3.right = new FormAttachment(0, 1044);
		fd_composite3.top = new FormAttachment(0);
		fd_composite3.left = new FormAttachment(0, 353);
		composite3.setLayoutData(fd_composite3);
		composite3.setVisible(false);
		
		SashForm sashForm = new SashForm(composite3, SWT.VERTICAL);
		
		Composite composite_7 = new Composite(sashForm, SWT.NONE);
		composite_7.setLayout(new StackLayout());
		
		Composite composite_1 = new Composite(composite_7, SWT.NONE);
		
		Composite composite = new Composite(composite_7, SWT.NONE);
		composite.setLayout(null);
		
		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setBounds(319, 10, 61, 17);
		lblNewLabel_7.setText("\u6768(4\u4EBA)");
		
		SashForm sashForm_1 = new SashForm(composite_7, SWT.NONE);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(mainpage.class, "/images/111.png"));
		btnNewButton.setBounds(0, 0, 30, 30);
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		btnNewButton_1.setImage(SWTResourceManager.getImage(mainpage.class, "/images/222.png"));
		btnNewButton_1.setBounds(36, 0, 30, 30);
		
		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		btnNewButton_2.setImage(SWTResourceManager.getImage(mainpage.class, "/images/333.png"));
		btnNewButton_2.setBounds(69, 0, 30, 30);
		
		Button btnNewButton_3 = new Button(composite_2, SWT.NONE);
		btnNewButton_3.setImage(SWTResourceManager.getImage(mainpage.class, "/images/444.png"));
		btnNewButton_3.setBounds(102, 0, 30, 30);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		
		Button btnNewButton_4 = new Button(composite_3, SWT.NONE);
		btnNewButton_4.setImage(SWTResourceManager.getImage(mainpage.class, "/images/1111.png"));
		btnNewButton_4.setBounds(579, 185, 80, 27);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.SYSTEM_MODAL);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_8 = new Label(composite_4, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblNewLabel_8.setText("New Label");
		
		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_9 = new Label(composite_5, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblNewLabel_9.setText("New Label");
		
		Composite composite_6 = new Composite(sashForm_1, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_10 = new Label(composite_6, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblNewLabel_10.setText("New Label");
		sashForm_1.setWeights(new int[] {1, 1, 1, 1, 1});
		sashForm.setWeights(new int[] {1});
		

	}
	Composite composite3;
	private Composite composite1_1;
	private void addContent(Shell shell2) {
		shell.setLayout(new FormLayout());
		
		composite1_1=new Composite(shell,SWT.BORDER);
		FormData fd_composite1_1 = new FormData();
		fd_composite1_1.bottom = new FormAttachment(0, 710);
		fd_composite1_1.right = new FormAttachment(0, 50);
		fd_composite1_1.top = new FormAttachment(0);
		fd_composite1_1.left = new FormAttachment(0);
		composite1_1.setLayoutData(fd_composite1_1);
		composite1_1.setBackground(new Color(shell.getDisplay(), 62,62,64));
    		
		Composite composite2=new Composite(shell,SWT.NONE);
		FormData fd_composite2 = new FormData();
		fd_composite2.bottom = new FormAttachment(0, 48);
		fd_composite2.right = new FormAttachment(0, 351);
		fd_composite2.top = new FormAttachment(0);
		fd_composite2.left = new FormAttachment(0, 51);
		composite2.setLayoutData(fd_composite2);
		composite2.setBackground(new Color(shell.getDisplay(), 235,235,235));
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.bottom = new FormAttachment(0, 710);
		fd_scrolledComposite.right = new FormAttachment(0, 351);
		fd_scrolledComposite.top = new FormAttachment(0, 46);
		fd_scrolledComposite.left = new FormAttachment(0, 51);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite composite_7 = new Composite(scrolledComposite, SWT.NONE);
		composite_7.setLayout(new RowLayout(SWT.SEARCH));
		
		Label lblNewLabel = new Label(composite_7, SWT.NONE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				composite3.setVisible(true);
			}
		});
		
		
		lblNewLabel.setImage(SWTResourceManager.getImage(mainpage.class, "/images/11.png"));
		
		Label lblNewLabel_1 = new Label(composite_7, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(mainpage.class, "/images/22.png"));
		
		Label lblNewLabel_2 = new Label(composite_7, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(mainpage.class, "/images/33.png"));
		
		Label lblNewLabel_3 = new Label(composite_7, SWT.NONE);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(mainpage.class, "/images/44.png"));
		
		Label lblNewLabel_4 = new Label(composite_7, SWT.NONE);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(mainpage.class, "/images/55.png"));
		
		Label lblNewLabel_5 = new Label(composite_7, SWT.NONE);
		lblNewLabel_5.setImage(SWTResourceManager.getImage(mainpage.class, "/images/77.png"));
		
		Label lblNewLabel_6 = new Label(composite_7, SWT.NONE);
		lblNewLabel_6.setImage(SWTResourceManager.getImage(mainpage.class, "/images/88.png"));
		
		Label label = new Label(composite_7, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(mainpage.class, "/images/88.png"));
		
		Label label_1 = new Label(composite_7, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(mainpage.class, "/images/99.png"));
		
		Label label_2 = new Label(composite_7, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(mainpage.class, "/images/11.png"));
		
		Label label_3 = new Label(composite_7, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(mainpage.class, "/images/22.png"));
		
		Label label_4 = new Label(composite_7, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(mainpage.class, "/images/55.png"));
		
		Label label_5 = new Label(composite_7, SWT.NONE);
		label_5.setImage(SWTResourceManager.getImage(mainpage.class, "/images/77.png"));
		
		//addLabelListener(label);
		
		scrolledComposite.setContent(composite_7);
		scrolledComposite.setMinSize(composite_7.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
	
				
        
		addpanel1Content(shell,composite1_1);		
		addpanel2Content(shell,composite2);
		addscrolledComposite(shell);
		
	}
	
	

	private void addscrolledComposite(Shell shell) {
		
	
		
		
	}



	private void addpanel2Content(Shell shell, Composite composite2) {
	
	Button btn6 =new Button(composite2,SWT.NONE);
	btn6.setBounds(265,12,25,25);
	InputStream in = Login.class.getClassLoader().getResourceAsStream("images/6.png");
	Image image6=new Image(shell.getDisplay(),in);
	btn6.setImage(image6);
	
	Text text=new Text(composite2, SWT.NONE);
	
	text.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			 Text text=(Text)e.widget;
			 System.out.println(text.getText().trim());
			 if("请输入查询关键字".equals(text.getText()))
			   {
				 text.setText("");
			   }
			 text.setForeground(new Color(shell.getDisplay(), 0,0,0));
		}
		@Override
		public void focusLost(FocusEvent e) {
			Text text=(Text)e.widget;
			 System.out.println(text.getText().trim());
			 if("".equals(text.getText().trim()))
			 {
				 text.setText("请输入查询关键字");
				 text.setForeground(new Color(shell.getDisplay(), 200,200,200));
			 }
		}
	});
	text.setBounds(10,12, 240,25);
	text.setText("请输入查询关键字");
	text.setForeground(new Color(shell.getDisplay(), 200,200,200));
	
 }

private void addpanel1Content(Shell shell,Composite composite1) {
	
	Button btn1 =new Button(composite1,SWT.NONE);
	btn1.setBounds(2,2,42,43);
	InputStream in = Login.class.getClassLoader().getResourceAsStream("images/1.png");
	Image image1=new Image(shell.getDisplay(),in);
	btn1.setImage(image1);
	
	Button btn2 =new Button(composite1,SWT.NONE);
	btn2.setBounds(2,52,42,42);
	in = Login.class.getClassLoader().getResourceAsStream("images/2.png");
	Image image2=new Image(shell.getDisplay(),in);
	btn2.setImage(image2);
	
	Button btn4 =new Button(composite1,SWT.NONE);
	
	Button btn3 =new Button(composite1,SWT.NONE);
	btn3.setBounds(2,102,42,43);
	in = Login.class.getClassLoader().getResourceAsStream("images/3.png");
	Image image3=new Image(shell.getDisplay(),in);
	btn3.setImage(image3);
	
	btn3.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		    InputStream in = Login.class.getClassLoader().getResourceAsStream("images/1_1.png");
			Image image3=new Image(shell.getDisplay(),in);
			btn3.setImage(image3);
			in = Login.class.getClassLoader().getResourceAsStream("images/4.png");
			Image image4=new Image(shell.getDisplay(),in);
			btn4.setImage(image4);	
		}
	});
	
	
	//Button btn4 =new Button(composite1,SWT.NONE);
	btn4.setBounds(2,150,42,43);
	in = Login.class.getClassLoader().getResourceAsStream("images/4.png");
	Image image4=new Image(shell.getDisplay(),in);
	btn4.setImage(image4);
	btn4.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			InputStream in = Login.class.getClassLoader().getResourceAsStream("images/3_1.png");
			Image image4=new Image(shell.getDisplay(),in);
			btn4.setImage(image4);
			in = Login.class.getClassLoader().getResourceAsStream("images/3.png");
			Image image3=new Image(shell.getDisplay(),in);
			btn3.setImage(image3);
		}
	});
	
	
	Button btn5 =new Button(composite1,SWT.NONE);
	btn5.setBounds(2,653,42,43);
	in = Login.class.getClassLoader().getResourceAsStream("images/5.png");
	Image image5=new Image(shell.getDisplay(),in);
	btn5.setImage(image5);
		
 }
}
