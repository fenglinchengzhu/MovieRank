package com.example.kinglu.movierank.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kinglu.movierank.MovieNews;
import com.example.kinglu.movierank.adapter.MovieNewsAdapter;
import com.example.kinglu.movierank.R;
import com.example.kinglu.movierank.data.MovieInTheatersBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 新片排行榜
 * <p>
 * Created by 风临城城主 on 2018/10/10.
 */

public class MovieFragment extends Fragment {
    // API
    private static final String NEW_MOVIE_API = "https://api.douban.com/v2/movie/new_movies?apikey=0b2bdeda43b5688921839c8ecb20399b&client=&udid=";
    // TAG
    private static final String TAG = MovieFragment.class.getSimpleName();
    // 更新ui的动作常量
    private static final int UPDATE_FRAGMENT = 1;
    // 电影信息列表
    private List<MovieNews> mMovieRankList = new ArrayList<>();
    // recycler view 适配器
    private MovieNewsAdapter mMovieRankAdapter;

    private Handler mHandlerMovieRank = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_FRAGMENT:
                    // 更新界面
                    MovieInTheatersBean movieInTheatersBean = (MovieInTheatersBean) msg.obj;
                    setUpdateFragment(movieInTheatersBean);
                    break;
                default:
                    Log.d(TAG, "can not get data");
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movie_recycler);
        // 瀑布流布局
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // 解析数据、初始化将要上映的电影列表
        getMovieRank();

        mMovieRankAdapter = new MovieNewsAdapter(mMovieRankList, getContext());
        recyclerView.setAdapter(mMovieRankAdapter);
        return view;
    }

    private void getMovieRank() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(NEW_MOVIE_API).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    // 解析获得的数据
                    Gson gson = new Gson();
                    MovieInTheatersBean movieInTheatersBean = gson.fromJson(responseData, MovieInTheatersBean.class);

                    // 更新UI
                    Message message = Message.obtain();
                    message.what = UPDATE_FRAGMENT;
                    message.obj = movieInTheatersBean;
                    mHandlerMovieRank.sendMessage(message);

                    Log.d(TAG, "加载电影信息：success");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "加载电影信息: failed");
                }
            }
        }).start();
    }

    public void setUpdateFragment(MovieInTheatersBean movieInTheatersBean) {
        List<MovieInTheatersBean.SubjectsBean> subjectsBeansList = movieInTheatersBean.getSubjects();

        for (MovieInTheatersBean.SubjectsBean subjectsBean : subjectsBeansList) {

            // 电影名字
            String movieName = subjectsBean.getTitle();
            Log.d(TAG, "movieName:" + movieName);

            // 电影海报
            String imageUrl = subjectsBean.getImages().getSmall();
            Log.d(TAG, "imageUrl:" + imageUrl);

            // 电影评分
            String movieScore = "" + subjectsBean.getRating().getAverage();
            Log.d(TAG, "movieScore:" + movieScore);
            if (movieScore.equals("0.0")) {
                movieScore = "暂无评分";
            }

            // 电影id
            String movieId = subjectsBean.getId();
            Log.d(TAG, "movieId:" + movieId);

            mMovieRankList.add(new MovieNews(movieScore, imageUrl, movieName, movieId))
            ;
            mMovieRankAdapter.notifyDataSetChanged();
        }
    }
}
