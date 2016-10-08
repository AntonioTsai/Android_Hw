package antonio.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private boolean zooming;
    private Intent intent;
    private TextView text;
    private int textColor;
    private String message;
    private Button edit;
    private Button zoomIn;
    private Button zoomOut;
    private SeekBar seekBar_R;
    private SeekBar seekBar_G;
    private SeekBar seekBar_B;
    private TextView textView_color_R;
    private TextView textView_color_G;
    private TextView textView_color_B;
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    text.setTextSize(TypedValue.COMPLEX_UNIT_PX, text.getTextSize() + 1);
                    break;
                case 0:
                    text.setTextSize(TypedValue.COMPLEX_UNIT_PX, text.getTextSize() - 1);
                    break;
            }
        }
    };
    private Runnable zoomInText = new Runnable() {
        @Override
        public void run() {
            while (zooming) {
                uiHandler.sendEmptyMessage(1);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Runnable zoomOutText = new Runnable() {
        @Override
        public void run() {
            while (zooming) {
                uiHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text = (TextView) findViewById(R.id.demo);
        edit = (Button) findViewById(R.id.back);
        zoomIn = (Button) findViewById(R.id.zoomIn);
        zoomOut = (Button) findViewById(R.id.zoomOut);
        seekBar_R = (SeekBar) findViewById(R.id.seekBar_R);
        seekBar_G = (SeekBar) findViewById(R.id.seekBar_G);
        seekBar_B = (SeekBar) findViewById(R.id.seekBar_B);
        textView_color_R = (TextView) findViewById(R.id.textView_color_R);
        textView_color_G = (TextView) findViewById(R.id.textView_color_G);
        textView_color_B = (TextView) findViewById(R.id.textView_color_B);


        text.setText(message);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zoomIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    zooming = true;
                    new Thread(zoomInText).start();
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    zooming = false;
                }
                return false;
            }
        });

        zoomOut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    zooming = true;
                    new Thread(zoomOutText).start();
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    zooming = false;
                }
                return false;
            }
        });

        seekBar_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_color_R.setText("R " + progress + "/" + seekBar.getMax());
                textColor = (seekBar_R.getProgress() << 16 & 0x00FF0000) +
                        (seekBar_G.getProgress() << 8 & 0x0000FF00) +
                        (seekBar_B.getProgress() & 0x000000FF) +
                        0xFF000000;
                text.setTextColor(textColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_color_G.setText("G " + progress + "/" + seekBar.getMax());
                textColor = (seekBar_R.getProgress() << 16 & 0x00FF0000) +
                        (seekBar_G.getProgress() << 8 & 0x0000FF00) +
                        (seekBar_B.getProgress() & 0x000000FF) +
                        0xFF000000;
                text.setTextColor(textColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_color_B.setText("B " + progress + "/" + seekBar.getMax());
                textColor = (seekBar_R.getProgress() << 16 & 0x00FF0000) +
                        (seekBar_G.getProgress() << 8 & 0x0000FF00) +
                        (seekBar_B.getProgress() & 0x000000FF) +
                        0xFF000000;
                text.setTextColor(textColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
