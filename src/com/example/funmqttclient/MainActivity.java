package com.example.funmqttclient;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private String mDeviceID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDeviceID = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		((TextView) findViewById(R.id.androidId)).setText(mDeviceID);

		Editor editor = getSharedPreferences(PushService.TAG, MODE_PRIVATE)
				.edit();
		editor.putString(PushService.PREF_DEVICE_ID, mDeviceID);
		editor.commit();
		PushService.actionStart(getApplicationContext());
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
