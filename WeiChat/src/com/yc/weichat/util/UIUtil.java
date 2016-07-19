package com.yc.weichat.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.weichat.entity.Account;
import com.yc.weichat.entity.Group;


public class UIUtil {

	public static Object user;
	public static List<Account> friendList;
	public static List<Group> groupList;


	public static void saveBit(InputStream inStream, String name) {
	
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		try {
			while( (len=inStream.read(buffer)) != -1 ) {
				//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭输入流
		try {
			inStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//把outStream里的数据写入内存
	
		//得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = outStream.toByteArray();
		//new一个文件对象用来保存图片，默认保存当前工程根目录
		File imageFile = new File(System.getProperty("user.dir") + "\\src\\images\\" + name + ".jpg");
		FileOutputStream fileOutStream;
		try {
			//创建输出流
			fileOutStream = new FileOutputStream(imageFile);
			//写入数据
			fileOutStream .write(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 图片自适用大小
	 * @param imageClassPath
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image changeImage(String imageClassPath, int width, int height) {
		//判断文件的路径是相对还是绝对
		//window的绝对路径是以盘符号开始，Linux的绝对路径是以“/”开始
		//imageClassPath.charAt(0) == '/' || imageClassPath.charAt(1) == ':'判断绝对路径
		InputStream in = UIUtil.class.getClassLoader().getResourceAsStream(imageClassPath);
		if(in == null) {
			try {
				in = new FileInputStream(imageClassPath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return changeImage(in, width, height);
	}
	
	public static Image changeImage(InputStream in, int width, int height) {
		ImageData idata = new ImageData(in).scaledTo(width, height);
		Image image = new Image(null, idata);
		return image;
	}
	
	/**
	 * 窗口居中
	 * @param shell
	 */
	public static void winCenter(Shell shell) {
		Rectangle systemR = shell.getDisplay().getBounds();
		Rectangle winR = shell.getBounds();
		shell.setLocation((systemR.width - winR.width) / 2, (systemR.height - winR.height) / 2);
	}
	
	/**
	 * 关闭窗口事件
	 * @param lblClose
	 */
	public static void addCloseEvent(final Label lblClose) {
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/btn_close_down.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				Label label = (Label)e.widget;
				Rectangle r = label.getBounds();
				if(e.x >=0 && e.x <= r.width && e.y >=0 && e.y <= r.height) {
					lblClose.getShell().dispose();
				}
			}
		});
		lblClose.addMouseTrackListener(new MouseTrackAdapter() {
			@Override//放上
			public void mouseHover(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/btn_close_highlight.png"));
			}
			@Override//移开
			public void mouseExit(MouseEvent e) {
				Label l = (Label)e.widget;
				l.setImage(SWTResourceManager.getImage(UIUtil.class, "/images/btn_close_normal.png"));
			}
			
		});
	}
	
	
	/**
	 * 移动窗口
	 */
	private static int beginX;
	private static int beginY;
	private static boolean isPress;
	public static void addMoveEvent(final Shell shell) {
		shell.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				
				if(isPress) {
					int moveX = arg0.x - beginX;
					int moveY = arg0.y - beginY;
					shell.setLocation(shell.getBounds().x + moveX, shell.getBounds().y + moveY);

				}
			}
		});
		shell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				isPress = true;
				beginX = e.x;
				beginY = e.y;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				isPress = false;
			}
		});
	}
	
}
