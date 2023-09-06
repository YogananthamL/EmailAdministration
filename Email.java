import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class Email {
	private static int id=0;
	private String firstName;
	private String lastName;
	private String department;
	private String email;
	private String password;
	private int mailCapacity=500;
	private String alteremail;
	private String company="meta.com";
	
	
	//Constructor
	public Email(String firstName,String lastName) throws Exception {
		id++;
		this.firstName=firstName;
		this.lastName=lastName;
		System.out.println("EMAIL CREATED: "+this.firstName +" "+this.lastName);
		
		//System.out.println("Id: "+id);
		
		//department
		
		
		this.department=department();
		
		//password
		
		this.password=setPassword(9);
		System.out.println("Your password is:"+this.password);
		//email
		email=firstName.toLowerCase()+"."+lastName.toLowerCase()+"@"+department+"."+company;
		System.out.println("Your email is: "+email);
		
		//Database insertion method call
		
		//insert();
		
		
		//Database read method call
		
		
		read();
		
	}
	//department choosing method
	private String department() {
		System.out.println("DEPARTMENT CODES: \n1 Development \n2 Accounting \n3 Sales \n4 none \nEnter Department code:");
		Scanner in=new Scanner(System.in);
		int depChoice=in.nextInt();
		switch(depChoice) {
		case 1:
			return "Development";
		case 2:
			return "Account";
		case 3:
			return "Sales";
		default:
			return ""; 
		
		}
	}
	
	//password generation method
	private String setPassword(int length) {
		String passwordSet="ABCEFGHIJKLMNOPQRSTUVWXYZ1234567890!@$%&";
		char[] password=new char[length];
		for(int i=0;i<length;i++) {
			int rand=(int)(Math.random()*passwordSet.length());
			 password[i]=passwordSet.charAt(rand);
		}
		return new String (password);
		
	}
	//set methods
	public void setMailCapacity(int capacity) {
		this.mailCapacity=capacity;
	}
	public void setAltEmail(String altemail) {
		this.alteremail=altemail;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	//get methods
	public int getMailCapacity() {return mailCapacity;}
	public String getAltEmail() {return alteremail;}
	public String getPassword() {return password;}
	
	
	//show information method
	public void showInfo() {
		System.out.println("Mail Capacity: "+mailCapacity+"mb");
		System.out.println("Alternate Email ID: "+alteremail);
		System.out.println("Password: "+password);
		
	}
	
	private void read() throws Exception {
		String url="jdbc:mysql://localhost:3306/email";
		String uName="root";
		String password="LuciferSaran@123";
		String query="select*from emailtable ";
		
		Connection con=DriverManager.getConnection(url, uName, password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
			System.out.println("ID "+rs.getInt(1));
			System.out.println("FirstName: "+rs.getString(2));
			System.out.println("LastName: "+rs.getString(3));
			System.out.println("Department: "+rs.getString(4));
			System.out.println("Email: "+rs.getString(5));
			System.out.println("Password: "+rs.getString(6));
		
		
			
		}
		con.close();
				
	}

	private void insert() throws Exception {
		String url="jdbc:mysql://localhost:3306/email";
		String uName="root";
		String password="LuciferSaran@123";
		String query="insert into emailtable values(?,?,?,?,?,?)";
	
		Connection con=DriverManager.getConnection(url, uName, password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, firstName);
		pst.setString(3, lastName);
		pst.setString(4, department);
		pst.setString(5, email);
		pst.setString(6, this.password);
		int row=pst.executeUpdate();
		System.out.println("Rows affected: "+row);
		con.close();
	
	
	
	}
	

	public void delete(int id) throws Exception {
		String url="jdbc:mysql://localhost:3306/email";
		String uName="root";
		String password="LuciferSaran@123";
		String query="delete from emailtable where id="+id;
		
		Connection con=DriverManager.getConnection(url, uName, password);
		Statement st=con.createStatement();
		int row=st.executeUpdate(query);
		System.out.println("deleted row: "+row);
		con.close();
}
}