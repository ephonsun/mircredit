package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.banger.mobile.webapp.util.DBUtil;


public  class TestCase  {

/*	@Test
	public void test3() throws SQLException{
		String sqlString = "SELECT * FROM CD_CITY";
		Connection conn = 	DBUtil.getConnection();
		System.out.println(conn);
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery(sqlString);
		ResultSetMetaData rsd = rs.getMetaData();
		int len = rsd.getColumnCount();
		List<String> list = new ArrayList<String>();
		
		@SuppressWarnings("rawtypes")
		List<Map> result = new ArrayList<Map>();
		for(int i=1;i<len;i++){
			System.out.println(rsd.getColumnName(i));
			list.add(rsd.getColumnName(i));
			System.out.println(rsd.getTableName(i));
		}
		while(rs.next()){
			Map<String,String> map = new LinkedHashMap<String,String>();
			for(int i=1;i<len;i++){
				System.out.println(i+rsd.getColumnName(i));
				map.put(rsd.getColumnName(i), rs.getString(i));
			}
			result.add(map);
		}
		System.out.println(result);

		
	}*/
	@Test
	public void test4() throws SQLException{
//		Date dNow = new Date();   //当前时间
//		Date dBefore = new Date();
//		Calendar calendar = Calendar.getInstance(); //得到日历
//		calendar.setTime(dNow);//把当前时间赋给日历
//		calendar.add(calendar.YEAR, -1);  //设置为前3月
//		dBefore = calendar.getTime();   //得到前3月的时间
//
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
//		String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
//		String defaultEndDate = sdf.format(dNow); //格式化当前时间
//
//		System.out.println("前3个月的时间是：" + defaultStartDate);
//		System.out.println("生成的时间是：" + defaultEndDate);
//		String str ="01";
//		System.out.println("str.substring(1) = " + str.substring(1));
		try {
//			Calendar time = Calendar.getInstance();
//			Date date = new Date();
//			String start = "2002-09-01";
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date d1 = sdf.parse(start);
//			long l = new Date().getTime()-d1.getTime();

//			System.out.println(l/(1000*24*60*60));
//			System.out.println(l);
			List<List<String>> father = new ArrayList();
			List<String> son = new ArrayList<String>();
			son.add("hello");
			father.add(son);
			son.add("world!");
			System.out.println("father = " + father);

		}catch (Exception e){

		}
	}
}
