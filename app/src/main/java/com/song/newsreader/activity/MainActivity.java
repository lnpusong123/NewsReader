package com.song.newsreader.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.song.newsreader.R;
import com.song.newsreader.fragment.BaseFragment;
import com.song.newsreader.fragment.FragmentFactory;
import com.song.newsreader.utils.UIUtils;
import com.song.newsreader.widget.PagerTab;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, DrawerLayout.DrawerListener {

    private ViewPager pager;
    private PagerTab pagerTab;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void initActionBar() {
        //获取支持包里的actionbar
        ActionBar actionBar = getSupportActionBar();
        //展示按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        //按钮可以被点击
        actionBar.setHomeButtonEnabled(true);
        //获取到actionbar的开关
        toggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open,R.string.drawer_close);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        pagerTab = (PagerTab) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerListener(this);

        pager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        pagerTab.setViewPager(pager);
        pagerTab.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BaseFragment fragment = (BaseFragment) FragmentFactory.createFragment(position);
        fragment.show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        toggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        toggle.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        toggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        toggle.onDrawerStateChanged(newState);
    }


    class MainAdapter extends FragmentPagerAdapter {

        private String[] tab_names = null;

        public MainAdapter(FragmentManager fm) {
            super(fm);
            tab_names = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK();
    }
}
