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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

public class About extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        //go to update page
        Button bUpdate=(Button)findViewById(R.id.update);
        bUpdate.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v) {
        	Uri updateUri = Uri.parse("http://code.google.com/p/zghaia-drcom-android/");
        	Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW,updateUri);
        	startActivity(openBrowserIntent);
        	}
        });
    }
}
