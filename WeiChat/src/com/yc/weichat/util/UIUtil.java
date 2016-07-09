package com.yc.weichat.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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


public class UIUtil {

	public static Object user;
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
