package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tech.tablesaw.api.Table;

public class Main {

	String dbname = "helloworld";
	String tablename = "main"; 
	Connection connection;
	
	public static void main(String[] args) {
		System.out.println();
	    helloworld();
	    System.out.println();
		dbFun();
		System.out.println();
	}
	private static void helloworld() {
		System.out.println("Hello World");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void dbFun() {
	    Main main = new Main();
	    main.connection = main.connectToDB();
	    if(main.connection == null) {
	    	System.out.println("Database not connected!");
	    }else{
	    	System.out.println("Database connected!");
	    	main.printData(main.connection);
	    	main.closeDBConnection(main.connection);
	    }
	}
	private Connection connectToDB() {
		 String url = "jdbc:mysql://localhost:3306/helloworld";
		 String username = "root";
		 String password = "";
		 System.out.println("Connecting database ...");
		 try {
			 Connection connection = DriverManager.getConnection(url, username, password);
		     //System.out.println("Database connected!");
	    	// test
	    	try {
				if(connection.isClosed()) {
					System.out.println("ouch");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	// test
			 return connection;
		 } catch (SQLException e) {
		     //throw new IllegalStateException("Cannot connect the database!", e);
			 return null;
		 }
	}
	private void printData(Connection connection) {
		try {
			if(connection.isClosed()) {
				System.out.println("Connection is closed :O");
			} else {
				String sql = "select * from " + tablename + ";";
				System.out.println("Query: '" + sql + "'");
				//PreparedStatement preparedStatement = connection.prepareStatement(sql).executeQuery()
				//ResultSet resultSet = preparedStatement.executeQuery();
				ResultSet resultSet =  connection.prepareStatement(sql).executeQuery();
	    		System.out.print(Table.read().db(resultSet).print());
			}
    	} catch (Exception e) {
    		e.printStackTrace();
		}
	}
	private void closeDBConnection (Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
