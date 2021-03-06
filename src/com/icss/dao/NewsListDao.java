package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.vo.NewsList;

public class NewsListDao {

	public ArrayList<NewsList> NewsList() throws ClassNotFoundException, SQLException{
		ArrayList<NewsList> newslist = new ArrayList<NewsList>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "tiger");
		PreparedStatement ps = conn.prepareStatement("select * from news");
		ResultSet rs = ps.executeQuery();
		for(int i=0;i<5;i++){
			rs.next();
			NewsList nl = new NewsList();
			nl.setId(rs.getInt(1));
			nl.setTitle(rs.getString(2));
			nl.setInfo(rs.getString(3).substring(0,100)+"...");
			nl.setImg(rs.getString(4));
			nl.setFrom1(rs.getString(5));
			nl.setTime(rs.getString(6).substring(0,11));
			newslist.add(nl);
		}
		rs.close();
		ps.close();
		conn.close();
		return newslist;
	}
}
