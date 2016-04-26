package com.example.simplefacebookclient;

import android.app.Application;

import com.facebook.FacebookSdk;

public class ClientApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FacebookSdk.sdkInitialize(getApplicationContext());
	}
}
