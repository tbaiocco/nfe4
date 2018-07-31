package com.master.util;

import java.sql.*;   // All we need for JDBC

public class testa_conn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Connection       db;
		Statement        sql;
		DatabaseMetaData dbmd;

		if (args.length != 3) {
		    System.out.println("\nIncorrect number of arguments.\nUsage:\n "+
            "java   \n");
			System.exit(1);
		}
	    try
	    {
	    	String database = args[0];
	        String username = args[1];
	        String password = args[2];
	        Class.forName("org.postgresql.Driver"); //load the driver
	        db = DriverManager.getConnection("jdbc:postgresql://13.0.22.3:5432/"+database,
	                                         username,
	                                         password); //connect to the db
	        dbmd = db.getMetaData(); //get MetaData to confirm connection
	         System.out.println("Connection to "+dbmd.getDatabaseProductName()+" "+
	                           dbmd.getDatabaseProductVersion()+" successful.\n");
	        sql = db.createStatement(); //create a statement that we can use later


	        String sqlText = "create table jdbc_demo (code int, text varchar(20))";
	        // System.out.println("Executing this command: "+sqlText+"\n");
	        sql.executeUpdate(sqlText);


	        sqlText = "insert into jdbc_demo values (1,'One')";
	        // System.out.println("Executing this command: "+sqlText+"\n");
	        sql.executeUpdate(sqlText);


	        sqlText = "insert into jdbc_demo values (3,'Four')";
	        // System.out.println("Executing this command twice: "+sqlText+"\n");
	        sql.executeUpdate(sqlText);
	        sql.executeUpdate(sqlText);


	        sqlText = "update jdbc_demo set text = 'Three' where code = 3";
	        // System.out.println("Executing this command: "+sqlText+"\n");
	        sql.executeUpdate(sqlText);
	         System.out.println (sql.getUpdateCount()+
	                            " rows were update by this statement\n");


	        // System.out.println("\n\nNow demostrating a prepared statement...");
	        sqlText = "insert into jdbc_demo values (?,?)";
	        // System.out.println("The Statement looks like this: "+sqlText+"\n");
	        // System.out.println("Looping three times filling in the fields...\n");
	        PreparedStatement ps = db.prepareStatement(sqlText);
	        for (int i=10;i<13;i++)
	        {
	          // System.out.println(i+"...\n");
	          ps.setInt(1,i);         //set column one (code) to i
	          ps.setString(2,"HiHo"); //Column two gets a string
	          ps.executeUpdate();
	        }
	        ps.close();


	         System.out.println("Now executing the command: "+
	                           "select * from jdbc_demo");
	        ResultSet results = sql.executeQuery("select * from jdbc_demo");
	        if (results != null)
	        {
	          while (results.next())
	          {
	             System.out.println("code = "+results.getInt("code")+
	                               "; text = "+results.getString(2)+"\n");
	          }
	        }
	        results.close();


	        sqlText = "drop table jdbc_demo";
	        // System.out.println("Executing this command: "+sqlText+"\n");
	        sql.executeUpdate(sqlText);


	        db.close();
	    }
	    catch (Exception ex)
	    {
	      // System.out.println("***Exception:\n"+ex);
	      ex.printStackTrace();
	    }

	}

}
