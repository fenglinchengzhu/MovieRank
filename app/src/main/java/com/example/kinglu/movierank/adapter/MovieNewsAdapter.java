package com.example.kinglu.movierank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kinglu.movierank.DetailsShowActivity;
import com.example.kinglu.movierank.MovieNews;
import com.example.kinglu.movierank.R;

import java.util.List;

/**
 * Created by 风临城城主 on 2018/10/11.
 */

public class MovieNewsAdapter extends RecyclerView.Adapter<MovieNewsAdapter.ViewHolder> {
    private List<MovieNews> mMovieNewsList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieName;
        TextView movieScore;
        View movieView;

        public ViewHolder(View view) {
            super(view);
            movieImage = (ImageView) view.findViewById(R.id.movie_image);
            movieName = (TextView) view.findViewById(R.id.movie_name);
            movieScore = (TextView) view.findViewById(R.id.movie_score);
            movieView = view;
        }
    }

    public MovieNewsAdapter(List<MovieNews> movieNewsList, Context context) {
        mMovieNewsList = movieNewsList;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MovieNews movieNews = mMovieNewsList.get(position);
                Toast.makeText(v.getContext(), movieNews.getName(), Toast.LENGTH_SHORT).show();
                //  跳转到显示详情页的活动
                Intent intent = new Intent(v.getContext(), DetailsShowActivity.class);
                intent.putExtra("image_details", movieNews.getmMovieId());
                Log.d("MainActivity", movieNews.getmMovieId());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieNews movieNews = mMovieNewsList.get(position);
        holder.movieName.setText(movieNews.getName());
        holder.movieScore.setText(movieNews.getMovieScore());
        Glide.with(mContext).load(movieNews.getImageUrl()).into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return mMovieNewsList.size();
    }
}
