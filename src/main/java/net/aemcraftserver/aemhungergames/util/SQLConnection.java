package main.java.net.aemcraftserver.aemhungergames.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection{
	private Connection c;
	
	public SQLConnection(){
		
	}
	
	public Connection getConnection(){
		return this.c;
	}
	
	/**
	 * Attempt to connect this SQLConnection object instance to a specified database.
	 * 
	 * @param host - Hostname/IP
	 * @param port - Port
	 * @param un - Database username
	 * @param pw - Database password
	 * @return Whether or not the connection attempt was successful.
	 */
	public boolean connect(String host, int port, String database, String un, String pw){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.c = DriverManager.getConnection("jdbc:mysql://"+host+":"+String.valueOf(port)+"/"+database, un, pw);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}