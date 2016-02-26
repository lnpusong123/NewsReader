package com.song.newsreader.fragment;

import android.support.v4.app.Fragment;

import com.song.newsreader.R;
import com.song.newsreader.utils.DrawableUtils;
import com.song.newsreader.utils.StringUtils;
import com.song.newsreader.utils.UIUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FragmentFactory {

    private static HashMap<Integer, Fragment> mFragments = new HashMap<>();

    private static String[] scenarios = UIUtils.getStringArray(R.array.scenario_id);

    public static Fragment createFragment(int position) {
        Fragment fragment = mFragments.get(position);
        if (null == fragment){
            switch (position){
                case 0:
                    fragment = IndexFragment.newInstance(scenarios[position]);
                    break;
                default:
                    fragment = IndexFragment.newInstance(scenarios[position]);
                    break;
            }

            mFragments.put(position, fragment);
        }
        return fragment;
    }
}
