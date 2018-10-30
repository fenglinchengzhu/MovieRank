package com.example.kinglu.movierank;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;

import com.example.kinglu.movierank.adapter.FragmentAdapter;
import com.example.kinglu.movierank.fragment.MovieFragment;
import com.example.kinglu.movierank.fragment.MovieNewsFragment;
import com.example.kinglu.movierank.fragment.QueryFragment;
import com.example.kinglu.movierank.fragment.RankListFragment;
import com.example.kinglu.movierank.fragment.TeleplayFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String[] TITLES = new String[]{"影讯", "排行榜", "TOP250", "电视剧", "分类查询"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.susu);
        setSupportActionBar(toolbar);

        // 初始化主界面
        init();
    }

    private void init() {
        // Tablayout 标签
        List<Fragment> fragments = new ArrayList<>();
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        // 循环注入TabLayout标签
        for (String tab : TITLES) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tab));
        }

        // 加载fragment
        fragments.add(new MovieNewsFragment());  // 影讯
        fragments.add(new MovieFragment());      // 新片排行榜
        fragments.add(new RankListFragment());   // TOP250
        fragments.add(new TeleplayFragment());   // 电视剧
        fragments.add(new QueryFragment());      // 查询

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), TITLES, fragments);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ");
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: ");
            }
        });
    }
}
