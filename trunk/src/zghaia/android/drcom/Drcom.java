/*
  Copyright (C) <2011> <Dr.com for Android Authors :zghaia@gmail.com>
                          All rights reserved.
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
               GNU General Public License for more details 
                      <./GNU GENERAL PUBLIC LICENSE>
 */

package zghaia.android.drcom;

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
        
        final Http http=new Http();
        
        //init Button must before using it
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
        		if(http.login(server,user,password)){
        			showToast(R.string.login_success);	
        		}else{
        			showToast(R.string.login_fail);
        		}
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
        		if(http.logout(server)){
        			showToast(R.string.logout_success);	
        		}else{
        			showToast(R.string.logout_fail);
        		}
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

}