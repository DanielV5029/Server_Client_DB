import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class BookUpdate {

	public static void updateBook(String id,  String author, String title, String year) {
		try
		{
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
	                  .setPath("/Rest/rest/book/" + id).build();
			
			System.out.println(uri.toString());
			
			HttpPut httpPut = new HttpPut(uri);
			httpPut.setHeader("Accept", "text/html");
			
			CloseableHttpClient client = HttpClients.createDefault();
			
			//PUT
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("author", author));
            nameValuePairs.add(new BasicNameValuePair("title", title));
            nameValuePairs.add(new BasicNameValuePair("year", year));
			
			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			System.out.println("Sending PUT request...");
			CloseableHttpResponse response = client.execute(httpPut);
			
			System.out.println("Response: "+ response.toString());

		} catch (Exception e) {
	          e.printStackTrace();
	      }
	}

}
