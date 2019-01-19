package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
class Point {
    private int myX;
    private int myY;

    Point(int myX, int myY) {
        this.myX = myX;
        this.myY = myY;
    }

    public int getMyX() {
        return myX;
    }

    public void setMyX(int myX) {
        this.myX = myX;
    }

    public int getMyY() {
        return myY;
    }

    public void setMyY(int myY) {
        this.myY = myY;
    }
}

public class Board extends Fragment {
    final static int UP = 1;
    final static int RIGHT = 2;
    final static int DOWN = 3;
    final static int LEFT = 4;
    final static int dimension = 10;
    final static String TAG = "board";
    View mRootView;
    InterfaceB mCallback;
    ArrayList<Point> player1;
    int player1max = 3;
    ArrayList<Point> player2;
    int player2max = 3;
    ArrayList<Point> player3;
    int player3max = 3;
    Bitmap green, red, blue, empty, black;
    ImageView[] squares;

    public Board() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG, "reached 0:");
        mRootView = inflater.inflate(R.layout.board, container, false);
        player1 = new ArrayList<Point>();
        player2 = new ArrayList<Point>();
        player3 = new ArrayList<Point>();
        Resources res = getActivity().getResources();
        green = BitmapFactory.decodeResource(res, R.drawable.blacksquarewgreendot);
        blue = BitmapFactory.decodeResource(res, R.drawable.blacksquarewbluedot1);
        red = BitmapFactory.decodeResource(res, R.drawable.blacksquarewreddot);
        black = BitmapFactory.decodeResource(res, R.drawable.blacksquarewblackdot1);
        empty = BitmapFactory.decodeResource(res, R.drawable.blacksquare1);

        squares = new ImageView[100];
        squares[0] = mRootView.findViewById(R.id.a0); squares[1] = mRootView.findViewById(R.id.a1); squares[2] = mRootView.findViewById(R.id.a2); squares[3] = mRootView.findViewById(R.id.a3); squares[4] = mRootView.findViewById(R.id.a4); squares[5] = mRootView.findViewById(R.id.a5); squares[6] = mRootView.findViewById(R.id.a6); squares[7] = mRootView.findViewById(R.id.a7); squares[8] = mRootView.findViewById(R.id.a8); squares[9] = mRootView.findViewById(R.id.a9);
        squares[10] = mRootView.findViewById(R.id.a10); squares[11] = mRootView.findViewById(R.id.a11); squares[12] = mRootView.findViewById(R.id.a12); squares[13] = mRootView.findViewById(R.id.a13); squares[14] = mRootView.findViewById(R.id.a14); squares[15] = mRootView.findViewById(R.id.a15); squares[16] = mRootView.findViewById(R.id.a16); squares[17] = mRootView.findViewById(R.id.a17); squares[18] = mRootView.findViewById(R.id.a18); squares[19] = mRootView.findViewById(R.id.a19);
        squares[20] = mRootView.findViewById(R.id.a20); squares[21] = mRootView.findViewById(R.id.a21); squares[22] = mRootView.findViewById(R.id.a22); squares[23] = mRootView.findViewById(R.id.a23); squares[24] = mRootView.findViewById(R.id.a24); squares[25] = mRootView.findViewById(R.id.a25); squares[26] = mRootView.findViewById(R.id.a26); squares[27] = mRootView.findViewById(R.id.a27); squares[28] = mRootView.findViewById(R.id.a28); squares[29] = mRootView.findViewById(R.id.a29);
        squares[30] = mRootView.findViewById(R.id.a30); squares[31] = mRootView.findViewById(R.id.a31); squares[32] = mRootView.findViewById(R.id.a32); squares[33] = mRootView.findViewById(R.id.a33); squares[34] = mRootView.findViewById(R.id.a34); squares[35] = mRootView.findViewById(R.id.a35); squares[36] = mRootView.findViewById(R.id.a36); squares[37] = mRootView.findViewById(R.id.a37); squares[38] = mRootView.findViewById(R.id.a38); squares[39] = mRootView.findViewById(R.id.a39);
        squares[40] = mRootView.findViewById(R.id.a40); squares[41] = mRootView.findViewById(R.id.a41); squares[42] = mRootView.findViewById(R.id.a42); squares[43] = mRootView.findViewById(R.id.a43); squares[44] = mRootView.findViewById(R.id.a44); squares[45] = mRootView.findViewById(R.id.a45); squares[46] = mRootView.findViewById(R.id.a46); squares[47] = mRootView.findViewById(R.id.a47); squares[48] = mRootView.findViewById(R.id.a48); squares[49] = mRootView.findViewById(R.id.a49);
        squares[50] = mRootView.findViewById(R.id.a50); squares[51] = mRootView.findViewById(R.id.a51); squares[52] = mRootView.findViewById(R.id.a52); squares[53] = mRootView.findViewById(R.id.a53); squares[54] = mRootView.findViewById(R.id.a54); squares[55] = mRootView.findViewById(R.id.a55); squares[56] = mRootView.findViewById(R.id.a56); squares[57] = mRootView.findViewById(R.id.a57); squares[58] = mRootView.findViewById(R.id.a58); squares[59] = mRootView.findViewById(R.id.a59);
        squares[60] = mRootView.findViewById(R.id.a60); squares[61] = mRootView.findViewById(R.id.a61); squares[62] = mRootView.findViewById(R.id.a62); squares[63] = mRootView.findViewById(R.id.a63); squares[64] = mRootView.findViewById(R.id.a64); squares[65] = mRootView.findViewById(R.id.a65); squares[66] = mRootView.findViewById(R.id.a66); squares[67] = mRootView.findViewById(R.id.a67); squares[68] = mRootView.findViewById(R.id.a68); squares[69] = mRootView.findViewById(R.id.a69);
        squares[70] = mRootView.findViewById(R.id.a70); squares[71] = mRootView.findViewById(R.id.a71); squares[72] = mRootView.findViewById(R.id.a72); squares[73] = mRootView.findViewById(R.id.a73); squares[74] = mRootView.findViewById(R.id.a74); squares[75] = mRootView.findViewById(R.id.a75); squares[76] = mRootView.findViewById(R.id.a76); squares[77] = mRootView.findViewById(R.id.a77); squares[78] = mRootView.findViewById(R.id.a78); squares[79] = mRootView.findViewById(R.id.a79);
        squares[80] = mRootView.findViewById(R.id.a80); squares[81] = mRootView.findViewById(R.id.a81); squares[82] = mRootView.findViewById(R.id.a82); squares[83] = mRootView.findViewById(R.id.a83); squares[84] = mRootView.findViewById(R.id.a84); squares[85] = mRootView.findViewById(R.id.a85); squares[86] = mRootView.findViewById(R.id.a86); squares[87] = mRootView.findViewById(R.id.a87); squares[88] = mRootView.findViewById(R.id.a88); squares[89] = mRootView.findViewById(R.id.a89);
        squares[90] = mRootView.findViewById(R.id.a90); squares[91] = mRootView.findViewById(R.id.a91); squares[92] = mRootView.findViewById(R.id.a92); squares[93] = mRootView.findViewById(R.id.a93); squares[94] = mRootView.findViewById(R.id.a94); squares[95] = mRootView.findViewById(R.id.a95); squares[96] = mRootView.findViewById(R.id.a96); squares[97] = mRootView.findViewById(R.id.a97); squares[98] = mRootView.findViewById(R.id.a98); squares[99] = mRootView.findViewById(R.id.a99);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");

        try {
            mCallback = (InterfaceB) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement Interface0");
        }
    }

    public void changeColorSquare(int x, int y)
    {
        int number = x;
        if(y==0)
            squares[number].setImageBitmap(empty);
        else if(y==1)
            squares[number].setImageBitmap(blue);
        else if(y==2)
            squares[number].setImageBitmap(red);
        else if(y==3)
            squares[number].setImageBitmap(green);
        else
            squares[number].setImageBitmap(black);
    }

    void makeMove(int player, Point move){
        if(player == 1)
        {
            player1.add(0, move);
            if(player1.size() == player1max)
            {
                player1.remove(player1max-1);
            }
        }
        else if(player == 2)
        {
            player2.add(0, move);
            if(player2.size() == player2max)
            {
                player2.remove(player2max - 1);
            }
        }
        else if(player == 3)
        {
            player3.add(0, move);
            if(player3.size() == player3max)
            {
                player3.remove(player3max - 1);
            }
        }
    }

    int updateBoard(){
        return 0;
    }

    public interface InterfaceB{
        public void todo_board();
    }
}
