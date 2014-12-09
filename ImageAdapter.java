package com.example.jim.tictactoe;

/**
 * Created by Jim on 2014/10/31.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.Hashtable;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imageBoard.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        // System.out.println("getView pass 1");
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(imageBoard[position]);
    return imageView;
}

    // Keep all Images in array
    public static Integer[] imageBoard = {
            R.drawable.empty, R.drawable.empty,R.drawable.empty,
            R.drawable.empty,R.drawable.empty, R.drawable.empty,
            R.drawable.empty, R.drawable.empty, R.drawable.empty
    };

    /**
     * update the imageBoard by changing the image at given position
     * @para position: the index of the image in the image array that has been changed
     */
    public static void updateImageAdapter (int position) {
        // THIS IS THE ORIGINAL CODE WORKS FOR SINGLE PLAYER
        // imageBoard[position] = Logic.updateBoard(position);

        // below are codes for games with  machine player
        // first pass the change made by human player into the Logic class
        Hashtable<Integer,Integer> convertIntToImage = new Hashtable<Integer, Integer>();
        convertIntToImage.put(Logic.PLAYER_O,R.drawable.player_o);
        convertIntToImage.put(Logic.PLAYER_X,R.drawable.player_x);
        convertIntToImage.put(Logic.EMPTY,R.drawable.empty);
        // update the imageBoard according to the intBoard in the Logic class
        Logic.updateBoard(position);
        int[] gameStatus = Logic.getIntBoard();
        for (int index = 0; index < imageBoard.length; index++) {
             int status = gameStatus[index];
             imageBoard[index] = convertIntToImage.get(status);
        }
    }

    public static void resetImageAdapter() {
        for (int index = 0; index < 3 * 3; index++) {
            imageBoard[index] = R.drawable.empty;
        }
        Logic.resetIntBoard();
    }
}
