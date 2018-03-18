package com.example.nabila.nabila_1202150253_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //masuk ke activity list nama mahasiswa
    public void list(View view) {
        Intent intent = new Intent(MainActivity.this, ListNamaMahasiswaActivity.class);
        startActivity(intent);
    }
    //masuk ke activity pencarian gambar
    public void search(View view) {
        Intent intent = new Intent(MainActivity.this, PencarianGambarActivity.class);
        startActivity(intent);
    }
}
