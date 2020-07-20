package com.example.fisiovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VideoDetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail_view);
    }

    public void goToVideoListView(View view){
        Intent intent = new Intent(this,VideoListView.class);
        startActivity(intent);
    }
}
