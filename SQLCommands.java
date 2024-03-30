
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class SQLCommands {
	
	
	public void add_course(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		System.out.println("Add a course");
		System.out.println("Please input the course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		System.out.println("Please input the course title: ");
		String courseTitle = keyboard.nextLine().trim();
		String query = "select code from Course Where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) 
		{
			System.out.println("The course code already exists in our database, please use another code");
			return;
		}
		query = "Insert into Course (code, title) values ('" + courseCode + "', '" + courseTitle + "')";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("The course has been added");
	}
	
	public void delete_course(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		System.out.println("Delete a course");
		System.out.println("Please input the course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		String query = "select code from Course Where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (!rs.next()) 
		{
			System.out.println("This course does not exist in our database, please make sure you have typed the course code in correctly");
			return;
		}
		query = "Delete from registered where code = '" + courseCode + "'";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		query = "Delete from course where code = '" + courseCode + "'";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("The course has been deleted");
	}
	
	public void add_student(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		System.out.println("Add a Student");
		System.out.println("Please input the students social security number: ");
		String ssn = keyboard.nextLine().toUpperCase().trim();
		System.out.println("Please input the students name: ");
		String name = keyboard.nextLine().trim();
		System.out.println("Please input the students address: ");
		String address = keyboard.nextLine().toUpperCase().trim();
		System.out.println("Please input the students major: ");
		String major = keyboard.nextLine().toUpperCase().trim();


		String query = "select ssn from Student Where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) 
		{
			System.out.println("This student is already in our database, please make sure you have typed in the ssn correctly");
			return;
		}
		query = "Insert into student (ssn, name, address, major) values ('" + ssn + "', '" + name + "', '" + address + "', '" + major + "')";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("The student has been added");
	}
	
	public void delete_student(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		System.out.println("Delete a student");
		System.out.println("Please input the social security number of the student you want to remove: ");
		String ssn = keyboard.nextLine().toUpperCase().trim();
		String query = "select ssn from Student Where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (!rs.next()) 
		{
			System.out.println("This student is not in our database, please make sure you have typed in the ssn correctly");
			return;
		}
		query = "Delete from registered where ssn = '" + ssn + "'";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		query = "Delete from student where ssn = '" + ssn + "'";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("The student has been removed");
	}
	
	public void register_course(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		System.out.println("Register a student for a course");
		System.out.println("Please input the students ssn: ");
		String ssn = keyboard.nextLine().toUpperCase().trim();
		if (!checkSSN(conn,ssn)) {
			System.out.println("This ssn is not in our database, please make sure you have typed it in correctly");
			return;
		}
		System.out.println("Please input the course code: ");
		String courseCode = keyboard.nextLine().trim();
		if(!checkCourse(conn,courseCode)) {
			System.out.println("This course code is not in our database, please make sure you have typed it in correctly");
			return;
		}
		System.out.println("Please input the year: ");
		String year = keyboard.nextLine().trim();
		System.out.println("Please input semester: ");
		String semester = keyboard.nextLine().trim();
		String query = "select ssn from Registered Where ssn = '" + ssn + "' And code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) 
		{
			System.out.println("The student is already registered for the course");
			return;
		}
		query = "Insert into Registered (ssn, code, year, semester) values (( Select ssn from Student where  ssn = '" + ssn + "'), (Select code from course where code = '" + courseCode + "'), '" + year + "', '" + semester + "')";
		try 
		{
			st.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("The student has been registered.");
	}
	
	public void check_registration(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		try
		(
		Statement st = conn.createStatement();
		)
		{
		System.out.println("Check Registration");
		System.out.println("Please input the students ssn: ");
		String ssn = keyboard.nextLine().toUpperCase().trim();
		if(!checkSSN(conn,ssn)) {
			System.out.println("This ssn is not in our database, please make sure you have typed it in correctly");
			return;
		}
		
		String query = "Select code, year, semester from registered Where ssn =  '" + ssn + "'"; 
		ResultSet rs = st.executeQuery(query);	
		while (rs.next()) 
		{
				 String code = rs.getString("code");
				 String year = rs.getString("year");
				 String semester = rs.getString("semester");
			     System.out.println("Code: " + code + "\tYear: " + year + "\tSemester: " + semester);
		}
			rs.close();
			st.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Message: " + e.getMessage());
		
		}
	
	}
	
	public void upload_grades(Connection conn, Scanner keyboard) throws SQLException, IOException
	{
		Statement st = conn.createStatement();
		Statement lt = conn.createStatement();
		System.out.println("Upload grades");
		System.out.println("Please input the course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		if(!checkCourse(conn,courseCode)) {
			System.out.println("This course code is not in our database, please make sure you have typed it in correctly");
			return;
		}
		System.out.println("Please input the year: ");
		String year = keyboard.nextLine().trim();
		if(!checkYear(conn,year)) {
			System.out.println("This year does not match with any of the registrations in our database, please make sure you have typed it in correctly");
			return;
		}
		System.out.println("Please input the semester: ");
		String semester = keyboard.nextLine().trim();
		if(!checkSemester(conn,semester)) {
			System.out.println("This semester does not match with any of the registrations in our database, please make sure you have typed it in correctly");
			return;
		}
		String query = "select ssn from registered Where code = '" + courseCode + "' And year = '" + year + "' And semester = '" + semester + "'";
		ResultSet rs = st.executeQuery(query);
		ResultSet rt = lt.executeQuery(query);
				
		while (rs.next()) {
			String ssn = rs.getString("ssn");
			System.out.println("Please enter grade for student: " + ssn);
			String grade = keyboard.nextLine().trim();
			
			query = "Update registered set grade = '" + grade + "' where ssn = '" + ssn + "' And code = '" + courseCode + "'";	
			lt.executeUpdate(query);			
		}
		rs.close();
		st.close();
		System.out.println("Grades have been updated");		
	}
	
	public boolean checkSSN(Connection conn, String ssn) throws SQLException, IOException 
	{
		Statement st = conn.createStatement();
		String query = "select ssn from student where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	public boolean checkCourse(Connection conn, String courseCode) throws SQLException, IOException 
	{
		Statement st = conn.createStatement();
		String query = "select code from course where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	public boolean checkYear(Connection conn, String year) throws SQLException, IOException 
	{
		Statement st = conn.createStatement();
		String query = "select year from registered where year = '" + year + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	public boolean checkSemester(Connection conn, String semester) throws SQLException, IOException 
	{
		Statement st = conn.createStatement();
		String query = "select semester from registered where semester = '" + semester + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			return true; 
		}
		else {
			return false;
		}
		
	}
	
}
