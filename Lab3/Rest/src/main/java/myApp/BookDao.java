package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum BookDao {
	
	instance;
	
	private Map<Integer, Book> booksMap = new HashMap<Integer, Book>();

	
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		
		Connection connection = null;
		try {
			connection = getConnection();
			//statement to run sql query
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM BOOKS;");
			
			//resultset to read data from sql
			while(result.next()) {
				Book book = new Book();
				book.setId(result.getInt(1));
				book.setAuthor(result.getString(2));
				book.setTitle(result.getString(3));
				book.setYear(result.getInt(4));
				
				books.add(book);
				
	            booksMap.put(book.getId(), book);
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return books;
		}
	
	
	
	public Book getBook(int id) {
		return booksMap.get(id);
	}
	
	

	public String testDB()
	{			
		StringBuilder sb = new StringBuilder();;
		Connection connection = null;
		try
		{
			//driver
			Class.forName("org.hsqldb.jdbcDriver");
			//connector
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB",
					"SA",
					"Passw0rd");
			//statement to run sql query
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM BOOKS;");
			
			//resultset to read data from sql
			while(result.next())
			{
				sb.append(result.getString(2)).append(", ");
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
	
	
	private Connection getConnection() throws SQLException{
		//driver
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB",
				"SA",
				"Passw0rd");
	}
	
	
	
	
	public boolean create(Book book)
	{
		Connection connection = null;
		boolean successed = false;
		try {
			connection = getConnection();
			String insertQuery =
					"INSERT INTO BOOKS ( id, author, title, year ) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			
			preparedStatement.setInt(1, book.getId());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getTitle());
			preparedStatement.setInt(4, book.getYear());
			
			int row = preparedStatement.executeUpdate();
			successed = row>0;				
			
	        if (successed) {
	            booksMap.put(book.getId(), book);
	        }
	        
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return successed;
	}

	

	public boolean delete(int id) {
	    Connection connection = null;
	    boolean successed = false;
	    try {
	        connection = getConnection();
	        PreparedStatement stmt = connection.prepareStatement("DELETE FROM BOOKS WHERE id = ?");
	        stmt.setInt(1, id);
	        int rowsAffected = stmt.executeUpdate();
	        if(rowsAffected > 0){
	             booksMap.remove(id);
	             successed=true;
	         }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return successed;
	}



	
	
	public boolean update(Book book) {
		Connection connection = null;
		boolean successed = false;
		try {
			connection = getConnection();
			String insertQuery =
					"UPDATE BOOKS SET author = ?, title = ?, year = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			
			preparedStatement.setString(1, book.getAuthor());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setInt(3, book.getYear());
			preparedStatement.setInt(4, book.getId());

			
			int row = preparedStatement.executeUpdate();
			successed = row>0;					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return successed;
	}
	
	

}
