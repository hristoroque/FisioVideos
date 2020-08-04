package com.example.fisiovideos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fisiovideos.video.Video;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoListView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> nombre_ejercicios;
    private static final String PATH_VIDEOS = "videos";
    private static final String PATH_VIDEO = "video";
    private static final String PATH_NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list_view);
        recyclerView = (RecyclerView) findViewById(R.id.videoListView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        nombre_ejercicios = new ArrayList<String>();
        agregarEjercicios(nombre_ejercicios);
        VideoItemAdapter adapter = new VideoItemAdapter(nombre_ejercicios);
        recyclerView.setAdapter(adapter);
    }

    private void agregarEjercicios(final ArrayList<String> nombre_ejercicios) {

        Video video1 = new Video("video1","algo de este video supongo");
        Video video2 = new Video("video2","algo de este video supongo 2");
        Video video3 = new Video("video3","algo de este video supongo 2");

        /*
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference  reference = database.getReference(PATH_VIDEOS);


        reference.push().setValue(video1);
        reference.push().setValue(video2);
        reference.push().setValue(video3);
        */

        nombre_ejercicios.add(video1.getName());
        nombre_ejercicios.add(video2.getName());
        nombre_ejercicios.add(video3.getName());
    }

    public void goToVideoDetailView(View view){
        Intent intent = new Intent(this,VideoDetailView.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:
                Intent intent = new Intent(this,LoginActivity.class);
                AuthUI.getInstance().signOut(this);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
