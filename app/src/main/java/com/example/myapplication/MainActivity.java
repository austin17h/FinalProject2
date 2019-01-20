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
import java.util.Collections;

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
    ArrayList<Integer> player1moves, player2moves, player3moves, storage1, storage2, storage3, playback1, playback2, playback3;
    boolean[] isUnSelected;
    boolean isDuringRound;
    int index;
    View tabby;
    Bundle bundle, endOfRound;

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
        isDuringRound = true;
        player1max = 3;
        player2max = 3;
        player3max = 3;
        player1moves = new ArrayList<Integer>();
        player2moves = new ArrayList<Integer>();
        player3moves = new ArrayList<Integer>();
        storage1 = new ArrayList<Integer>();
        storage2 = new ArrayList<Integer>();
        storage3 = new ArrayList<Integer>();

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

        bundle = new Bundle();
        endOfRound = new Bundle();
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0)
                {
                    frag0.mNext.setEnabled(true);
                    frag0.mNext.setClickable(true);
                }
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

    public void reset()
    {
        player1moves = new ArrayList<Integer>();
        player2moves = new ArrayList<Integer>();
        player3moves = new ArrayList<Integer>();
        tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
        tabby.setClickable(true);
        tabby.setAlpha(1F);
        tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);
        tabby.setClickable(true);
        tabby.setAlpha(1F);
        tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
        tabby.setClickable(true);
        tabby.setAlpha(1F);
        frag1.moves = 5; frag2.moves = 5; frag3.moves = 5;
        frag0.at1 = 2; frag0.at2 = 2; frag0.at3 = 2;
        frag1.movesLeft.setText("Moves Left: " + frag1.moves);
        frag1.mUp.setEnabled(true); frag1.mDown.setEnabled(true); frag1.mLeft.setEnabled(true); frag1.mRight.setEnabled(true); frag1.mUp.setClickable(true); frag1.mDown.setClickable(true); frag1.mLeft.setClickable(true); frag1.mRight.setClickable(true);
        frag1.DisablePreviousMove();
        frag2.DisablePreviousMove();
        frag3.DisablePreviousMove();
        isUnSelected[0] = false; isUnSelected[1] = false; isUnSelected[2] = false;
        for(int i = 0; i < storage1.size(); i++)
        {
            frag1.squares[storage1.get(i)].setImageBitmap(frag1.empty);
        }
        for(int i = 0; i < storage2.size(); i++)
        {
            frag1.squares[storage2.get(i)].setImageBitmap(frag1.empty);
        }
        for(int i = 0; i < storage3.size(); i++)
        {
            frag1.squares[storage3.get(i)].setImageBitmap(frag1.empty);
        }
        int limit = storage1.size() - player1max;
        for(int i = 0; i < limit; i++)
        {
            storage1.remove(0);
        }
        limit = storage2.size() - player2max;
        for(int i = 0; i < limit; i++)
        {
            storage2.remove(0);
        }
        limit = storage3.size() - player3max;
        for(int i = 0; i < limit; i++)
        {
            storage3.remove(0);
        }

        for(int i = 0; i < storage1.size(); i++)
        {
            player1moves.add(0, storage1.get(i));
        }
        for(int i = 0; i < storage2.size(); i++)
        {
            player2moves.add(0, storage2.get(i));
        }
        for(int i = 0; i < storage3.size(); i++)
        {
            player3moves.add(0, storage3.get(i));
        }

        bundle.putIntegerArrayList("firstmoves1", player1moves);
        bundle.putIntegerArrayList("firstmoves2", player2moves);
        bundle.putIntegerArrayList("firstmoves3", player3moves);
        frag1.setBoard(storage1, storage2, storage3);
        Collections.reverse(frag1.moveList);
        Collections.reverse(frag2.moveList);
        Collections.reverse(frag3.moveList);
    }

    public void navigate1(int i, ArrayList<Integer> x)
    {
        if(i == 1) {
            isUnSelected[0] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
            endOfRound.putIntegerArrayList("playback1", x);
        }
        else if(i == 2) {
            isUnSelected[1] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
            endOfRound.putIntegerArrayList("playback2", x);
        }
        else if(i == 3) {
            isUnSelected[2] = true;
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
            tabby.setClickable(false);
            tabby.setAlpha(0.3F);
            endOfRound.putIntegerArrayList("playback3", x);
        }
        if(isUnSelected[0] && isUnSelected[1] && isUnSelected[2])
        {
            tabby =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
            tabby.setClickable(true);
            tabby.setAlpha(1F);
            frag0.setArguments(bundle);
            frag0.retrieveB();
            isDuringRound = false;

            frag0.setArguments(endOfRound);
            frag0.retrieveE();
            frag0.setArguments(bundle);
        }
    }

    public void storeIt(int playerid, ArrayList<Integer> x) {
        if (playerid == 1) {
            storage1 = x;
            bundle.putIntegerArrayList("storage1", storage1);
        }
        else if (playerid == 2) {
            storage2 = x;
            bundle.putIntegerArrayList("storage2", storage2);
        }
        else if (playerid == 3) {
            storage3 = x;
            bundle.putIntegerArrayList("storage3", storage3);
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
                bundle.putIntegerArrayList("firstmoves1", player1moves);
                bundle.putIntegerArrayList("firstmoves2", player2moves);
                bundle.putIntegerArrayList("firstmoves3", player3moves);
                frag0 = Fragment0.newInstance();
                frag0.setArguments(bundle);
                return frag0;
            }
            else if(position == 1) {
                Log.i(TAG, "getItem frag1");
                frag1 = Fragment1.newInstance(1);
                if(storage1.size() > 0)
                    bundle.putIntegerArrayList("storage1", storage1);
                else{
                    bundle.putIntegerArrayList("firstmoves1", player1moves);
                    bundle.putIntegerArrayList("firstmoves2", player2moves);
                    bundle.putIntegerArrayList("firstmoves3", player3moves);
                }
                frag1.setArguments(bundle);
                return frag1;
            }
            else if(position == 2){
                frag2 = Fragment1.newInstance(2);
                if(storage2.size() > 0)
                    bundle.putIntegerArrayList("storage2", storage2);
                else{
                    bundle.putIntegerArrayList("firstmoves1", player1moves);
                    bundle.putIntegerArrayList("firstmoves2", player2moves);
                    bundle.putIntegerArrayList("firstmoves3", player3moves);
                }
                frag2.setArguments(bundle);
                return frag2;
            }
            else
                frag3 = Fragment1.newInstance(3);
                if(storage3.size() > 0)
                    bundle.putIntegerArrayList("storage3", storage3);
                else{
                    bundle.putIntegerArrayList("firstmoves1", player1moves);
                    bundle.putIntegerArrayList("firstmoves2", player2moves);
                    bundle.putIntegerArrayList("firstmoves3", player3moves);
                }
                frag3.setArguments(bundle);
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
