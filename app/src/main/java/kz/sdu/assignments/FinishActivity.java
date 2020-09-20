package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        TextView result_text = findViewById(R.id.result_text);

        Intent intent = getIntent();
        boolean result = intent.getBooleanExtra(ActionActivity.EXTRA_RESULT, true);
        int repetitions_val = intent.getIntExtra(MainActivity.EXTRA_REPETITIONS, 1);
        int one_repetition_val = intent.getIntExtra(MainActivity.EXTRA_ONE_REPETITION,1);
        if (result) {
            result_text.setText(String.format(getString(R.string.congrats_message), repetitions_val, repetitions_val * one_repetition_val));
        } else {
            result_text.setText(R.string.lost_message);
        }
    }

    public void startAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}