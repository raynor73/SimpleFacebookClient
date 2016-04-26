package com.example.simplefacebookclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onTestButtonClick(final View view) {
		startActivity(new Intent(this, com.facebook.FacebookActivity.class));
	}
}
