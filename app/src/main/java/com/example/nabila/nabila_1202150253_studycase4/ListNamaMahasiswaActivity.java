package com.example.nabila.nabila_1202150253_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNamaMahasiswaActivity extends AppCompatActivity {
    //pendeklarasian untuk setiap variable
    ListView list;
    private ProgressDialog progress;
    //list nama yang akan ditampilkan
    private String [] name = {"MIA", "ALIF", "NADYA", "HAFIDZ", "LINDA", "RAFTIANA", "GITA",
            "TAMARA", "SYAMIL", "SYAIMA", "AMEL", "NAZLA", "RIDWAN", "NABILA"};
    private MyTask task;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //kondisi pada saat orientasi menjadi landscape
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
        //kondisi pada saat orientasi menjadi potrait
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_nama_mahasiswa);
        //melakukan inisiasi pada id
        list = (ListView)findViewById(R.id.list_nama);
        list.setVisibility(View.VISIBLE);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

    }
    //mendeklarasikan button untuk memprogress dan list nama mahasiswa
    public void Mulai(View view) {
        //untuk menjalankan progressdialog yang akan mendata nama mahasiswa
        progress = new ProgressDialog(ListNamaMahasiswaActivity.this);
        progress.setMax(15);
        progress.setMessage("Loading Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //ketika progress dialog dicancel
        progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                task.cancel(true);
                dialogInterface.dismiss();
            }
        });
        //menampilkan progressdialog
        progress.show();
        //untuk menjalankan fungsi yang ada pada mytask
        new MyTask().execute();
    }
    //menjalankan adapter untuk list nama mahasiswa
    class MyTask extends AsyncTask<Void, String, String>{

        ArrayAdapter<String> adapter;
        private int counter=1;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>)list.getAdapter();
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String Name : name){
                publishProgress(Name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //menjalankan adapter untuk list dan untuk progressdialog
        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

            Integer current_status = (int)((counter/(float)name.length)*100);
            progress.setProgress(current_status);
            progress.setProgress(current_status);
            progress.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        @Override
        protected void onPostExecute(String result){
            //remove progressdialog
            progress.dismiss();
            list.setVisibility(View.VISIBLE);
        }

    }
}
