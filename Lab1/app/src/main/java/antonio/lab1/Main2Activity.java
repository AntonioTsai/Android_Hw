package antonio.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView text;
    private int textColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text = (TextView) findViewById(R.id.demo);
        Button edit = (Button) findViewById(R.id.back);
        final SeekBar seekBar_R = (SeekBar) findViewById(R.id.seekBar_R);
        final SeekBar seekBar_G = (SeekBar) findViewById(R.id.seekBar_G);
        final SeekBar seekBar_B = (SeekBar) findViewById(R.id.seekBar_B);
        final TextView textView_color_R = (TextView) findViewById(R.id.textView_color_R);
        final TextView textView_color_G = (TextView) findViewById(R.id.textView_color_G);
        final TextView textView_color_B = (TextView) findViewById(R.id.textView_color_B);



        text.setText(message);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    public void zoomInText(View view) {
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, text.getTextSize() + 1);
    }

    public void zoomOutText(View view) {
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, text.getTextSize() - 1);
    }
}
