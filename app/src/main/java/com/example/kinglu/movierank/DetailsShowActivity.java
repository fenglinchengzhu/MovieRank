package com.example.kinglu.movierank;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kinglu.movierank.adapter.MovieCelebrityAdapter;
import com.example.kinglu.movierank.data.MovieSubjectInfoBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsShowActivity extends AppCompatActivity {
    private static final String TAG = DetailsShowActivity.class.getSimpleName();
    private static final String API_KEY = "?apikey=0b2bdeda43b5688921839c8ecb20399b";
    private static final String SUBJECT_URL = "https://api.douban.com/v2/movie/subject/";
    private static final int UPDATE_DETAILS = 1; // 更新ui的动作常量

    // 控件
    private ImageView mImageView;
    private TextView mTextTitle;
    private TextView mTextDirectors;
    private TextView mTextWriters;
    private TextView mTextCasts;
    private TextView mTextGenres;
    private TextView mTextRating;
    private TextView mTextDetail;
    private TextView mTextComments;
    // 演员信息列表
    private List<MovieCelebrity> mMovieCelebrityList = new ArrayList<>();
    private MovieCelebrityAdapter mMovieCelebrityAdapter;
    private MovieSubjectInfoBean mMovieSubjectInfoBean = new MovieSubjectInfoBean();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_DETAILS:
                    // 更新ui、显示电影详情
                    setUpdateDetails();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_show_layout);

        Intent intent = getIntent();
        String movieUrl = SUBJECT_URL + intent.getStringExtra("image_details") + API_KEY;
        Log.d("DetailsShowActivity", "onCreate: " + movieUrl);

        // 加载控件
        mImageView = (ImageView) findViewById(R.id.image_poster);
        mTextDirectors = (TextView) findViewById(R.id.text_directors);
        mTextWriters = (TextView) findViewById(R.id.text_writers);
        mTextCasts = (TextView) findViewById(R.id.text_casts);
        mTextGenres = (TextView) findViewById(R.id.text_genres);
        mTextRating = (TextView) findViewById(R.id.text_rating);
        mTextDetail = (TextView) findViewById(R.id.textView_detail);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextComments = (TextView) findViewById(R.id.textView_comments);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.celebrity_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 解析subject数据
        getSubjectInfo(movieUrl);
        mMovieCelebrityAdapter = new MovieCelebrityAdapter(mMovieCelebrityList, getBaseContext());
        recyclerView.setAdapter(mMovieCelebrityAdapter);
        Button button = (Button)findViewById(R.id.comments_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CommentsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void getSubjectInfo(final String url) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String respanseData = response.body().string();

                    // 解析获得的数据
                    Gson gson = new Gson();
                    mMovieSubjectInfoBean = gson.fromJson(respanseData, MovieSubjectInfoBean.class);

                    // 更新UI
                    Message message = new Message();
                    message.what = UPDATE_DETAILS;
                    handler.sendMessage(message);
                    Log.d(TAG, "子线程success");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "failed");
                }
            }
        }).start();
    }

    public void setUpdateDetails() {
        // 电影名
        String temp = "";
        temp = mMovieSubjectInfoBean.getTitle();
        mTextTitle.setText(temp);
        temp = "";

        // 电影海报
        Glide.with(mImageView.getContext()).load(mMovieSubjectInfoBean.getImages().getSmall()).into(mImageView);
        Log.d(TAG, mMovieSubjectInfoBean.getImages().getSmall()); // 海报

        // 导演
        for (int i = 0; i < mMovieSubjectInfoBean.getDirectors().size(); i++) {
            String name = mMovieSubjectInfoBean.getDirectors().get(i).getName();
            Log.d(TAG, "导演: " + name);
            String imageUrl = mMovieSubjectInfoBean.getDirectors().get(i).getAvatars().getSmall();
            Log.d(TAG, "导演海报: " + imageUrl);
            String id = mMovieSubjectInfoBean.getDirectors().get(i).getId();
            Log.d(TAG, "导演: " + id);
            temp += name;
            mMovieCelebrityList.add(new MovieCelebrity(name, imageUrl, id))
            ;
            mMovieCelebrityAdapter.notifyDataSetChanged();
        }
        mTextDirectors.setText("导演:" + temp);
        Log.d(TAG, temp);
        temp = "";

        // 编剧
        for (int i = 0; i < mMovieSubjectInfoBean.getWriters().size(); i++) {
            String name = mMovieSubjectInfoBean.getWriters().get(i).getName();
            Log.d(TAG, "编剧: " + name);
            String id = mMovieSubjectInfoBean.getWriters().get(i).getId();
            Log.d(TAG, "编剧id " + id);

            temp += name;
        }
        mTextWriters.setText("编剧:" + temp);
        Log.d(TAG, temp);
        temp = "";

        // 演员
        for (int i = 0; i < mMovieSubjectInfoBean.getCasts().size(); i++) {
            String name = mMovieSubjectInfoBean.getCasts().get(i).getName();
            String imageUrl = mMovieSubjectInfoBean.getCasts().get(i).getAvatars().getSmall();
            String id = mMovieSubjectInfoBean.getCasts().get(i).getId();
            temp += name;

            mMovieCelebrityList.add(new MovieCelebrity(name, imageUrl, id))
            ;
            mMovieCelebrityAdapter.notifyDataSetChanged();
        }
        mTextCasts.setText("主演:" + temp);

        // 类型
        mTextGenres.setText("类型: " + mMovieSubjectInfoBean.getGenres().toString());
        Log.d(TAG, mMovieSubjectInfoBean.getGenres().toString());
        // 评分
        mTextRating.setText("评分：" + mMovieSubjectInfoBean.getRating().getAverage());
        Log.d(TAG, " " + mMovieSubjectInfoBean.getRating().getAverage());
        // 内容简介
        mTextDetail.setText(mMovieSubjectInfoBean.getSummary());

        // 影评
       mTextComments.setText(mMovieSubjectInfoBean.getPopular_comments().get(0).getContent());
    }
}
