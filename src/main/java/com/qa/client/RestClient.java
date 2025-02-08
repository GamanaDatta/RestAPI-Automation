package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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
	public CloseableHttpResponse get(String url, HashMap<String, String>headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient HttpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry :headerMap.entrySet() ) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}  
		
		CloseableHttpResponse chr = HttpClient.execute(httpget); //hit the get url
		
		return chr;
		
	}
	
	//PostMethod
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); //http post request
		httppost.setEntity(new StringEntity(entityString)); //for payload
		
		//for headers:
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
		return closebaleHttpResponse;
		
		
	}
	
	//PutMethod
	public CloseableHttpResponse  put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		  HttpPut httpput = new HttpPut(url);
		  try {
			  httpput.setEntity(new StringEntity(entityString));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		//for headers:
			for(Map.Entry<String,String> entry : headerMap.entrySet()){
				httpput.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpput);
			return closebaleHttpResponse;	
		  
	}
	
}
