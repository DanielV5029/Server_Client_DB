package myApp;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;


@Path("/book")
public class BookResource {

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Book> getBooks() {
		return BookDao.instance.getBooks();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{bookId}")
	public Book getBook(@PathParam("bookId") String id) {
		return BookDao.instance.getBook(Integer.parseInt(id));
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postBook(@FormParam("id") String id,
			@FormParam("title") String title,
			@FormParam("author") String author,
			@FormParam("year") String year,
			@Context HttpServletResponse servletResponse)throws IOException{
		System.out.println("Inside POST id = "+id);
		System.out.println("title = "+title);
		
		Book book = new Book();
		book.setId(Integer.parseInt(id));
		book.setTitle(title);
		book.setAuthor(author);
		book.setYear(Integer.parseInt(year));
		BookDao.instance.create(book);
		
		servletResponse.sendRedirect("../createBook.html");
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{bookId}")
	public void deleteBook(@PathParam("bookId") String id)throws IOException{
		System.out.println("Delete id = " + id);
		
		BookDao.instance.delete(Integer.parseInt(id));
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{bookId}")
	public void putBook(@PathParam("bookId")String id,
		@FormParam("title") String title,
		@FormParam("author") String author,
		@FormParam("year") String year,
		@Context HttpServletResponse servletResponse)throws IOException{
		System.out.println("PUT id = "+id);
	
		Book book = new Book();
		book.setId(Integer.parseInt(id));
		book.setTitle(title);
		book.setAuthor(author);
		book.setYear(Integer.parseInt(year));
		BookDao.instance.update(book);
	}
	
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return BookDao.instance.testDB();
	}
}
