package com.example.fisiovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToVideoListView(View view){
        Intent intent = new Intent(this,VideoListView.class);
        startActivity(intent);
    }

    public void goToVideoCreateView(View view){
        Intent intent = new Intent(this,VideoCreateView.class);
        startActivity(intent);
    }

    public void goToRegistrarPaciente(View view){
        Intent intent = new Intent(this,RegistrarPaciente.class);
        startActivity(intent);
    }
}
