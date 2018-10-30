package com.example.kinglu.movierank;

import com.example.kinglu.movierank.data.MovieInTheatersBean;
import com.example.kinglu.movierank.data.MovieSubjectInfoBean;

/**
 * Created by 风临城城主 on 2018/10/11.
 */

public class MovieNews {
    private String mName;
    private String mImageUrl;
    private String mMovieScore;
    private String mMovieId;
    private String mMovieCast;
    private MovieInTheatersBean.SubjectsBean subjectsBean;
    private MovieSubjectInfoBean movieSubjectInfoBean;

    // Cast
    public MovieNews(String name, String imageUrl, String cast) {
        this.mMovieCast = cast;
        this.mImageUrl = imageUrl;
        this.mName = name;
    }

    public MovieNews(String name, String imageUrl, String movieScore, String movieId) {
        this.mMovieScore = movieScore;
        this.mImageUrl = imageUrl;
        this.mName = name;
        this.mMovieId = movieId;
    }

    public void setmMovieCast(String cast){
        this.mMovieCast = cast;
    }
    public String getmMovieCast() {
        return mMovieCast;
    }

    public String getMovieScore() {
        return mMovieScore;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public MovieInTheatersBean.SubjectsBean getSubjectsBean() {
        return subjectsBean;
    }

    public void setmMovieId(String mMovieId) {
        this.mMovieId = mMovieId;
    }

    public String getmMovieId() {
        return mMovieId;
    }
}
