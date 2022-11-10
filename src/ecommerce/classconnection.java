
package ecommerce;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class classconnection {
	private static final String DB_NAME = "dbecommerce";
	private static final String DB_IP= "127.0.0.1";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "ADARDORNaima2002";
	
	public static Connection getDBConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+DB_IP+"/"+DB_NAME, DB_USER,DB_PASSWORD);
			//System.out.println("bien");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}
return connection;
		
	
}
	public static boolean validate(String username,String password) {

		final String query = "SELECT * FROM admin WHERE login = ? AND pass_word=?";
		
		try {
				 Connection connection = getDBConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			System.out.println(preparedstatement );
			ResultSet resultSet=preparedstatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return false;
	}
	private static void printSQLException(SQLException ex) {
		for(Throwable e : ex ) {
			
			if(ex instanceof SQLException ) {
				e.printStackTrace(System.err);
				System.err.println("SQLstate: " +((SQLException)e).getSQLState());
				System.err.println("Error code: " +((SQLException)e).getErrorCode());
				System.err.println("Message: " + ex.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.err.println("Cause : "+t);
					t = t.getCause();
				}
				
			}}
	}
}
