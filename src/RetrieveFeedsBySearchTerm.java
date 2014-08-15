import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class RetrieveFeedsBySearchTerm {
	static String AccessToken = "22440636-aAsWewFH4XsVldICls5Bpy1LTgiaIuXIxWqBRWgtb";
	  static String AccessSecret = "xuhcY9h77v6fpZuIXNQj6MDH1BjjJbpA7RcjZZXg1eSbd";
	  static String ConsumerKey = "jXIycnRHrIFZQ6FbDdLZ0raaS";
	  static String ConsumerSecret = "6sRLrmEtbaoGAlNyXYFo1Rgb9FeuDYbn7hccHZsDfBAXyNfyXA";
	  static Hashtable<String, String> twitterAccountDetails = new Hashtable<String, String>();
	  
	public static void main(String[] args) throws Exception
	{
		getBySearchTerm("IceBucketChallenge");
	}
	
	public static void getBySearchTerm(String searchTerm) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
      consumer.setTokenWithSecret(AccessToken, AccessSecret);
		String URL = "https://api.twitter.com/1.1/search/tweets.json?result_type=recent&count=100&q="+searchTerm;
		HttpGet request = new HttpGet(URL);		
		consumer.sign(request);
		HttpClient client = HttpClientBuilder.create().build();
  
  try {
		HttpResponse response = client.execute(request);			
		int statusCode = response.getStatusLine().getStatusCode();
		String JSONString = IOUtils.toString(response.getEntity().getContent());		
		System.out.println("Writing Status Code For Retrieving Tweets" + statusCode);
		PrintWriter jsonObjectWriter = new PrintWriter("../TwitterSearch/files/"+searchTerm+"JSON");
		jsonObjectWriter.write(JSONString);
		jsonObjectWriter.close();			
		} 
  catch (IOException e) 
  {
		e.printStackTrace();
	}	
	}
}
