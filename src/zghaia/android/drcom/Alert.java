package zghaia.android.drcom;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class Alert extends  Service {
	 @Override
	    public void onCreate() {
	        Http http = new Http();
	        //get Dr.com setting object can't put out off the onCreate function
	    	final SharedPreferences pDrcom = getSharedPreferences("Drcom",0);
	    	final SharedPreferences.Editor eDrcom=pDrcom.edit();
	        
	        //get the Dr.com data
	        final String address=pDrcom.getString("address","");
	        if(pDrcom.getInt("state",-1)==1){
	        	String server="http://"+address+"/F.htm";
        		http.logout(server);
        		eDrcom.putInt("state", 0);
        		eDrcom.commit();
    		}
	 }
	 
    @Override
    	public IBinder onBind(Intent intent) {
		         return null;
    	}
}
