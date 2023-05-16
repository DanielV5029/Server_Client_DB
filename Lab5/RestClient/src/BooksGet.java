import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BooksGet {
    private static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws Exception
	{
		CloseableHttpResponse response = null;
		try 
		{
			//http://localhost:8080/Rest/rest/book
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/Rest/rest/book").build();
			
			System.out.println(uri.toString());
			
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Accept", "application/xml");
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			String text = EntityUtils.toString(entity);
			
			List<Book> bookList = new ParseBooks().doParseBooks(text);
            output.append("------------------------------------------------------------------------------------------------\n");			
			for(Book book : bookList)
			{
				output.append("ID: " + book.getId() + "\n");
                output.append("Title: " + book.getTitle() + "\n");
                output.append("Author: " + book.getAuthor() + "\n");
                output.append("Year: " + book.getYear() + "\n");
                output.append("------------------------------------------------------------------------------------------------\n");			
			}
		} finally {
			response.close();
		}
	}

	public static String getOutput() {
        return output.toString();
    }

}
