package com.example.fisiovideos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.vr.ndk.base.AndroidCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {
    private ArrayList<String> videos;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView v){
            super(v);
            textView = v;
            context = v.getContext();
        }

    }

    public VideoItemAdapter(ArrayList<String> videos){
        this.videos = videos;
    }

    @Override
    public VideoItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType){
        final TextView ve = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("tag", ve.getText().toString());
                Intent intent = new Intent(context,VideoDetailView.class);
                intent.putExtra("USER", ve.getText().toString());
                context.startActivity(intent);
            }
        }) ;
        ViewHolder vh = new ViewHolder(ve);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView.setText(videos.get(position));
    }

    @Override
    public int getItemCount(){
        return videos.size();
    }
}
