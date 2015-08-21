package com.example.opensesame;

import java.net.*;
import java.io.*;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class opensesame extends Activity {

	Socket s0;
	PrintStream PR;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
Parse.initialize(this, "K8OB4nELNSl1rBM835CYcA33kaEJYkvfrgHhHOns", "q5to6ov3ljmdIVQkV6bLZZCf6L0BtlpLEsrorgqk");
		
		ParsePush.subscribeInBackground("", new SaveCallback() {

			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					Log.d("com.parse.push",
							"successfully subscribed to the broadcast channel.");
				} else {
					Log.e("com.parse.push", "failed to subscribe for push", e);
				}

			}
		});

		WebView myWebView = (WebView) findViewById(R.id.webview);
		myWebView.getSettings().setJavaScriptEnabled(true);

		myWebView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		myWebView.loadUrl("http://raspidoor.pagekite.me/stream_simple.html");

		Button buttonOpen = (Button) findViewById(R.id.buttonOpen);
		buttonOpen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				message = "Open";
				SendMessage sendMessageTask = new SendMessage();
				sendMessageTask.execute();
			}
		});

		Button buttonClose = (Button) findViewById(R.id.buttonClose);
		buttonClose.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				message = "Close";
				SendMessage sendMessageTask = new SendMessage();
				sendMessageTask.execute();

			}
		});

	}

	private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			while (true) {
				try {

					s0 = new Socket("10.176.67.119", 4445); // connect to the
															// server
					PR = new PrintStream(s0.getOutputStream());

					PR.print(message);
					s0.close(); // closing the connection

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

		}

	}
}
