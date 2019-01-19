package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Fragment0.Interface0, Fragment1.Interface1, Board.InterfaceB{

    final static String TAG = "MainActivity";
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    TabLayout tabLayout;
    Fragment0 frag0;
    Fragment1 frag1;
    Fragment1 frag2;
    Fragment1 frag3;
    int player1max, player2max, player3max, player1first, player2first, player3first;
    ArrayList<Integer> player1moves, player2moves, player3moves;
    boolean[] isUnSelected;
    boolean isFirstRound;
    int index;
    View tabby;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        isUnSelected = new boolean[3];
        isFirstRound = true;
        player1max = 3;
        player2max = 3;
        player3max = 3;
        player1moves = new ArrayList<Integer>();
        player2moves = new ArrayList<Integer>();
        player3moves = new ArrayList<Integer>();

        player1first = (int)(Math.random()*99);
        player2first = (int)(Math.random()*99);
        while(player2first == player1first)
            player2first = (int)(Math.random()*99);
        player3first = (int)(Math.random()*99);
        while(player3first == player1first || player2first == player3first)
            player3first = (int)(Math.random()*99);
        player1moves.add(player1first);
        player2moves.add(player2first);
        player3moves.add(player3first);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                index = tab.getPosition();
                if(index == 0) {
                    Log.i(TAG, "OnTabUnselected:");
                    tabby = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
                    tabby.setClickable(false);
                    tabby.setAlpha(0.3F);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bundle = new Bundle();
        bundle.putIntegerArrayList("firstmove1",player1moves);
        bundle.putIntegerArrayList("firstmove2", player2moves);
        bundle.putIntegerArrayList("firstmove3", player3moves);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void navigate()
    {

    }

    public void navigate1(int i)
    {
        if(i == 1) {
            isUnSelected[0] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
        }
        else if(i == 2) {
            isUnSelected[1] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
        }
        else if(i == 3) {
            isUnSelected[2] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
        }
        if(isUnSelected[0] && isUnSelected[1] && isUnSelected[2])
        {
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
            tabby.setClickable(true);
            tabby.setAlpha(1F);
            isFirstRound = false;
        }
    }

    public void makeMove(int move, int player){
        if(player ==1)
        {
            player1moves.add(move);
            if(player1moves.size() > player1max)
            {
                player1moves.remove(player1max-1);
            }
        }
        else if(player ==2)
        {
            player2moves.add(move);
            if(player2moves.size() > player2max)
            {
                player2moves.remove(player2max-1);
            }
        }
        else if(player ==3)
        {
            player3moves.add(move);
            if(player3moves.size() > player3max)
            {
                player3moves.remove(player3max-1);
            }
        }
    }

    public boolean isFirstRound()
    {
        return isFirstRound;
    }

    public int firstMove(int player)
    {
        if (player == 1) {
            frag1.firstRound = false;
            return player1first;
        }
        else if(player ==2) {
            frag2.firstRound = false;
            return player2first;
        }
        else {
            frag3.firstRound = false;
            return player3first;
        }
    }
    public void todo_board() {

    }

    public class PagerAdapter extends FragmentPagerAdapter {
        String[] tabTitles = {"Home", "Player1", "Player2", "Player3"};
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                frag0 = Fragment0.newInstance();
                frag0.setArguments(bundle);
                return frag0;
            }
            else if(position == 1) {
                Log.i(TAG, "getItem frag1");
                frag1 = Fragment1.newInstance(1);
                return frag1;
            }
            else if(position == 2){
                frag2 = Fragment1.newInstance(2);
                return frag2;
            }
            else
                frag3 = Fragment1.newInstance(3);
                return frag3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return tabTitles.length;
        }


    }
}
