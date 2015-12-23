package br.edu.ifpb.examplelogin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifpb.examplelogin.R;
import br.edu.ifpb.examplelogin.asynctask.LoginAsyncTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enviar = (Button)findViewById(R.id.enviarButton);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ExampleLogin", "Clique no botão de Login");

                EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
                String nome = nomeEditText.getText().toString();
                
                EditText senhaEditText = (EditText) findViewById(R.id.senhaEditText);
                String senha = senhaEditText.getText().toString();

                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(view.getContext());
                String[] val = {nome, senha};

                loginAsyncTask.execute(val);
            }
        });
    }
}