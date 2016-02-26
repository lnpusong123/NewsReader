package com.song.newsreader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

	private static BaseActivity mForegroundActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		initActionBar();
	}

	protected abstract void initActionBar();

	protected abstract void initView();
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BaseActivity.mForegroundActivity = this;
	}

	public static BaseActivity getForegroundActivity() {
		// TODO Auto-generated method stub
		return mForegroundActivity;
	}

}
