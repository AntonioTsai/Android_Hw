package com.example.antonio.lab2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnRaw, btnSD, btnURL, btnPlay, btnPause, btnStop, btnVolUp, btnVolDown;
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
        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnVolUp = (Button) findViewById(R.id.btnVolUp);
        btnVolDown = (Button) findViewById(R.id.btnVolDown);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        player = new MediaPlayer();

        btnPlay.setEnabled(true);
        btnRaw.setEnabled(true);
        btnStop.setEnabled(true);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                btnPlay.setEnabled(true);
                btnRaw.setEnabled(true);
                btnStop.setEnabled(true);
            }
        });

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

        btnSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player = new MediaPlayer();
                    // player.setDataSource(Environment.getExternalStorageDirectory().toString() + "/04 - Eine Kleine.mp3");
                    player.setDataSource(Environment.getExternalStorageDirectory().toString() + "/01 to the beginning.mp3");
                    progressBar.setProgress(0);
                    player.prepare();
                    length = player.getDuration();
                    progressBar.setMax(length / 1000);
                    player.start();
                    new Thread(new ProcessBarRefresh()).start();
                    btnRaw.setEnabled(false);
                    btnSD.setEnabled(false);
                    btnStop.setEnabled(true);
                } catch (IllegalStateException | IllegalArgumentException | IOException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player.stop();
                    btnRaw.setEnabled(true);
                    btnSD.setEnabled(true);
                    btnURL.setEnabled(true);
                    btnStop.setEnabled(false);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });

        btnVolDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, 5);
            }
        });

        btnVolUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, 5);
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
