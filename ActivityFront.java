package com.example.jim.tictactoe;

import android.app.*;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jim on 2014/11/5.
 */
public class ActivityFront extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);
    }
    public void buttonStartOnClick(View view) {
        setContentView(R.layout.activity_tic_tac_toe_game);
    }
}
