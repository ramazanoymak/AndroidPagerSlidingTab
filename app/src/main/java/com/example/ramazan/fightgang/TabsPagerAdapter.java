package com.example.ramazan.fightgang;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by ramazan on 13.05.2016.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private final String[] TITLES = { "Arena", "Chat", "Me"};
    ArenaFragment arenaFragment;
    ChatFragment chatFragment;
    MeFragment meFragment;
    HashMap<Integer,String> mFragmentTags;
    FragmentManager mFragmentManager;
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentTags = new HashMap<Integer,String>();
        mFragmentManager=fm;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                 arenaFragment=new ArenaFragment();
                return arenaFragment;

            case 1:
                chatFragment=new ChatFragment();
                return chatFragment;
            case 2:
                meFragment=new MeFragment();
                return meFragment;
        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);

        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }
    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    public void instantiateFragment(int position){

        switch (position)
        {
            case 0:
            arenaFragment.onResume();
            break;
        case 1:
        chatFragment.onResume();
        break;
        case 2:
        meFragment.onResume();
        break;
        default:
    }
    }
}
