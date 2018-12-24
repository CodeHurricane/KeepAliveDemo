package com.example.administrator.keepalivedemo.double_process_protect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.keepalivedemo.R;
import com.example.administrator.keepalivedemo.onepixel.MyService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService(new Intent(this, LocalService.class));
		startService(new Intent(this, RemoteService.class));
		startService(new Intent(this, JobHandleService.class));
		startService(new Intent(this,MyService.class));
	}

}
