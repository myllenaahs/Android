package com.example.asynctask;

import android.os.Bundle; 
import android.app.Activity; 
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView; 
public class MainActivity extends Activity { 
	private TextView text; 
	private Button bt; 
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main); 
		text = (TextView) findViewById(R.id.texto); 
		bt = (Button) findViewById(R.id.botao); 
		} 
	
	
	public void processamento(View view){ 
		Num num = new Num(text, bt); 
		// Executa o doInBackground e passa o valor 50 como parâmetro num.execute(50); 
		} 
}

