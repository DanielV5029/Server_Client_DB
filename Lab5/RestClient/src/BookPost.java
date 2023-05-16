import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class BookPost {

	 public static void insertBook(String id, String author, String title, String year) throws Exception {
	        try {
	            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/Rest/rest/book")
	                    .build();

	            System.out.println(uri.toString());

	            HttpPost httpPost = new HttpPost(uri);
	            httpPost.setHeader("Accept", "text/html");

	            CloseableHttpClient client = HttpClients.createDefault();

	            // POST
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	            nameValuePairs.add(new BasicNameValuePair("id", id));
	            nameValuePairs.add(new BasicNameValuePair("author", author));
	            nameValuePairs.add(new BasicNameValuePair("title", title));
	            nameValuePairs.add(new BasicNameValuePair("year", year));

	            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            System.out.println("Sending request...");
	            CloseableHttpResponse response = client.execute(httpPost);

	            System.out.println("Response: " + response.toString());

	        } finally {

	        }
	    }
}
