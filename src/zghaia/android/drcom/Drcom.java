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

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Drcom extends Activity {
	private Button bLogin,bLogout,bSetting,bAbout;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //init Button must before use it
        bLogin=(Button)findViewById(R.id.login);
        bLogout=(Button)findViewById(R.id.logout);
        bSetting=(Button)findViewById(R.id.setting);
        bAbout=(Button)findViewById(R.id.about);
        
        //get Dr.com setting object can't put out off the onCreate function
    	final SharedPreferences pDrcom = getSharedPreferences("Drcom",0);
    	final SharedPreferences.Editor eDrcom=pDrcom.edit();
        
        //get the Dr.com data
        final String address=pDrcom.getString("address","");
        final String user=pDrcom.getString("user","");
        final String password=pDrcom.getString("password","");
        
        //Notification Icon will appear in Status bar
        showNotification();
        
        //showState
        switch(pDrcom.getInt("state",-1)){
        case -1:showToast(R.string.need_setting);
        		bLogin.setEnabled(false);
        		bLogout.setEnabled(false);
        		break;
        case 0 :showToast(R.string.logouted);
				bLogin.setEnabled(true);
				bLogout.setEnabled(false);
				break;
        case 1 :showToast(R.string.logined);
				bLogin.setEnabled(false);
				bLogout.setEnabled(true);
				break;
		default:showToast(R.string.unknown_error);
				bLogin.setEnabled(false);
				bLogout.setEnabled(false);
        }
        
        //call Login function
        bLogin.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		String server="http://"+address+"/";
        		login(server,user,password);	
        		eDrcom.putInt("state", 1);
        		eDrcom.commit();
        		bLogin.setEnabled(false);
				bLogout.setEnabled(true);
        	}
        });
        
        //call Logout function
        bLogout.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		String server="http://"+address+"/F.htm";
        		logout(server);
        		eDrcom.putInt("state", 0);
        		eDrcom.commit();
        		bLogin.setEnabled(true);
				bLogout.setEnabled(false);
        	}
        });
        
        //call Setting Activity
        bSetting.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		if(pDrcom.getInt("state",-1)==1){
        			showToast(R.string.need_logout);
        		}else{
        			Intent iSetting = new Intent();
        			iSetting.setClass(Drcom.this, Setting.class);
        			startActivity(iSetting);
        		}
        	}
        });
        
        //call About Activity
        bAbout.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent iAbout = new Intent();
        		iAbout.setClass(Drcom.this, About.class);
        		startActivity(iAbout);
        	}
        }); 
        
    }
    
    //notification 
	private NotificationManager mDrcom; 
	private int iDrcom; 
	private Notification nDrcom; 
	public void showNotification() {
    	mDrcom = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	nDrcom = new Notification(R.drawable.icon, "Dr.com", System.currentTimeMillis());
    	PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, Drcom.class), 0);
    	nDrcom.setLatestEventInfo(this, "Dr.com", "Dr.com", intent);
    	mDrcom.notify( iDrcom, nDrcom); 
    }
    
    //showToast
    public void showToast(int toastId){
    	Toast.makeText( this,getString(toastId),Toast.LENGTH_LONG).show();
    }
    
    // login function
    public void login(String server,String user,String password){
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
			showToast(R.string.login_success);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
						showToast(R.string.login_fail);
					}
				}
				
			}
    }
    
    //logout function
    public void logout(String server){
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
			showToast(R.string.logout_success);
			//error control
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					showToast(R.string.logout_fail);
				}
			}
			
		}
    }
}