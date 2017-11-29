package com.example.a2019kle.grit;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private TextView mTitleView;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //google-api-services-youtube-v3-rev124-1.19.0.jar is missing
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(getString(R.string.API_key), this);
        mTitleView = (TextView)findViewById(R.id.titleView);
        mTitleView.setText(R.string.title);


    }
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, R.string.errormessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(getString(R.string.videoKey));
        }
    }

    @Override
    protected void onActivityResult(int reqcode, int result, Intent data){
        if (reqcode == RECOVERY_DIALOG_REQUEST){
             youTubePlayerView.initialize(getString(R.string.API_key), this);
        }
    }
}

//https://code.tutsplus.com/tutorials/create-a-youtube-client-on-android--cms-22858