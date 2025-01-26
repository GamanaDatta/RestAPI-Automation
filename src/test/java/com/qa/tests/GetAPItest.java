package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPItest extends TestBase{
	
	TestBase testbase;
	String url;
	String apiURL;
	String Testurl;
	CloseableHttpResponse chr;
	
	@BeforeMethod
	public void setUp() {
		
		testbase = new TestBase();
		Testurl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		
		url = Testurl + apiURL;
		}
	
	@Test
	public void getAPITestwithoutHeaders() throws ClientProtocolException, IOException{
	
		RestClient restClient = new RestClient();
		
		
		HashMap<String, String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		//headerMap.put("username","gamana@nt.com");
		//headerMap.put("password","pass123");
		
		chr = restClient.get(url,headerMap);
		
				//status code
				int statusCode = chr.getStatusLine().getStatusCode();
				System.out.println("status code : "+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				//json response 
				String ResponseString = EntityUtils.toString(chr.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(ResponseString);
				System.out.println("JSON Response : "+responseJson);
				
				//single value assertion from json response
				String perpagevalue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("value of per page is-->"+ perpagevalue);
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);

				//get the value from JSON ARRAY:
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				//headers
				Header[] headersArray = chr.getAllHeaders();
				
				HashMap<String,String> allHeaders = new HashMap<String,String>();
				for(Header header : headersArray) {
					
					allHeaders.put(header.getName(), header.getValue());
					
				}
				
				System.out.println(" Headers array : "+allHeaders);
						
	}

	@Test
	public void getAPITestwithHeaders() throws ClientProtocolException, IOException{
	
		RestClient restClient = new RestClient();
		chr = restClient.get(url);
		
				//status code
				int statusCode = chr.getStatusLine().getStatusCode();
				System.out.println("status code : "+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				//json response 
				String ResponseString = EntityUtils.toString(chr.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(ResponseString);
				System.out.println("JSON Response : "+responseJson);
				
				//single value assertion from json response
				String perpagevalue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("value of per page is-->"+ perpagevalue);
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);

				//get the value from JSON ARRAY:
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				//headers
				Header[] headersArray = chr.getAllHeaders();
				
				HashMap<String,String> allHeaders = new HashMap<String,String>();
				for(Header header : headersArray) {
					
					allHeaders.put(header.getName(), header.getValue());
					
				}
				
				System.out.println(" Headers array : "+allHeaders);
				
			
				
			}
	}

	


