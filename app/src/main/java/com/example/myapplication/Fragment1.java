package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements View.OnClickListener, Board.InterfaceB{
    final static String TAG = "frag1";
    Interface1 mCallback;
    View mRootView;
    View mBoardView;
    Button mUp;
    Button mDown;
    Button mLeft;
    Button mRight;
    TextView player;
    TextView movesLeft;
    int moves, playerid, firstMove, max, previousMove;
    ArrayList<Integer> moveList, other1, other2, placeholder;
    boolean isFinishedTurn;
    boolean allIsFinishedTurn;
    Bitmap green, red, blue, black, empty;
    ImageView[] squares;

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
        other1 = new ArrayList<Integer>();
        other2 = new ArrayList<Integer>();
        Log.i(TAG, "reached 1:");
        mRootView = inflater.inflate(R.layout.fragment1, container, false);
        mBoardView = mRootView.findViewById(R.id.brd1);
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

        movesLeft = (TextView) mRootView.findViewById(R.id.movesleft);
        movesLeft.setText("Moves Left: " + moves);

        Resources res = getActivity().getResources();
        green = BitmapFactory.decodeResource(res, R.drawable.blacksquarewgreendot);
        blue = BitmapFactory.decodeResource(res, R.drawable.blacksquarewbluedot1);
        red = BitmapFactory.decodeResource(res, R.drawable.blacksquarewreddot);
        black = BitmapFactory.decodeResource(res, R.drawable.blacksquarewblackdot1);
        empty = BitmapFactory.decodeResource(res, R.drawable.blacksquare1);

        squares = new ImageView[100];
        squares[0] = mBoardView.findViewById(R.id.a0); squares[1] = mBoardView.findViewById(R.id.a1); squares[2] = mBoardView.findViewById(R.id.a2); squares[3] = mBoardView.findViewById(R.id.a3); squares[4] = mBoardView.findViewById(R.id.a4); squares[5] = mBoardView.findViewById(R.id.a5); squares[6] = mBoardView.findViewById(R.id.a6); squares[7] = mBoardView.findViewById(R.id.a7); squares[8] = mBoardView.findViewById(R.id.a8); squares[9] = mBoardView.findViewById(R.id.a9);
        squares[10] = mBoardView.findViewById(R.id.a10); squares[11] = mBoardView.findViewById(R.id.a11); squares[12] = mBoardView.findViewById(R.id.a12); squares[13] = mBoardView.findViewById(R.id.a13); squares[14] = mBoardView.findViewById(R.id.a14); squares[15] = mBoardView.findViewById(R.id.a15); squares[16] = mBoardView.findViewById(R.id.a16); squares[17] = mBoardView.findViewById(R.id.a17); squares[18] = mBoardView.findViewById(R.id.a18); squares[19] = mBoardView.findViewById(R.id.a19);
        squares[20] = mBoardView.findViewById(R.id.a20); squares[21] = mBoardView.findViewById(R.id.a21); squares[22] = mBoardView.findViewById(R.id.a22); squares[23] = mBoardView.findViewById(R.id.a23); squares[24] = mBoardView.findViewById(R.id.a24); squares[25] = mBoardView.findViewById(R.id.a25); squares[26] = mBoardView.findViewById(R.id.a26); squares[27] = mBoardView.findViewById(R.id.a27); squares[28] = mBoardView.findViewById(R.id.a28); squares[29] = mBoardView.findViewById(R.id.a29);
        squares[30] = mBoardView.findViewById(R.id.a30); squares[31] = mBoardView.findViewById(R.id.a31); squares[32] = mBoardView.findViewById(R.id.a32); squares[33] = mBoardView.findViewById(R.id.a33); squares[34] = mBoardView.findViewById(R.id.a34); squares[35] = mBoardView.findViewById(R.id.a35); squares[36] = mBoardView.findViewById(R.id.a36); squares[37] = mBoardView.findViewById(R.id.a37); squares[38] = mBoardView.findViewById(R.id.a38); squares[39] = mBoardView.findViewById(R.id.a39);
        squares[40] = mBoardView.findViewById(R.id.a40); squares[41] = mBoardView.findViewById(R.id.a41); squares[42] = mBoardView.findViewById(R.id.a42); squares[43] = mBoardView.findViewById(R.id.a43); squares[44] = mBoardView.findViewById(R.id.a44); squares[45] = mBoardView.findViewById(R.id.a45); squares[46] = mBoardView.findViewById(R.id.a46); squares[47] = mBoardView.findViewById(R.id.a47); squares[48] = mBoardView.findViewById(R.id.a48); squares[49] = mBoardView.findViewById(R.id.a49);
        squares[50] = mBoardView.findViewById(R.id.a50); squares[51] = mBoardView.findViewById(R.id.a51); squares[52] = mBoardView.findViewById(R.id.a52); squares[53] = mBoardView.findViewById(R.id.a53); squares[54] = mBoardView.findViewById(R.id.a54); squares[55] = mBoardView.findViewById(R.id.a55); squares[56] = mBoardView.findViewById(R.id.a56); squares[57] = mBoardView.findViewById(R.id.a57); squares[58] = mBoardView.findViewById(R.id.a58); squares[59] = mBoardView.findViewById(R.id.a59);
        squares[60] = mBoardView.findViewById(R.id.a60); squares[61] = mBoardView.findViewById(R.id.a61); squares[62] = mBoardView.findViewById(R.id.a62); squares[63] = mBoardView.findViewById(R.id.a63); squares[64] = mBoardView.findViewById(R.id.a64); squares[65] = mBoardView.findViewById(R.id.a65); squares[66] = mBoardView.findViewById(R.id.a66); squares[67] = mBoardView.findViewById(R.id.a67); squares[68] = mBoardView.findViewById(R.id.a68); squares[69] = mBoardView.findViewById(R.id.a69);
        squares[70] = mBoardView.findViewById(R.id.a70); squares[71] = mBoardView.findViewById(R.id.a71); squares[72] = mBoardView.findViewById(R.id.a72); squares[73] = mBoardView.findViewById(R.id.a73); squares[74] = mBoardView.findViewById(R.id.a74); squares[75] = mBoardView.findViewById(R.id.a75); squares[76] = mBoardView.findViewById(R.id.a76); squares[77] = mBoardView.findViewById(R.id.a77); squares[78] = mBoardView.findViewById(R.id.a78); squares[79] = mBoardView.findViewById(R.id.a79);
        squares[80] = mBoardView.findViewById(R.id.a80); squares[81] = mBoardView.findViewById(R.id.a81); squares[82] = mBoardView.findViewById(R.id.a82); squares[83] = mBoardView.findViewById(R.id.a83); squares[84] = mBoardView.findViewById(R.id.a84); squares[85] = mBoardView.findViewById(R.id.a85); squares[86] = mBoardView.findViewById(R.id.a86); squares[87] = mBoardView.findViewById(R.id.a87); squares[88] = mBoardView.findViewById(R.id.a88); squares[89] = mBoardView.findViewById(R.id.a89);
        squares[90] = mBoardView.findViewById(R.id.a90); squares[91] = mBoardView.findViewById(R.id.a91); squares[92] = mBoardView.findViewById(R.id.a92); squares[93] = mBoardView.findViewById(R.id.a93); squares[94] = mBoardView.findViewById(R.id.a94); squares[95] = mBoardView.findViewById(R.id.a95); squares[96] = mRootView.findViewById(R.id.a96); squares[97] = mRootView.findViewById(R.id.a97); squares[98] = mRootView.findViewById(R.id.a98); squares[99] = mRootView.findViewById(R.id.a99);
        DisablePreviousMove();
        isFinishedTurn = false;
        allIsFinishedTurn = false;
        placeholder = new ArrayList<Integer>();
        if(playerid == 1)
        {
            Log.i(TAG, "getting the info from mainactivity: player1");
            player.setTextColor(Color.RED);
            moveList = getArguments().getIntegerArrayList("storage1");
            if(moveList != null)
            {
                for(int i = 0; i < max; i++) {
                    placeholder.add(0, moveList.get(i));
                }
            }
            else {
                ArrayList<Integer> temp = getArguments().getIntegerArrayList("firstmoves1");
                moveList = new ArrayList<Integer>();
                for (int i = 0; i < temp.size(); i++)
                {
                    moveList.add(i, temp.get(i));
                    placeholder.add(i, temp.get(i));
                }
            }
            other1 = getArguments().getIntegerArrayList("firstmoves2");
            other2 = getArguments().getIntegerArrayList("firstmoves3");
            setBoard(placeholder, other1, other2);
        }
        else if(playerid == 2)
        {
            Log.i(TAG, "getting the info from mainactivity: player2");
            player.setTextColor(Color.GREEN);
            other1 = getArguments().getIntegerArrayList("firstmoves1");
            moveList = getArguments().getIntegerArrayList("storage2");
            if(moveList != null)
            {
                for(int i = 0; i < max; i++) {
                    placeholder.add(0, moveList.get(i));
                }
            }
            else {
                ArrayList<Integer> temp = getArguments().getIntegerArrayList("firstmoves2");
                moveList = new ArrayList<Integer>();
                for (int i = 0; i < temp.size(); i++)
                {
                    moveList.add(i, temp.get(i));
                    placeholder.add(i, temp.get(i));
                }
            }
            other2 = getArguments().getIntegerArrayList("firstmoves3");
            setBoard(other1, placeholder, other2);
        }
        else if(playerid == 3)
        {
            Log.i(TAG, "getting the info from mainactivity: player3");
            player.setTextColor(Color.BLUE);
            other1 = getArguments().getIntegerArrayList("firstmoves1");
            other2 = getArguments().getIntegerArrayList("firstmoves2");
            moveList = getArguments().getIntegerArrayList("storage3");
            if(moveList != null)
            {
                for(int i = 0; i < max; i++) {
                    placeholder.add(0, moveList.get(i));
                }
            }
            else {
                ArrayList<Integer> temp = getArguments().getIntegerArrayList("firstmoves3");
                moveList = new ArrayList<Integer>();
                for (int i = 0; i < temp.size(); i++)
                {
                    moveList.add(i, temp.get(i));
                    placeholder.add(i, temp.get(i));
                }
            }
                setBoard(other1, other2, placeholder);
        }

        firstMove = moveList.get(0);
        if(firstMove < 10)
        {
            mLeft.setEnabled(false);
            mLeft.setClickable(false);
        }
        if(firstMove%10 ==9)
        {
            mDown.setEnabled(false);
            mDown.setClickable(false);
        }
        if(firstMove > 89)
        {
            mRight.setEnabled(false);
            mRight.setClickable(false);
        }
        if(firstMove%10 == 0)
        {
            mUp.setEnabled(false);
            mUp.setClickable(false);
        }

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");

        try {
            mCallback = (Interface1) context;
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

    public void updateBoard(ArrayList<Integer> p)
    {
        int i = 0;
        Log.i(TAG, "updateBoard");
        if(playerid == 1) {
            while(i < p.size()){
                squares[p.get(i)].setImageBitmap(red);
                if(i == max) {
                    squares[p.get(i)].setImageBitmap(empty);
                    break;
                }
                i++;
            }
            mCallback.storeIt(1, p);
        }
        else if (playerid == 2) {
            while(i < p.size()){
                squares[p.get(i)].setImageBitmap(green);
                if(i == max) {
                    squares[p.get(i)].setImageBitmap(empty);
                    break;
                }
                i++;
            }
            mCallback.storeIt(2, p);
        }
        else
        {
            while(i < p.size()){
                squares[p.get(i)].setImageBitmap(blue);
                if(i == max) {
                    squares[p.get(i)].setImageBitmap(empty);
                    break;
                }
                i++;
            }
            mCallback.storeIt(3, p);
        }
        firstMove = moveList.get(0);
        if(firstMove < 10 || previousMove == 4)
        {
            mLeft.setEnabled(false);
            mLeft.setClickable(false);
        }
        else
        {
            mLeft.setEnabled(true);
            mLeft.setClickable(true);
        }
        if(firstMove%10 ==9 || previousMove == 1)
        {
            mDown.setEnabled(false);
            mDown.setClickable(false);
        }
        else
        {
            mDown.setEnabled(true);
            mDown.setClickable(true);
        }
        if(firstMove > 89 || previousMove == 3)
        {
            mRight.setEnabled(false);
            mRight.setClickable(false);
        }
        else
        {
            mRight.setEnabled(true);
            mRight.setClickable(true);
        }
        if(firstMove%10 == 0 || previousMove == 2)
        {
            mUp.setEnabled(false);
            mUp.setClickable(false);
        }
        else
        {
            mUp.setEnabled(true);
            mUp.setClickable(true);
        }
    }

    public void DisablePreviousMove()
    {
        if(previousMove == 0)
            return;
        else
            if(previousMove == 2) {
                mUp.setEnabled(false);
                mUp.setClickable(false);
            }
            else if(previousMove == 1) {
                mDown.setEnabled(false);
                mDown.setClickable(false);
            }
            else if(previousMove == 3) {
                mRight.setEnabled(false);
                mRight.setClickable(false);
            }
            else if(previousMove == 4) {
                mLeft.setEnabled(false);
                mLeft.setClickable(false);
            }
    }

    public void setBoard(ArrayList<Integer> p1, ArrayList<Integer> p2, ArrayList<Integer> p3)
    {
        for(int i = 0; i < p1.size(); i++) {
            squares[p1.get(i)].setImageBitmap(red);
        }
        for(int i = 0; i < p2.size(); i++) {
            squares[p2.get(i)].setImageBitmap(green);
        }
        for(int i = 0; i < p3.size(); i++) {
            squares[p3.get(i)].setImageBitmap(blue);
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v == mUp){
            DecrementMovesLeft();
            moveList.add(0, moveList.get(0)-1);
            previousMove = 1;
            Log.i(TAG, "onClick: up");
        }
        else if(v == mDown){
            DecrementMovesLeft();
            moveList.add(0, moveList.get(0)+1);
            previousMove = 2;
            Log.i(TAG, "onClick: down");
        }
        else if(v == mLeft){
            DecrementMovesLeft();
            moveList.add(0, moveList.get(0)-10);
            previousMove = 3;
            Log.i(TAG, "onClick: left");
        }
        else if(v == mRight){
            DecrementMovesLeft();
            moveList.add(0, moveList.get(0)+10);
            previousMove = 4;
            Log.i(TAG, "onClick: right");
        }
        updateBoard(moveList);
        if(moves == 0) {
            isFinishedTurn = true;
            mUp.setEnabled(false); mUp.setClickable(false);
            mDown.setEnabled(false); mDown.setClickable(false);
            mLeft.setEnabled(false); mLeft.setClickable(false);
            mRight.setEnabled(false); mRight.setClickable(false);
            mCallback.navigate1(playerid, moveList);
        }
    }

    public void todo_board(){

    }

    public interface Interface1{
        void navigate1(int i, ArrayList<Integer> moves);
        void storeIt(int playerid, ArrayList<Integer> x);
    }
}
