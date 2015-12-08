package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class Num extends AsyncTask<Integer, Integer, Void> {

	private TextView text;
	private Button bt;

	public Num(TextView text, Button bt) {
		this.text = text;
		this.bt = bt;
	} 
	
	@Override
	protected void doInBackground(Integer... params) {
		int n = params[0];
		int i = 0;
		while (i < n) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			publishProgress(i);
			i++;
		}
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		text.setText(String.valueOf(values[0]));
	}
}
