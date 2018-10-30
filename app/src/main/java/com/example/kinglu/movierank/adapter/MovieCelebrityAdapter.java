package com.example.kinglu.movierank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kinglu.movierank.CelebrityShowActivity;
import com.example.kinglu.movierank.MovieCelebrity;
import com.example.kinglu.movierank.R;

import java.util.List;

/**
 * Created by 风临城城主 on 2018/10/24.
 */

public class MovieCelebrityAdapter extends RecyclerView.Adapter<MovieCelebrityAdapter.ViewHolder> {
    private static final String TAG = MovieCelebrityAdapter.class.getSimpleName();
    private List<MovieCelebrity> mMovieCelebrityList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView celebrityImage;
        TextView celebrityName;
        View celebrityView;

        public ViewHolder(View view){
            super(view);
            celebrityImage = (ImageView)view.findViewById(R.id.celebrity_image);
            celebrityName = (TextView)view.findViewById(R.id.celebrity_name);
            celebrityView = view;
        }
    }

    public MovieCelebrityAdapter(List<MovieCelebrity> movieCelebrityList, Context context) {
        mMovieCelebrityList = movieCelebrityList;
        mContext = context;
    }

    @NonNull
    @Override
    public MovieCelebrityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celebrity_layout, parent, false);
        final MovieCelebrityAdapter.ViewHolder holder = new MovieCelebrityAdapter.ViewHolder(view);
        holder.celebrityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MovieCelebrity movieCelebrity = mMovieCelebrityList.get(position);
                //  跳转到显示详情页的活动
                Intent intent = new Intent(view.getContext(), CelebrityShowActivity.class);
                intent.putExtra("celebrity_detail", movieCelebrity.getCelebrityId());
                Log.d(TAG, "celebrity information:" + movieCelebrity.getCelebrityId());
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCelebrityAdapter.ViewHolder holder, int position) {
        MovieCelebrity movieCelebrity = mMovieCelebrityList.get(position);
        holder.celebrityName.setText(movieCelebrity.getCelebrityName());
        if (movieCelebrity.getCelebrityImageUrl() != null){
            Glide.with(mContext).load(movieCelebrity.getCelebrityImageUrl()).into(holder.celebrityImage);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieCelebrityList.size();
    }
}
