package com.example.funmqttclient;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends Activity {
	private String mDeviceID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	mDeviceID = ((EditText) findViewById(R.id.editText1)).getText().toString();
        		Editor editor = getSharedPreferences(PushService.TAG, MODE_PRIVATE)
        				.edit();
        		editor.putString(PushService.PREF_DEVICE_ID, mDeviceID);
        		editor.commit();
        		
            	//new Thread(){
        			//public void run(){
        				
        				PushService.actionStart(getApplicationContext());
        			//}
        		//}.start();
            }
        });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		SharedPreferences p = getSharedPreferences(PushService.TAG,
				MODE_PRIVATE);
		boolean started = p.getBoolean(PushService.PREF_STARTED, false);

	}
}
