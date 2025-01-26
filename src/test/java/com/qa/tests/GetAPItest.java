package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

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
	public void getTest() throws ClientProtocolException, IOException{
	
		RestClient restClient = new RestClient();
		chr = restClient.get(url);
		
				//statuscode
				int statusCode = chr.getStatusLine().getStatusCode();
				System.out.println("status code : "+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
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
