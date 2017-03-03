package com.jam.vlc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.goPlay).setOnClickListener(this);
        int num = 10;
        if (num > 0) {
            ShortcutBadger.applyCount(this, num); //for 1.1.4+
        } else {
            ShortcutBadger.removeCount(this); //for 1.1.4+
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goPlay) {
            Intent intent = new Intent(this, VideoPlayActivity.class);
            startActivity(intent);
        }
    }
}
