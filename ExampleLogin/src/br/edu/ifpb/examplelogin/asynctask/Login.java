package br.edu.ifpb.examplelogin.asynctask;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import br.edu.ifpb.examplelogin.util.ServicoHttp;
import br.edu.ifpb.examplelogin.util.Resposta;

public class Login extends AsyncTask<String, Void, Resposta>{

    Context context;

    public Login(Context activity) {

        this.context = activity;
    }

    @Override
    protected void onPreExecute() {

        Log.i("ExampleLogin", "OnPreExecute");
    }

    @Override
    protected Resposta doInBackground(String... valores) {

        Log.i("ExampleLogin", "doInBackground " + "Nome: "+ valores[0]);
        Log.i("ExampleLogin", "doInBackground " + "Senha: "+ valores[1]);

        Resposta response = null;
        HttpURLConnection connection = null;

        try {

            connection = ServicoHttp.sendPostRequest("servicoservlet");

            int status = connection.getResponseCode();
            String contentValue = ServicoHttp.postHttpContent(connection);

            response = new Resposta(status, contentValue);
            
            

        } catch (MalformedURLException ex) {

            Log.e("ExampleLogin","MalformedURLException: " + ex);

        } catch (IOException ex) {

            Log.e("ExampleLogin","IOException: " + ex);

        } finally {

            connection.disconnect();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Resposta response) {

        try {

            int status = response.getStatusCodeHttp();

            if (status == HttpURLConnection.HTTP_OK) {

                JSONObject json = new JSONObject(response.getContentValue());

                String nome = json.getString("nome");
                Log.i("ExampleLogin", "Nome: " + nome);
                String senha = json.getString("senha");
                Log.i("ExampleLogin", "Senha: " + senha);
                Toast.makeText(context, nome, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("ExampleLogin", "JSONException: " + e);
        }
    }
}