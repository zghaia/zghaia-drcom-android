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
