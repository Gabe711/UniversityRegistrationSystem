
//package test;

import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class test {


    public static void main(String[] args) throws SQLException, IOException
	{
    	//connect to database server	
    	Connection conn;
		try
		{
			String url = "jdbc:mysql://localhost/test";  //test is the database name
			String user = "root"; //username
			String password = "t123456"; //root password, you set it when you install the DBMS

			conn = DriverManager.getConnection(url, user, password);
		    //print menu
			Scanner scan = new Scanner(System.in);
			SQLCommands SQL = new SQLCommands();
			int command;
			String input;
	        boolean keepGoing = true;
	        while (keepGoing == true)
	        {
	            printmenu();
	            input = scan.nextLine();
	            command = Integer.parseInt(input);
				
				switch(command)
				{
					case 1: SQL.add_course(conn, scan); break;
					case 2: SQL.delete_course(conn, scan); break;
					case 3: SQL.add_student(conn, scan); break;
					case 4: SQL.delete_student(conn, scan); break;
					case 5: SQL.register_course(conn, scan); break;
					case 6: SQL.check_registration(conn, scan); break;
					case 7: SQL.upload_grades(conn, scan); break;
					case 0: 
						System.out.println("The Session has been ended, Thank you!");
	               	    keepGoing = false;
	               	    conn.close(); //close database connection   
						break;
				}    		            			
		    }
		}
		catch (SQLException ex)
		{
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
		}				
  }
    public static void printmenu()
    {
    		System.out.println();
    		System.out.println("*********************************************************************");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("***              Welcome to the Online Registration System        ***");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("*********************************************************************");
	      	System.out.println("1. Add a course");
	      	System.out.println("2. Delete a course");
	      	System.out.println("3. Add a student");
	      	System.out.println("4. Delete a student");
	      	System.out.println("5. Register for a course");
	      	System.out.println("6. Check a students registration");
	      	System.out.println("7. Upload grades");
	      	System.out.println("0. Quit ");
	      	System.out.println();
	      	
	      	System.out.println("Please choose an option");
    }
}

