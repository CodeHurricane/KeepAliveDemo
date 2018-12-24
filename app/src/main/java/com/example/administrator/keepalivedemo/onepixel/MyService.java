package com.example.administrator.keepalivedemo.onepixel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ScreenListener listener = new ScreenListener(this);
		listener.begin(new ScreenListener.ScreenStateListener() {
			@Override
			public void onUserPresent() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScreenOn() {
				KeepLiveActivityManager.getInstance(MyService.this).finishKeepLiveActivity();
				Log.d("123132131","onScreenOn");
			}
			
			@Override
			public void onScreenOff() {
				KeepLiveActivityManager.getInstance(MyService.this).startKeepLiveActivity();
				Log.d("123132131","onScreenOff");
			}
		});
		
	}

}
