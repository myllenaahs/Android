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

/**
 * Created by Rhavy on 01/12/2015.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, Resposta>{

    Context context;

    public LoginAsyncTask(Context activity) {

        this.context = activity;
    }

    @Override
    protected void onPreExecute() {

        Log.i("Android-AsyncTask", "OnPreExecute");
    }

    @Override
    protected Resposta doInBackground(String... valores) {

        Log.i("Android-AsyncTask", "doInBackground " + "Nome: "+ valores[0]);
        Log.i("Android-AsyncTask", "doInBackground " + "Senha: "+ valores[1]);

        Resposta response = null;
        HttpURLConnection connection = null;

        try {

            connection = ServicoHttp.sendPostRequest("servicoservlet");

            int status = connection.getResponseCode();
            String contentValue = ServicoHttp.postHttpContent(connection);

            response = new Resposta(status, contentValue);

        } catch (MalformedURLException ex) {

            Log.e("Android-AsyncTask","MalformedURLException: " + ex);

        } catch (IOException ex) {

            Log.e("Android-AsyncTask","IOException: " + ex);

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
                Log.i("Android-AsyncTask", "Nome: " + nome);
                String senha = json.getString("senha");
                Log.i("Android-AsyncTask", "Senha: " + senha);
                Toast.makeText(context, nome, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {

            Log.e("Android-AsyncTask", "JSONException: " + e);
        }
    }
}