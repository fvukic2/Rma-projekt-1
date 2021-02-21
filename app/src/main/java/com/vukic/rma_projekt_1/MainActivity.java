package com.vukic.rma_projekt_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickInterface {

    private RecyclerView recyclerView;
    private AdapterListe adapterListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterListe = new AdapterListe(this);
        adapterListe.setItemClickInterface(this);

        recyclerView.setAdapter(adapterListe);

        String url = getString(R.string.REST_API_KEY);
        System.out.println(url);

        RESTTask task = new RESTTask();
        task.execute(url);

    }

    private class RESTTask extends AsyncTask<String, Void, List<results>> {
        @Override
        protected List<results> doInBackground(String... strings) {
            String adresa = strings[0];
            try {
                URL url = new URL(adresa);
                System.out.println("PP###############################################"+adresa);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader((httpURLConnection.getInputStream()));
                System.out.println("#####################################################"+inputStreamReader);

                BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
                System.out.println("###############################################"+bufferedReader);

                Odgovor odgovor = new Gson().fromJson(bufferedReader, Odgovor.class);
                System.out.println("Odgovor:####################################");
                System.out.println("Odgovor:####################################");

                bufferedReader.close();
                inputStreamReader.close();

/*                if (odgovor.isAdult) {
                    return null;
                }*/
                return odgovor.getResults();

            } catch (MalformedURLException e) {
                Log.e("Problem adresa", e.getMessage());
            } catch (IOException e) {
                Log.e("Problem pristupa", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<results> results) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            adapterListe.setResults(results);
            adapterListe.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(results results) {
        System.out.println("*************************************************");
        System.out.println(results.getOriginal_title());
        Intent intent = new Intent(this, DetaljiActivity.class);
        intent.putExtra("film", results);
        startActivity(intent);

    }

}
