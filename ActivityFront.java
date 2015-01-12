package com.example.jim.tictactoe;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
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
        Intent intent = new Intent(this, TicTacToeGameActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
