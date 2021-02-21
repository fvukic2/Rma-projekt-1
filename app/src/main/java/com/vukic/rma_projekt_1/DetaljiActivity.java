package com.vukic.rma_projekt_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class DetaljiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);

        Intent intent = getIntent();
        results results = (results)intent.getSerializableExtra("film");

        TextView TextViewOverview = findViewById(R.id.overview);
        TextViewOverview.setText("OVERVIEW: "+ String.valueOf(results.getOverview()));

        TextView original_title = findViewById(R.id.original_title);
        original_title.setText("TITLE:"+String.valueOf(results.getOriginal_title()));

        TextView original_language = findViewById(R.id.original_language);
        original_language.setText("ORIGINAL LANGUAGE:"+String.valueOf(results.getOriginal_language()));

        /*
        ImageView poster_path = findViewById(R.id.poster_path);
        poster_path.setImageURI(Uri.parse(results.getBackdrop_path()));
         */
        Button nazad = findViewById(R.id.button);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new DownloadImageTask(findViewById(R.id.poster_path)).execute(results.getBackdrop_path());
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];

            Bitmap mIcon11 = null;
            if (urldisplay!=null) {
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    System.out.println("ERROR");
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }
            return null;
        }
        protected void onPostExecute(Bitmap result) {
            System.out.println("RESULT:");
            System.out.println(result);
            if(result==null){
                System.out.println("Null");
            }
            else{
                bmImage.setImageBitmap(result);
                bmImage.getLayoutParams().height=400;
                bmImage.getLayoutParams().width=400;
            }
        }
    }
}



