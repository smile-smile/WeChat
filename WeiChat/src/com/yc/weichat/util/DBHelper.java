package com.yc.weichat.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.sql.BLOB;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.logging.log4j.LogManager;

public class DBHelper {
	

//	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private static final String USER = "jdbc";
//	private static final String PASSWORD = "a";
	
	private static DataSource dataSource; 
	
	static {
		try {
			//Class.forName(DRIVER_CLASS_NAME);
			Properties props = new Properties();
			InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");
			props.load(in);
			
			//使用连接池技术，数据源DBCP
			dataSource = BasicDataSourceFactory.createDataSource(props);
			LogManager.getLogger().debug("加载数据库属性元素构建数据源成功");
		} catch (Exception e) {
			LogManager.getLogger().error("加载数据库属性元素构建数据源失败", e);
		}
	}
	
	/**
	 * 连接数据库
	 * @return
	 */
	public static Connection getConn() {
		Connection con = null;
		try {
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			
//			props.put("driverClassName", DRIVER_CLASS_NAME);
//			props.put("url", URL);
//			props.put("username", USER);
//			props.put("password", PASSWORD);
//			
//			Properties props = new Properties();
//			InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("db.properties");
//			props.load(in);
			con = dataSource.getConnection();
			LogManager.getLogger().debug("数据库连接成功");
		} catch (Exception e) {
			LogManager.getLogger().error("数据库连接失败", e);
		}
		return con;
	}
	
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void close(Connection con, Statement st, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				LogManager.getLogger().debug("关闭结果集完成");
			} catch (SQLException e) {
				LogManager.getLogger().error("关闭结果集失败", e);
			}
		}
		
		if(st != null) {
			try {
				st.close();
				LogManager.getLogger().debug("关闭执行工具完成");
			} catch (SQLException e) {
				LogManager.getLogger().error("关闭执行工具失败", e);
			}
		}
		
		if(con != null) {
			try {
				con.close();
				LogManager.getLogger().debug("关闭数据库连接完成");
			} catch (SQLException e) {
				LogManager.getLogger().error("关闭数据库连接失败");
			}
		}
	}
	
	/**
	 * 增、删、改
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int doUpdate(String sql, Object... params) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = getConn();
			LogManager.getLogger().debug("要执行的sql语句:" + sql);
			ps = con.prepareStatement(sql);
			setParams(ps, params);
			LogManager.getLogger().debug("sql执行工具创建成功");
		} catch (SQLException e) {
			LogManager.getLogger().error("sql执行工具创建失败", e);
		}
		
		try {
			result = ps.executeUpdate();
			LogManager.getLogger().debug("处理数据成功，处理数据的条数为:" + result);
		} catch (SQLException e) {
			LogManager.getLogger().error("处理数据失败", e);
		} finally {
			close(con, ps, null);	
		}
		return result;
	}

	
	/**
	 * 设置参数
	 * @param ps
	 * @param params
	 */
	private static void setParams(PreparedStatement ps, Object...params) {
		
		if(params == null || params.length == 0) {
			return;
		}
		int flag = 0;
		try {
			for(int i=0; i<params.length; i++) {
				flag = i+1;
				Object obj = params[i];
				
				if(obj instanceof InputStream) {
					ps.setBinaryStream(i+1,  (InputStream)obj);
				} else {
					ps.setObject(i+1, obj);
				}
			}
		}catch (SQLException e) {
			LogManager.getLogger().error(String.format("注入第%d个值失败", flag), e);
		}
	
	}
	
	/**
	 * 查	单个
	 * @param sql
	 * @param objs
	 * @return
	 */
	public static Map<String, Object> doQueryOne(String sql, Object...objs) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> results = null;
		
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			setParams(ps, objs);
			LogManager.getLogger().debug("sql执行工具创建成功");
		} catch (SQLException e) {
			LogManager.getLogger().error("sql执行工具创建失败", e);
		}
		
		try {
			rs = ps.executeQuery();
			LogManager.getLogger().debug("执行sql取到返回数据成功");
		} catch (SQLException e) {
			LogManager.getLogger().error("执行sql取到返回数据失败", e);
		}
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			if(rs.next()) {
				results = new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++) {
					Object obj = rs.getObject(i);
					if(obj instanceof BLOB) {
						BLOB blob = (BLOB) rs.getBlob(i);
						obj = blob.getBinaryStream();
					}
					results.put(rsmd.getColumnName(i).toLowerCase(), obj);
				}
			}
			LogManager.getLogger().debug("取出结果集数据完成");
		} catch (SQLException e) {
			LogManager.getLogger().error("取出结果集数据失败", e);
		} finally {
			close(con, ps, rs);
		}
		return results;
	}
	
	/**
	 * 查	多个
	 * @param sql
	 * @param objs
	 * @return
	 */
	public static List<Map<String, Object>> doQuery(String sql, Object...objs) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, Object>> results = null;
		try {
			con = getConn();
			ps = con.prepareStatement(sql);
			setParams(ps, objs);
			LogManager.getLogger().debug("sql执行工具创建成功");
		} catch (SQLException e) {
			LogManager.getLogger().error("sql执行工具创建失败", e);
		}
		
		try {
			rs = ps.executeQuery();
			LogManager.getLogger().debug("执行sql取到返回数据成功");
		} catch (SQLException e) {
			LogManager.getLogger().error("执行sql取到返回数据失败", e);
		}
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			results = new ArrayList<Map<String, Object>>();
			while(rs.next()) {
				Map<String, Object> record = new HashMap<String, Object>();

				for(int i=1; i<=columnCount; i++) {
					Object obj = rs.getObject(i);
					if(obj instanceof BLOB) {
						BLOB blob = (BLOB) rs.getBlob(i);
						obj = blob.getBinaryStream();
					}
					record.put(rsmd.getColumnName(i).toLowerCase(), obj);
//					record.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i));
				}
				results.add(record);
			}
			LogManager.getLogger().debug("取出结果集数据完成");
		} catch (SQLException e) {
			LogManager.getLogger().error("取出结果集数据失败", e);
		} finally {
			close(con, ps, rs);
		}
		return results;
	}
	
	
}
