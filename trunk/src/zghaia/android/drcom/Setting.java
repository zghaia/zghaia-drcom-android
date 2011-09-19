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
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;

public class Setting extends Activity {
	private EditText tAddress,tUser,tPassword;
	private Button bSave,bClear; 
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        
        //set SharedPreferences object for store and get data
        SharedPreferences pDrcom = getSharedPreferences("Drcom",0);
        final SharedPreferences.Editor eDrcom=pDrcom.edit();
        
        //get object of EditText
        tAddress=(EditText)findViewById(R.id.address);
        tUser=(EditText)findViewById(R.id.user);
        tPassword=(EditText)findViewById(R.id.password);
        
        //init the EditText text
        tAddress.setText(pDrcom.getString("Paddress",""));
        tUser.setText(pDrcom.getString("Puser",""));
        tPassword.setText(pDrcom.getString("Ppassword",""));
        
        // save address,user,password data
        bSave=(Button)findViewById(R.id.save);
        bSave.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		eDrcom.putString("Paddress", tAddress.getText().toString());
        		eDrcom.putString("Puser", tUser.getText().toString());
        		eDrcom.putString("Ppassword", tPassword.getText().toString());
        		eDrcom.commit();
        		Toast.makeText( Setting.this,"保存成功",Toast.LENGTH_LONG).show();
        	}
        });
        
        // clear address,user,password data
        bClear=(Button)findViewById(R.id.clear);
        bClear.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		eDrcom.putString("Paddress", "");
        		eDrcom.putString("Puser", "");
        		eDrcom.putString("Ppassword", "");
        		eDrcom.commit();
        		Toast.makeText( Setting.this,"清除成功",Toast.LENGTH_LONG).show();
        	}
        });
        
        //back Drcom Activity
        Button bBack=(Button)findViewById(R.id.back);
        bBack.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v){
        		Intent iDrcom = new Intent();
        		iDrcom.setClass(Setting.this, Drcom.class);
        		startActivity(iDrcom);
        	}
        });
        
    }
}
