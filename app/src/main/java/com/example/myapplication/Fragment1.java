package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements View.OnClickListener, Board.InterfaceB{
    final static String TAG = "frag1";
    Interface1 mCallback;
    View mRootView;
    Button mUp;
    Button mDown;
    Button mLeft;
    Button mRight;
    TextView player;
    TextView movesLeft;
    Board board;
    int moves, playerid, firstMove, max;
    ArrayList<Integer> moveList;
    boolean isFinishedTurn;
    boolean firstRound;
    boolean allIsFinishedTurn;

    public Fragment1() {
    }

    public static com.example.myapplication.Fragment1 newInstance(int id) {
        com.example.myapplication.Fragment1 fragment = new com.example.myapplication.Fragment1();
        Log.i(fragment.TAG, "newInstance Fragment1");
        fragment.playerid = id;
        fragment.moves = 5;
        fragment.max = 3;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG, "reached 1:");
        mRootView = inflater.inflate(R.layout.fragment1, container, false);
        mUp = (Button) mRootView.findViewById(R.id.up);
        mUp.setOnClickListener(this);
        mDown = (Button) mRootView.findViewById(R.id.down);
        mDown.setOnClickListener(this);
        mLeft = (Button) mRootView.findViewById(R.id.left);
        mLeft.setOnClickListener(this);
        mRight = (Button) mRootView.findViewById(R.id.right);
        mRight.setOnClickListener(this);
        player = (TextView) mRootView.findViewById(R.id.playerid);
        player.setText("Player "+playerid);
        if(playerid == 1)
        {
            player.setTextColor(Color.RED);
        }
        else if(playerid == 2)
        {
            player.setTextColor(Color.GREEN);
        }
        else if(playerid == 3)
        {
            player.setTextColor(Color.BLUE);
        }
        movesLeft = (TextView) mRootView.findViewById(R.id.movesleft);
        movesLeft.setText("Moves Left: " + moves);

        board = (Board) getActivity().getSupportFragmentManager().findFragmentById(R.id.brd1);
        isFinishedTurn = false;
        allIsFinishedTurn = false;
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");

        try {
            mCallback = (Interface1) context;
            firstRound = mCallback.isFirstRound();
            if(firstRound) {
                firstMove = mCallback.firstMove(playerid);
                Log.i(TAG, "firstMove: " + firstMove);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement Interface1");
        }
    }

    public void DecrementMovesLeft(){
        moves--;
        movesLeft.setText("Moves Left: "+moves);
    }

    public void IncrementMovesLeft(){
        moves++;
        movesLeft.setText("Moves Left:"+moves);
    }

    public void changeText(String x){
        player.setText(x);
    }

    @Override
    public void onClick(View v)
    {
        if(v == mUp){
            DecrementMovesLeft();
            Log.i(TAG, "onClick: up");
        }
        else if(v == mDown){
            DecrementMovesLeft();
            Log.i(TAG, "onClick: down");
        }
        else if(v == mLeft){
            DecrementMovesLeft();
            Log.i(TAG, "onClick: left");
        }
        else if(v == mRight){
            DecrementMovesLeft();
            Log.i(TAG, "onClick: right");
        }
        if(moves == 0) {
            isFinishedTurn = true;
            mUp.setEnabled(false); mUp.setClickable(false);
            mDown.setEnabled(false); mDown.setClickable(false);
            mLeft.setEnabled(false); mLeft.setClickable(false);
            mRight.setEnabled(false); mRight.setClickable(false);
            mCallback.navigate1(playerid);
        }
    }

    public void todo_board(){

    }

    public interface Interface1{
        void navigate1(int i);
        void makeMove(int move, int playerid);
        boolean isFirstRound();
        int firstMove(int player);
    }
}
