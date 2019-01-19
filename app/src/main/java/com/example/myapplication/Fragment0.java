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

public class Fragment0 extends Fragment implements View.OnClickListener, Board.InterfaceB {
    final static String TAG = "fragment0";
    View mRootView;
    View mBoardView;
    TextView mLabel;
    Button mNext;
    Interface0 mCallback;
    ArrayList<Integer> player1moves, player2moves, player3moves;
    Bitmap green, red, blue, empty, black;
    ImageView[] squares;

    public Fragment0() {
    }

    public static com.example.myapplication.Fragment0 newInstance() {
        com.example.myapplication.Fragment0 fragment = new com.example.myapplication.Fragment0();
        Log.i(fragment.TAG, "onCreateView Fragment0");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(TAG, "reached 0:");
        mRootView = inflater.inflate(R.layout.fragment0, container, false);
        mBoardView = mRootView.findViewById(R.id.brd);
        mLabel = (TextView) mRootView.findViewById(R.id.textView);
        mNext = (Button) mRootView.findViewById(R.id.next);
        mNext.setOnClickListener(this);
        mNext.setEnabled(false);
        mNext.setClickable(false);
        player1moves = getArguments().getIntegerArrayList("firstmove1");
        player2moves = getArguments().getIntegerArrayList("firstmove2");
        player3moves = getArguments().getIntegerArrayList("firstmove3");

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

        updateBoard(player1moves, player2moves, player3moves);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");

        try {
            mCallback = (Interface0) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement Interface0");
        }
    }

    public void updateBoard(ArrayList<Integer> p1, ArrayList<Integer> p2, ArrayList<Integer> p3)
    {
    for(int i = 0; i < p1.size(); i++)
    {
        squares[p1.get(i)].setImageBitmap(red);
        squares[p2.get(i)].setImageBitmap(green);
        squares[p3.get(i)].setImageBitmap(blue);
    }
    }

    public void changeText(String x){
        mLabel.setText(x);
    }

    public void todo_board()
    {

    }

    @Override
    public void onClick(View v)
    {
        Log.i(TAG, "Clicked0");
    }

    public interface Interface0{
        void navigate();
    }
}
