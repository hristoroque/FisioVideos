package com.example.fisiovideos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VideoListView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list_view);
        recyclerView = (RecyclerView) findViewById(R.id.videoListView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        VideoItemAdapter adapter = new VideoItemAdapter(new String[]{"Hristo Roque", "Iam Contreras", "Solansh"});
        recyclerView.setAdapter(adapter);
    }

    public void goToVideoDetailView(View view){
        Intent intent = new Intent(this,VideoDetailView.class);
        startActivity(intent);
    }
}
