package com.example.kinglu.movierank.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * 电影热映 /  即将上映
 * <p>
 * Created by 风临城城主 on 2018/10/10.
 */

public class MovieNewsFragment extends Fragment {
    // API
    private static final String MOVIE_THEATER_API = "https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b";
    private static final String MOVIE_COMING_API = "https://api.douban.com/v2/movie/coming_soon?apikey=0b2bdeda43b5688921839c8ecb20399b";
    // TAG
    private static final String TAG = MovieNewsFragment.class.getSimpleName();
    // 更新ui的动作常量
    private static final int UPDATE_FRAGMENT = 1;
    // 电影信息列表
    private List<MovieNews> mMovieNewsList = new ArrayList<>();
    private List<MovieNews> mMovieComingList = new ArrayList<>();
    private MovieNewsAdapter mMovieNewsAdapter;
    private MovieNewsAdapter mMovieComingAdapter;

    private Handler mHandlerMovieComing = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_FRAGMENT:
                    // 更新界面
                    MovieInTheatersBean movieInTheatersBean = (MovieInTheatersBean) msg.obj;
                    setUpdateFragment(movieInTheatersBean, mMovieComingList, mMovieComingAdapter);
                    break;
                default:
                    Log.d(TAG, "can not get data");
                    break;
            }
        }
    };

    private Handler mHandlerMovieInTheater = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_FRAGMENT:
                    // 更新界面
                    MovieInTheatersBean movieInTheatersBean = (MovieInTheatersBean) msg.obj;
                    setUpdateFragment(movieInTheatersBean, mMovieNewsList, mMovieNewsAdapter);
                    break;
                default:
                    Log.d(TAG, "can not get data");
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_news_layout, container, false);

        RecyclerView recyclerMovieNews = (RecyclerView) view.findViewById(R.id.movie_news_recycler);
        recyclerMovieNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        RecyclerView recyclerMovieComing = (RecyclerView) view.findViewById(R.id.movie_coming_recycler);
        recyclerMovieComing.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // 解析数据、初始化正在热映的电影列表
        getMovieData(MOVIE_THEATER_API, mHandlerMovieInTheater);
        mMovieNewsAdapter = new MovieNewsAdapter(mMovieNewsList, getContext());
        recyclerMovieNews.setAdapter(mMovieNewsAdapter);

        // 解析数据、初始化将要上映的电影列表
        getMovieData(MOVIE_COMING_API, mHandlerMovieComing);
        mMovieComingAdapter = new MovieNewsAdapter(mMovieComingList, getContext());
        recyclerMovieComing.setAdapter(mMovieComingAdapter);

        return view;
    }

    /**
     * 获取豆瓣电影数据
     *
     * @param url     api地址
     * @param handler 更新ui的handler
     */
    private void getMovieData(final String url, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    // 解析获得的数据
                    Gson gson = new Gson();
                    MovieInTheatersBean movieInTheatersBean = gson.fromJson(responseData, MovieInTheatersBean.class);

                    // 更新UI
                    Message message = Message.obtain();
                    message.what = UPDATE_FRAGMENT;
                    message.obj = movieInTheatersBean;
                    handler.sendMessage(message);

                    Log.d(TAG, "加载电影信息：success");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "加载电影信息: failed");
                }
            }
        }).start();
    }

    /**
     * 更新ui
     *
     * @param movieInTheatersBean 电影数据
     * @param movieNewsList       recycler 电影列表
     * @param movieNewsAdapter    recycler 适配器
     */
    private void setUpdateFragment(MovieInTheatersBean movieInTheatersBean, List<MovieNews> movieNewsList, MovieNewsAdapter movieNewsAdapter) {
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

            movieNewsList.add(new MovieNews(movieScore, imageUrl, movieName, movieId))
            ;
            movieNewsAdapter.notifyDataSetChanged();
        }
    }
}
