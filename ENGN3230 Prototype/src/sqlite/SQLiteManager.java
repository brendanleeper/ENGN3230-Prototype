/**************************************
 * SQLiteManager.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * SQLiteManager
 *
 * Handles all SQLite database interaction
 */
public class SQLiteManager {
	
	Connection c;
	String driver = "org.sqlite.JDBC";

	/**
	 * Initialises connection to SQLite db
	 * @author Brendan
	 */
	public void init() {
		c = null;
		try {
			// initialise the sqlite driver
			Class.forName(driver);
			// create the connection to our db file
			c = DriverManager.getConnection("jdbc:sqlite:prototype.db");
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("SQLiteManager.init() - Error initialising SQLite database");
			e.printStackTrace();
		}
		assert c != null;
		System.out.println("DB connection made");
	}
	
	/**
	 * Query the DB
	 * Web interface accesses the db directly
	 * @author Brendan
	 * @param query sql query to execute
	 * @return
	 */
	public String query(String query) {		
		String output = "";
		
		System.out.println("Querying database");
		System.out.println(query);
		
		Statement s = null;
		ResultSet rs;
		
		try {
			s = c.createStatement();
			rs = s.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			HashMap<String, String> columns = new HashMap<String, String>();
			
			for(int i=1; i<rsmd.getColumnCount()+1; i++) {
				columns.put(rsmd.getColumnLabel(i), rsmd.getColumnTypeName(i));
			}
			
			String colnames = "";
			
			System.out.println("Columns and their types are:");
			for(String c : columns.keySet()) {
				System.out.println(c + " " + columns.get(c));
				colnames+= c + "\t";
			}
			
			System.out.println(colnames);
			
			while(rs.next()) {
				for(String key : columns.keySet()) {
					if(columns.get(key).equals("text")) {
						output+= rs.getString(key) + "\t";
					}
					if(columns.get(key).equals("integer")) {
						output+= rs.getInt(key) + "\t";
					}
				}
				output+= "\n";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(output);
		
		return output;
	}
	
	/**
	 * Insert rows into the database
	 * TODO: Make it not hardcoded
	 * @author Brendan
	 */
	public void insert() {
		Statement s = null;
		
		try {
			s = c.createStatement();
			String insert;
			for(int i=1; i<12; i++) {
				insert = "INSERT INTO DATA (ID,NAME) VALUES (" + i + ", " + "'charles'" + ")";
				s.executeUpdate(insert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Inserted test data");
	}
	
	/**
	 * Create all the tables
	 * Don't run more than once before dropTables()
	 * @author Brendan
	 */
	public void makeTables() {
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate("CREATE TABLE data (id numeric, name varchar(30))");
			s.executeUpdate("CREATE TABLE users (username varchar(20), hash varchar(255))");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Created tables");
	}
	
	/**
	 * Drop all the tables
	 * @author Brendan
	 */
	public void dropTables() {
		Statement s = null;
		try {
			s = c.createStatement();
			s.executeUpdate("DROP TABLE data");
			s.executeUpdate("DROP TABLE users");
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Dropped tables");
	}
}
