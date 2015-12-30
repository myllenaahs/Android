package br.edu.ifpb.examplelogin.activity;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.ifpb.examplelogin.asynctask.Login;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button send = (Button) findViewById(R.id.enviarButton);
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
				String nome = nomeEditText.getText().toString();

				EditText senhaEditText = (EditText) findViewById(R.id.senhaEditText);
				String senha = senhaEditText.getText().toString();

				Login loginAsyncTask = new Login(view.getContext());
				String[] val = { nome, senha };
				
				loginAsyncTask.execute(val);
				
				Toast.makeText(getBaseContext(), "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
			}
		});
	}
}