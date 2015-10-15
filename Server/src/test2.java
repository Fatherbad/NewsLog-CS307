
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

@SuppressWarnings("deprecation")
public class test2  {

	public static void main(String[] args) throws IOException, JSONException {
		
		
		  
		  String[] word = {"technology","politics","science","economy","entertainment","sports"};
		  String baseURL = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
		  String key = "289faf26a7b20d69a77b43935f70e358:17:72997005"; 
		  String req = "";
		  Obj [] array = new Obj[1000];
		  int i  = 0;
		  String type = "";
		  int count = 0;
		 /* while(i < word.length){
			  req = baseURL + "?q=" + word[i] + "&page=1&sort=newest&api-key=" + key;
			  //  System.out.println(count);
			  count = fetch(req,array,word[i],count);
			 
			  i++;
		  }*
		  // String req1 = "http://api.nytimes.com/svc/search/v2/articlesearch.json?q=new+york&page=2&sort=newest&api-key=7497304579b17294fa0ffbe3f028b7ef:5:72997005";
			  /*  i=0;
		  while(i < word.length){
			  req = baseURL + "?q=" + word[0] + "&page=1&sort=newest&api-key=" + key;
			//  System.out.println(count);
			  count = fetch(req,array,word[i],count);
			 
			  i++;
		  }
		  i =0;
		  while(i < word.length){
			  req = baseURL + "?q=" + word[0] + "&page=3&sort=newest&api-key=" + key;
			//  System.out.println(count);
			  count = fetch(req,array,word[i],count);
			 
			  i++;
		  }
		  i =0;
		  while(i < word.length){
			  req = baseURL + "?q=" + word[0] + "&page=4&sort=newest&api-key=" + key;
			//  System.out.println(count);
			  count = fetch(req,array,word[i],count);
			 
			  i++;
		  }*/
		  //System.out.println(count);
		 i = 0;
		 
		 ///Fetching
		
		
		 //nameValuePairs.add(new BasicNameValuePair("urlToSearch","http://url.com/1"));
		
		 //HttpEntity entity = response.getEntity();
		 //response.s
		 //String result = EntityUtils.toString(entity);
		 //System.out.println("rrrrrrrrrr"+result);
		 i = 0;
		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		/* while( array[i] != null){
			
			//update(array[i].getURL(),array[i].getType(),nameValuePairs);
			//System.out.println(array[i].getType()); 
			//System.out.println(array[i].getURL());
			//System.out.println(array[i].getKey());
			i++;
		 }*/
		 
		 System.out.println(get("technology",nameValuePairs));
		 
	  }
	
	 static int fetch(String req, Obj [] array,String type,int i) throws ClientProtocolException, IOException, JSONException{
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
		  int ij = i;
		  for (int j = 0; j < arr.length(); j++) {
			 
			  temp3 = (JSONObject) arr.get(j);
			  //System.out.println(ij +"aaaaaaaaaaaaa"+type);
			  //System.out.println("aaaaaaaaaa"+temp3.get("web_url"));
			 
			  array[ij] = new Obj(temp3.get("web_url").toString(),type,temp3.get("snippet").toString());	  
			  ij  = i + j + 1;
		 }
		  
		  
		 return ij;
	 }
	 
	 static void update (String url,String catogory, ArrayList<NameValuePair> nameValuePairs) throws ClientProtocolException, IOException{
		
		 nameValuePairs.add(new BasicNameValuePair("url", url));	 
		 nameValuePairs.add(new BasicNameValuePair("category", catogory));
		 //System.out.println(array.length);
		 HttpParams httpParameters = new BasicHttpParams();
		 HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
		 HttpConnectionParams.setSoTimeout(httpParameters, 15000);	
		 HttpClient httpclient = new DefaultHttpClient(httpParameters);
		 HttpPost httppost = new HttpPost("http://10.184.218.69/newslog/serviceSetArticles.php");
		 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 HttpResponse response = httpclient.execute(httppost);
	 }
	
	 static JSONArray get (String catogory,ArrayList<NameValuePair> nameValuePairs ) throws ClientProtocolException, IOException, JSONException{
			
		// ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		 nameValuePairs.add(new BasicNameValuePair("category", catogory));
		 HttpParams httpParameters = new BasicHttpParams();
		 HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
		 HttpConnectionParams.setSoTimeout(httpParameters, 15000);	
		 HttpClient httpclient = new DefaultHttpClient(httpParameters);
		 HttpPost httppost = new HttpPost("http://10.184.223.128/newslog/serviceGetArticles.php");
		 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 HttpResponse response = httpclient.execute(httppost);
		 HttpEntity entity = response.getEntity();
		 String result = EntityUtils.toString(entity);
		 JSONArray ret = new JSONArray(result);
		 System.out.println("rrrrrrrrrr" + ret);
		 return ret;
		 
	}
}
