package com.example.ourfirst.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.ourfirst.R;
import com.example.ourfirst.adapter.MyPagerAdapter;
import com.example.ourfirst.bean.ImageBean;
import com.example.ourfirst.entity.TabEntity;
import com.example.ourfirst.fragment.HomeFragment1;
import com.example.ourfirst.fragment.HomePageFragment;
import com.example.ourfirst.fragment.OrganizationFragment;
import com.example.ourfirst.util.DatabaseHelper;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private Banner mBanner;
    DatabaseHelper myhelper;
    private List<ImageBean> mList = new ArrayList<>();
    private String[] mTitles = {"社团活动", "校园社团", "个人中心"};
    private int stu_id;
    //未选中（一 一对应）
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect, R.mipmap.collect_unselect,
            R.mipmap.my_unselect};
    //选中显示
    private int[] mIconSelectIds = {
            R.mipmap.home_selected0, R.mipmap.collect_selected0,
            R.mipmap.my_selected0};

    //存放Fragment的集合
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        System.out.println("这里是HomeActivity的username："+username);
        myhelper = new DatabaseHelper(HomeActivity.this);
        SQLiteDatabase sdb1=myhelper.getWritableDatabase();
        Cursor cursor1 = sdb1.rawQuery("select * from user where username=?", new String[]{username});
        if (cursor1 != null && cursor1.getColumnCount() > 0) {
            while (cursor1.moveToNext()) {
                stu_id = cursor1.getInt(0);
                cursor1.close();
            }
            sdb1.close();}
        System.out.println("this is HomeActivityid+++++++++="+stu_id);
    }

    @Override
    protected void initData() {
        mList.add(new ImageBean(R.mipmap.a));
        mList.add(new ImageBean(R.mipmap.b));
        mList.add(new ImageBean(R.mipmap.a));
        mList.add(new ImageBean(R.mipmap.b));
        mList.add(new ImageBean(R.mipmap.a));
        mFragments.add(HomeFragment1.newInstance());
        mFragments.add(OrganizationFragment.newInstance());
        mFragments.add(HomePageFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
        //切换
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }
}