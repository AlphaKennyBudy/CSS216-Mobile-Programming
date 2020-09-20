package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity {
    private TextView timer;
    private TextView repetitions_timer;

    private int repetitions_val;
    private int one_repetition_val;

    private boolean isGivenUp = false;

    public static final String EXTRA_RESULT = "kz.sdu.assignments.EXTRA_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Intent intent = getIntent();
        repetitions_val = intent.getIntExtra(MainActivity.EXTRA_REPETITIONS, 1);
        one_repetition_val = intent.getIntExtra(MainActivity.EXTRA_ONE_REPETITION, 1);

        timer = findViewById(R.id.timer);
        repetitions_timer = findViewById(R.id.repetitions_timer);

        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch ((int) ((4000 - millisUntilFinished) / 1000)) {
                    case 1:
                        timer.setText(R.string.timer_text1);
                        break;
                    case 2:
                        timer.setText(R.string.timer_text2);
                        break;
                    case 3:
                        timer.setText(R.string.timer_text3);
                        break;
                }
            }

            @Override
            public void onFinish() {
                new CountDownTimer(repetitions_val * one_repetition_val * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long secondsPassed = (repetitions_val * one_repetition_val * 1000 - millisUntilFinished) / 1000 + 1;
                        timer.setText(String.valueOf(secondsPassed));

                        if (secondsPassed % one_repetition_val == 0) {
                            repetitions_timer.setText(String.valueOf(secondsPassed / one_repetition_val));
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (!isGivenUp) sendResult(true);
                    }
                }.start();
            }
        }.start();

    }

    public void giveUp(View view) {
        isGivenUp = true;
        sendResult(false);
    }

    public void sendResult(boolean result) {
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra(EXTRA_RESULT, result);
        intent.putExtra(MainActivity.EXTRA_REPETITIONS, repetitions_val);
        intent.putExtra(MainActivity.EXTRA_ONE_REPETITION, one_repetition_val);
        startActivity(intent);
    }
}