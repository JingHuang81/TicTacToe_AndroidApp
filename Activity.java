package com.example.jim.tictactoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;



public class Activity extends ActionBarActivity {


    //   public static String[] letters = {
    //           "A", "B", "C", "D", "E",
    //           "F", "G", "H", "I"/*, "J",
    //           "K", "L", "M", "N", "O",
    //          "P", "Q", "R", "S", "T",
    //           "U", "V", "W", "X", "Y", "Z"*/};

    public GridView gridview;
    public ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
        gridview = (GridView) findViewById(R.id.gridView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,letters);
        //gridview.setAdapter(adapter);
        adapter = new ImageAdapter(this);
        // System.out.println("setAdapter pass");
        gridview.setAdapter(adapter);
        // System.out.println("setAdapter pass2");
       // adapter.notifyDataSetChanged();
        OnItemClickListener listener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // TODO change use the method in imageadaptor to change the value of board
                ImageAdapter.updateImageAdapter(position);
                updateText();
                // update the adapter
                gridview.setAdapter(adapter);
                // make a toast if finished
                //System.out.println("before make a toast");
                makeToast();
                //System.out.println("a toast is made");
            }
        };
        gridview.setOnItemClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tic_tac_toe_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * the click event handler for button
     * reset the image adapter to initial condition
     * @param view
     */
    public void buttonOnClick(View view) {
        ImageAdapter.resetImageAdapter();
        makeToast();
        updateText();
        gridview.setAdapter(adapter);
    }

    public void makeToast() {
        Toast.makeText(getApplicationContext(), Logic.getGameStatus(), Toast.LENGTH_SHORT).show();

    }

    public void updateText() {
        TextView text = (TextView) findViewById(R.id.textView3);
        text.setText(Logic.getGameStatus());
    }

}
