package com.example.antonio.lab3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

//import android.media.session.MediaController;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    VideoView videoView;
    MediaController mediaController;
    int REQUEST_CODE = 1;
    String TAG = "MainActivity";
    Intent FileSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set screen not to rorate and display in straight
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(this);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        FileSelect = new Intent(Intent.ACTION_GET_CONTENT);
        FileSelect.setType("video/*");
        this.startActivityForResult(FileSelect, REQUEST_CODE);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                FileSelect = new Intent(Intent.ACTION_GET_CONTENT);
                FileSelect.setType("video/*");
                startActivityForResult(FileSelect, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            Log.e(TAG, "Read File Error!");
            return;
        }
        if (requestCode != REQUEST_CODE) {
            Log.d(TAG, "Not Read File!");
            return;
        }
        Uri uri = data.getData();
        Log.d(TAG, "Get File Path" + uri.getPath());
        Log.d(TAG, "Transfer File Path to VideoView");
        videoView.setVideoURI(uri);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                // do something
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                // do something
                break;
            case KeyEvent.KEYCODE_BACK:
                videoView.suspend();
                FileSelect = new Intent(Intent.ACTION_GET_CONTENT);
                FileSelect.setType("video/*");
                this.startActivityForResult(FileSelect, REQUEST_CODE);
                break;
            case KeyEvent.KEYCODE_MENU:
                // do something
                break;

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.start();
    }
}
