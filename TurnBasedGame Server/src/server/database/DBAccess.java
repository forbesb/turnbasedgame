package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.jdbcx.sqlserver.SQLServerDataSource;

public class DBAccess {
	private SQLServerDataSource pool;
	private String dbname = "BrianSoapServer", 
					serverName = "10.10.10.33", 
					username = "brian", 
					password = "brian";
	private int 	portNumber = 1433;
	
	public DBAccess(){
		pool = new SQLServerDataSource();
		
		pool.setServerName(serverName);
		pool.setPortNumber(portNumber);
		pool.setDatabaseName(dbname);//This section definds attributes of the database.
		pool.setUser(username);
		pool.setPassword(password);
		
	}
	
	public Connection getConnection(){
		//This method returns a connection to the database.
		Connection conn = null;
		try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;	
	}
	
	public void boostWins(String username, Connection conn){
		String getWins = "select wins from Users where name='"+username+"'";
		int wins = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getWins);
			if (rs.next()){
				wins = rs.getInt("wins");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String updateWins = "update Users set wins="+(wins+1)+" where name='"+username+"'";
		
		try {
			Statement stmt = conn.createStatement();
			boolean b = stmt.execute(updateWins);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void boostLosses(String username, Connection conn){
		String getWins = "select losses from Users where name='"+username+"'";
		int losses = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getWins);
			if (rs.next()){
				losses = rs.getInt("losses");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String updateWins = "update Users set losses="+(losses+1)+" where name='"+username+"'";
		
		try {
			Statement stmt = conn.createStatement();
			boolean b = stmt.execute(updateWins);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
