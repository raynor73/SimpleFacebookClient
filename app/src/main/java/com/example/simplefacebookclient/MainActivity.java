package com.example.simplefacebookclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final CallbackManager callbackManager = CallbackManager.Factory.create();
		final LoginButton loginButton = (LoginButton) findViewById(R.id.view_facebook_login_button);
//		final List<String> permissionNeeds = Arrays.asList("user_posts");
		loginButton.setReadPermissions("user_posts");

		loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

			@Override
			public void onSuccess(final LoginResult loginResult) {
				System.out.println("onSuccess");
				final GraphRequest request = GraphRequest.newMeRequest
						(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
						{
							@Override
							public void onCompleted(final JSONObject object, final GraphResponse response)
							{
								// Application code
								Log.v("LoginActivity", response.toString());
								//System.out.println("Check: " + response.toString());
								try
								{
									final String id = object.getString("id");
									final String name = object.getString("name");
									final String email = object.getString("email");
									final String gender = object.getString("gender");
									final String birthday = object.getString("birthday");
									System.out.println(id + ", " + name + ", " + email + ", " + gender + ", " + birthday);
								}
								catch (final JSONException e)
								{
									e.printStackTrace();
								}

							}
						});
				final Bundle parameters = new Bundle();
				parameters.putString("fields", "id,name,email,gender, birthday");
				request.setParameters(parameters);
				request.executeAsync();
			}

			@Override
			public void onCancel()
			{
				System.out.println("onCancel");
			}

			@Override
			public void onError(final FacebookException exception)
			{
				System.out.println("onError");
				Log.v("LoginActivity", exception.getCause().toString());
			}
		});
	}
}
