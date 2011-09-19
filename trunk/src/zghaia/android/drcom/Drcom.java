package zghaia.android.drcom;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

public class Drcom extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //set SharedPreferences object for store and get data
        SharedPreferences pDrcom = getSharedPreferences("Drcom",0);
        
        //init the Dr.com data
        final String sAddress=pDrcom.getString("Paddress","");
        final String sUser=pDrcom.getString("Puser","");
        final String sPassword=pDrcom.getString("Ppassword","");
        
        //call Login function
        Button bLogin=(Button)findViewById(R.id.login);
        bLogin.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		String loginServer="http://"+sAddress+"/";
        		BufferedReader in = null;
        		
        		try {
        			HttpClient loginClient = new DefaultHttpClient();
        			HttpPost loginRequest = new HttpPost(loginServer);
        			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        			postParameters.add(new BasicNameValuePair("DDDDD", sUser));
        			postParameters.add(new BasicNameValuePair("upass", sPassword));
        			postParameters.add(new BasicNameValuePair("0MKKey", "%B5%C7%C2%BC+Login"));
        			UrlEncodedFormEntity formEntity = null;
					try {
						formEntity = new UrlEncodedFormEntity(postParameters);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			loginRequest.setEntity(formEntity);
        			HttpResponse response = null;
					try {
						response = loginClient.execute(loginRequest);
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
    				Toast.makeText( Drcom.this,"µÇÂ¼³É¹¦",Toast.LENGTH_LONG).show();
        			} finally {
        				if (in != null) {
        					try {
        						in.close();
        					} catch (IOException e) {
        						e.printStackTrace();
        						Toast.makeText( Drcom.this,"µÇÂ¼Ê§°Ü",Toast.LENGTH_LONG).show();
        					}
        				}
        				
        			}
        		
        	}
        });
        
        //call Logout function
        Button bLogout=(Button)findViewById(R.id.logout);
        bLogout.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		String logoutServer="http://"+sAddress+"/F.htm";
        		BufferedReader in=null;
        			try {//http get
        				HttpClient LogoutClient = new DefaultHttpClient();
        				HttpGet LogoutRequest = new HttpGet();
        				try {
							LogoutRequest.setURI(new URI(logoutServer));
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				HttpResponse LogoutResponse = null;
						try {
							LogoutResponse = LogoutClient.execute(LogoutRequest);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				//get Response  Content
        				try {
							in = new BufferedReader(new InputStreamReader(LogoutResponse.getEntity().getContent()));
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
        				Toast.makeText( Drcom.this,"×¢Ïú³É¹¦",Toast.LENGTH_LONG).show();
        				//error control
        			} finally {
        				if (in != null) {
        					try {
        						in.close();
        					} catch (IOException e) {
        						e.printStackTrace();
        						Toast.makeText( Drcom.this,"×¢ÏúÊ§°Ü",Toast.LENGTH_LONG).show();
        					}
        				}
        				
        			}
        		
        		
        	}
        });
        
        //call Setting Activity
        Button bSetting=(Button)findViewById(R.id.setting);
        bSetting.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent iSetting = new Intent();
        		iSetting.setClass(Drcom.this, Setting.class);
        		startActivity(iSetting);
        	}
        });
        
        //call About Activity
        Button bAbout=(Button)findViewById(R.id.about);
        bAbout.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent iAbout = new Intent();
        		iAbout.setClass(Drcom.this, About.class);
        		startActivity(iAbout);
        	}
        });
    }
}