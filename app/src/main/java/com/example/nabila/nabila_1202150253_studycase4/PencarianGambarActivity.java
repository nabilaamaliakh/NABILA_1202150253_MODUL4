package com.example.nabila.nabila_1202150253_studycase4;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class PencarianGambarActivity extends AppCompatActivity {
    //deklarasi variable
    EditText url;
    Button search;
    public ImageView image;
    ProgressDialog progress;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //kondisi pada saat orientasi menjadi landscape
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        //kondisi pada saat orientasi menjadi potrait
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pencarian_gambar);

        //
        url = (EditText) findViewById(R.id.et_url);
        search = (Button) findViewById(R.id.bt_klik);
        image = (ImageView) findViewById(R.id.imageView);
    }

        public void Search(View view) {
            String link = url.getText().toString();
            new Download().execute(link);
        }

        private class Download extends AsyncTask<String, Void, Bitmap>{
            @Override
            protected Bitmap doInBackground(String... URL) {
                String imageURL = URL[0];
                Bitmap bitmap = null;
                try {
                    //download image dari url
                    InputStream input = new java.net.URL(imageURL).openStream();
                    //decode bitmap
                    bitmap = BitmapFactory.decodeStream(input);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                //membuat progressDialog
                progress = new ProgressDialog(PencarianGambarActivity.this);
                //mengatur title untuk progressDialog
                progress.setTitle("Download Image");
                //mengatur Message untuk progressDialog
                progress.setMessage("Loading...");
                progress.setIndeterminate(false);
                //memperlihatkan progressDialog
                progress.show();
            }

            @Override
            protected void onPostExecute(Bitmap result){
                //mengatur bitmap menjadi imageView
                image.setImageBitmap(result);
                //menutup progressDialog
                progress.dismiss();
            }
        }
}
