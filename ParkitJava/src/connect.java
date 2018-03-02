import java.sql.DriverManager;

import com.mysql.jdbc.Connection;



public class connect 
{
	static Connection con;
	static Connection doConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://139.59.18.185:22/parkit","root","!Devil78");
		}
		catch(Exception e)
		{
			System.out.println(e); 
		}
		return con;
	}
}