
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.nio.charset.Charset;
import org.apache.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class test2 {



	public static void main(String[] args) throws IOException, JSONException {
		  String baseURL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
		  String key = "289faf26a7b20d69a77b43935f70e358:17:72997005"; 
		  String word = "apple+technology"; 
		  String req = baseURL + "?q=" + word + "&page=2&sort=newest&api-key=" + key;
		 // String req1 = "http://api.nytimes.com/svc/search/v2/articlesearch.json?q=new+york&page=2&sort=newest&api-key=7497304579b17294fa0ffbe3f028b7ef:5:72997005";
		 
		  HttpGet get = new HttpGet(req);
	
		  HttpClient client = new DefaultHttpClient();
		  HttpResponse res = client.execute(get);
		  HttpEntity entity = res.getEntity();
		
		  BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		  StringBuffer result = new StringBuffer();
		  String line = "";
		  
		  while((line = rd.readLine())!=null){
			  result.append(line);
		  }
		  JSONObject temp,temp2,temp3 = null;
		  
		  temp =  new JSONObject(result.toString());
	    
		  temp2 = temp.getJSONObject("response");
		  JSONArray arr = temp2.getJSONArray("docs");
		  for (int i = 0; i < arr.length(); i++) {
			  temp3 = (JSONObject) arr.get(i);
			  
			  System.out.println(temp3.get("web_url"));
		  }
		
	  }
}
