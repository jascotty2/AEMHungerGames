package net.aemcraftserver.aemhungergames.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnection {

	private Connection c;
	private String connectionString;
	private String username;
	private String password;
	private String table;

	public SQLConnection(String host, int port, String database, String username, String password) {
		this.connectionString = "jdbc:mysql://" + host + ":" + String.valueOf(port) + "/" + database;
	}

	public SQLConnection(String host, String port, String database, String username, String password) {
		this.connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
	}

	public SQLConnection() {
	}

	public Connection getConnection() {
		return this.c;
	}

	/**
	 * Attempt to connect this SQLConnection object instance to a specified
	 * database.
	 *
	 * @param host - Hostname/IP
	 * @param port - Port
	 * @param un - Database username
	 * @param pw - Database password
	 * @return Whether or not the connection attempt was successful.
	 */
	public boolean connect(String host, int port, String database, String un, String pw) {
		this.connectionString = "jdbc:mysql://" + host + ":" + String.valueOf(port) + "/" + database;
		this.username = un;
		this.password = pw;
		return connect();
	}

	public boolean connect() {
		if (this.connectionString == null) {
			return false;
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				this.c = DriverManager.getConnection(this.connectionString, this.username, this.password);
				this.c.setAutoCommit(true);
				return true;
			} catch (SQLException | ClassNotFoundException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Error in SQLConnection.connect: ", e);
				return false;
			}
		}
	}

	public PreparedStatement statement(String q) {
		try {
			return c.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Error in SQLConnection.statement: ", e);
			return null;
		}
	}

	public Statement statement() {
		try {
			return this.c.createStatement();
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Error in SQLConnection.statement: ", e);
			return null;
		}
	}

	public void createTables() {
		Statement s = this.statement();

		try {
			s.executeQuery("CREATE TABLE " + table + " ("
					+ "id INT NOT NULL AUTO_INCREMENT,"
					+ "playerName TEXT(16) NOT NULL,"
					+ "kills INT NOT NULL,"
					+ "deaths INT NOT NULL,"
					+ "gamesPlayed INT NOT NULL"
					+ "averageFinish DECIMAL NOT NULL"
					+ "PRIMARY KEY (id));").close();
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Error in SQLConnection.createTables: ", e);
		}
	}

	public boolean checkTables() {
		//if tables exist, return true
		//else return false
		try {
			return c.getMetaData().getTables(null, null, table, null).next();
		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Error in SQLConnection.checkTables: ", e);
		}
		return false;
	}
}