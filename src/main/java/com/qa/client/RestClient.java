package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse chr = HttpClient.execute(httpget); //hit the get url
		
		return chr;
		
	}
	

	//GetMethod with headers
	public CloseableHttpResponse get(String url, HashMap<String, String>hashMap) throws ClientProtocolException, IOException {
		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry :hashMap.entrySet() ) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}  
		
		CloseableHttpResponse chr = HttpClient.execute(httpget); //hit the get url
		
		return chr;
		
	}
}
