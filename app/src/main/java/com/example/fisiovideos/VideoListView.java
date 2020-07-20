package com.example.fisiovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VideoListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list_view);
    }

    public void goToVideoDetailView(View view){
        Intent intent = new Intent(this,VideoDetailView.class);
        startActivity(intent);
    }
}
