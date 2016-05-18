package com.example.ramazan.fightgang;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Handler;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by ramazan on 13.05.2016.
 */
public class MainActivity extends ActionBarActivity{

    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private TabsPagerAdapter adapter;
    ActionBar actionBar;
    public int SelectTabİndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow64x64back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            // This method will be invoked when a new page becomes selected
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        SelectTabİndex = 0;
                        RefleshFragment(position);
                        break;
                    case 1:
                        SelectTabİndex = 1;
                        RefleshFragment(position);
                        break;
                    case 2:
                        SelectTabİndex = 2;
                        RefleshFragment(position);
                        break;
                    default:

                }
            }
        });
    }

    public void RefleshFragment(int position){
        //Fragment fragment = ((TabsPagerAdapter)pager.getAdapter()).getFragment(position);
        Fragment fragment=adapter.getFragment(position);
        if (fragment != null)
        {
            fragment.onResume();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.reflesh==item.getItemId()){
            switch (SelectTabİndex) {
                case 0:
                    adapter.instantiateFragment(SelectTabİndex);
                    break;
                case 1:
                    adapter.instantiateFragment(SelectTabİndex);
                    break;
                case 2:
                    adapter.instantiateFragment(SelectTabİndex);
                    break;
                    default:break;
            }
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public  boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
