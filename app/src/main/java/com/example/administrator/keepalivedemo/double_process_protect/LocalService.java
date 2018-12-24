package com.example.administrator.keepalivedemo.double_process_protect;


import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.administrator.keepalivedemo.RemoteConnection;

public class LocalService extends Service {

	public static final String TAG = "ricky";
	private MyBinder binder;
	private MyServiceConnection conn;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		if(binder ==null){
			binder = new MyBinder();
		}
		conn = new MyServiceConnection();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);
		
		PendingIntent contentIntent = PendingIntent.getService(this, 0, intent, 0);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setTicker("360")
		.setContentIntent(contentIntent)
		.setContentTitle("hahahaha")
		.setAutoCancel(true)
		.setContentText("hehehe")
		.setWhen( System.currentTimeMillis());
		
		startForeground(startId, builder.build());
		return START_STICKY;
	}
	

	class MyBinder extends RemoteConnection.Stub {

		@Override
		public String getProcessName() throws RemoteException {
			// TODO Auto-generated method stub
			return "LocalService";
		}
		
	}
	
	class MyServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Toast.makeText(LocalService.this, "onServiceDisconnected", Toast.LENGTH_LONG).show();
			LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
			LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);
		}
		
	}
	

}
