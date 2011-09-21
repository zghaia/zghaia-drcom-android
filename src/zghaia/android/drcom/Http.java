package zghaia.android.drcom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Http {
	 // login function
    public boolean login(String server,String user,String password){
    	BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(server);
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("DDDDD", user));
			postParameters.add(new BasicNameValuePair("upass", password));
			postParameters.add(new BasicNameValuePair("0MKKey", "%B5%C7%C2%BC+Login"));
			UrlEncodedFormEntity formEntity = null;
			try {
				formEntity = new UrlEncodedFormEntity(postParameters);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setEntity(formEntity);
			HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			try {
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String page = sb.toString();
			System.out.println(page);
			return true;
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
    }
    
    //logout function
    public boolean logout(String server){
    	BufferedReader in=null;
		try {//HttpGet
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			try {
				request.setURI(new URI(server));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//get Response  Content
			try {
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			//print the Response  Content
			try {
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String page = sb.toString();
			System.out.println(page);
			return true;
			//error control
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
    }
}
