package com.example.antonio.lab2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    Button btnRaw, btnSD, btnURL, btnPlay, btnStop, btnVolUp, btnVolDown;
    MediaPlayer player;
    ProgressBar progressBar;
    SeekBar seekBar;
    int progressState = 0, length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AudioManager audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        btnRaw = (Button) findViewById(R.id.btnPlayRaw);
        btnSD = (Button) findViewById(R.id.btnPlaySD);
        btnURL = (Button) findViewById(R.id.btnPlayURL);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnVolUp = (Button) findViewById(R.id.btnVolUp);
        btnVolDown = (Button) findViewById(R.id.btnVolDown);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        player = new MediaPlayer();

        btnPlay.setEnabled(true);
        btnRaw.setEnabled(true);
        btnStop.setEnabled(true);

        btnRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = MediaPlayer.create(MainActivity.this, R.raw.sample);
                try {
                    progressBar.setProgress(0);
                    length = player.getDuration();
                    progressBar.setMax(length / 1000);
                    player.start();
                    new Thread(new ProcessBarRefresh()).start();
                    btnRaw.setEnabled(false);
                    btnSD.setEnabled(false);
                    btnStop.setEnabled(true);
                } catch (IllegalStateException | IllegalArgumentException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    class ProcessBarRefresh implements Runnable {
        @Override
        public void run() {
            while (player.isPlaying()) {
                progressState = player.getCurrentPosition() / 1000;
                progressBar.setProgress(progressState);
            }
        }
    }
}
