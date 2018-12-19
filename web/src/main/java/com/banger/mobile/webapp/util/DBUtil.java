package com.banger.mobile.webapp.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {
	
	//连接池对象
	private static BasicDataSource ds;
	
	static {
		try {
			//1.读取参数
			Properties p = new Properties();
			p.load(DBUtil.class.getClassLoader()
				.getResourceAsStream("jdbc.properties"));
			String driver=p.getProperty("jdbc.driverClassName");
			String url = p.getProperty("jdbc.url");
			String user = p.getProperty("jdbc.username");
			String password = p.getProperty("jdbc.password");
//			String initSize = p.getProperty("initSize");
//			String maxSize = p.getProperty("maxSize");
			//2.创建连接池
			ds = new BasicDataSource();
			//3.设置参数
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
//			ds.setInitialSize(new Integer(initSize));
//			ds.setMaxActive(new Integer(maxSize));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"加载db.properties失败",e);
		}
	}
	
	public static Connection getConnection() 
		throws SQLException {
		return ds.getConnection();
	}
	
	public static int[]  excute(String sqlString) throws SQLException {
		
		Connection conn = null;
		int [] results = null;
		try {
			conn = getConnection();
			Statement sta = conn.createStatement();
			String[] sqls = sqlString.split(";");
			System.out.println(sqls);
			for(String sql:sqls){
				sta.addBatch(sql);
		}
			results=sta.executeBatch();
			
		}catch(SQLException e){
			throw e;
		}finally{
			close(conn);
		}
		return results;
		
	}
	
	public static Map<Integer,String> getColumnNames(String sql) throws SQLException{
		
		Connection conn = null;
		Map<Integer,String> map= null;
		try{
		conn = DBUtil.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(sql);
		ResultSetMetaData rsd = rs.getMetaData();
		map = new LinkedHashMap<Integer, String>();
		for(int i=1;i<=rsd.getColumnCount();i++){
			map.put(i, rsd.getColumnName(i));
		}
		}catch(SQLException e){
			throw e;
		}finally{
			DBUtil.close(conn);
		}
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> getResultData(String sql) throws SQLException{
		Connection conn = null;
		List<Map> result = null;
		try{
			conn = DBUtil.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			ResultSetMetaData rsd =rs.getMetaData();
			result = new ArrayList<Map>();
			while(rs.next()){
			Map<String,String> map = new LinkedHashMap<String,String>();
			for(int i=1;i<=rsd.getColumnCount();i++){
				map.put(rsd.getColumnName(i), rs.getString(i));
			}
				result.add(map);
			
		}
		}catch(SQLException e){
				throw e;
		}finally{
				DBUtil.close(conn);
		}
		return result;
	}
	/**
	 * 该连接由连接池创建,实际上该连接的实现类
	 * 也是由连接池重写了,它所提供的close方法,
	 * 不再是关闭连接,而是改为归还连接.
	 */
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(
					"归还连接失败", e);
			}
		}
	}

}











