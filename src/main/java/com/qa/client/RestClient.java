package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GetMethod
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse chr = HttpClient.execute(httpget); //hit the get url
		
		//statuscode
		int statusCode = chr.getStatusLine().getStatusCode();
		System.out.println("Status code : "+statusCode);
		
		//jsonresponse 
		String ResponseString = EntityUtils.toString(chr.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(ResponseString);
		System.out.println("JSON Response : "+responseJson);
		
		//headers
		Header[] headersArray = chr.getAllHeaders();
		
		HashMap<String,String> allHeaders = new HashMap<String,String>();
		for(Header header : headersArray) {
			
			allHeaders.put(header.getName(), header.getValue());
			
		}
		
		System.out.println(" Headers array : "+allHeaders);
		
	}
}
