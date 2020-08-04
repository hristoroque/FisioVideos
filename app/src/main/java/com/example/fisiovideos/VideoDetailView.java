package com.example.fisiovideos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fisiovideos.video.Video;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VideoDetailView extends AppCompatActivity {
    private static final String PATH_VIDEOS = "videos";
    String user_name;

    private TextView tv_tittle;
    private TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail_view);
        user_name = getIntent().getStringExtra("USER");

        initialComponents();
        getFirebaseData();
    }

    private void initialComponents() {
        tv_tittle = (TextView)findViewById(R.id.title);
        tv_description = (TextView)findViewById(R.id.description);
    }

    private void getFirebaseData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_VIDEOS);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                Video video_data = dataSnapshot.getValue(Video.class);
                video_data.setId(dataSnapshot.getKey());
                if(video_data.getName().equals(user_name)){
                    //Log.d("tag", "IGUALEEEEEEEEEEEEEEEEEEEEEEEEEEEES " + user_name);
                    //Log.d("tag", "IGUALEEEEEEEEEEEEEEEEEEEEEEEEEEEES " + video_data.getDescription() +" " + video_data.getName());
                    tv_tittle.setText(video_data.getName());
                    tv_description.setText(video_data.getDescription());
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goToVideoListView(View view){
        Intent intent = new Intent(this,VideoListView.class);
        startActivity(intent);
    }

    public void goToVideo360(View view){
        Intent intent = new Intent(this,Video360.class);
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
