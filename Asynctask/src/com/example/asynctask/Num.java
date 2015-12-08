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
	} // � onde acontece o processamento

	// Este m�todo � executado em uma thread a parte,
	// ou seja ele n�o pode atualizar a interface gr�fica,
	// por isso ele chama o m�todo onProgressUpdate,
	// o qual � executado pela
	// UI thread.

	@Override
	protected Void doInBackground(Integer... params) {
		int n = params[0];
		int i = 0;
		while (i < n) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Notifica o Android de que ele precisa
			// fazer atualiza��es na
			// tela e chama o m�todo onProgressUpdate
			// para fazer a atualiza��o da interface gr�fica
			// passando o valor do
			// contador como par�metro.

			publishProgress(i);
			i++;
		}
	}

	// � invocado para fazer uma atualiza��o na
	// interface gr�fica
	@Override
	protected void onProgressUpdate(Integer... values) {
		text.setText(String.valueOf(values[0]));
	}
}
