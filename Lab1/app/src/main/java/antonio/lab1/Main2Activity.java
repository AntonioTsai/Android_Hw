package antonio.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text = (TextView) findViewById(R.id.demo);
        Button edit = (Button) findViewById(R.id.back);

        text.setText(message);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
