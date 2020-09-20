package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    private NumberPicker repetitions;
    private NumberPicker one_repetition;

    public static final String EXTRA_REPETITIONS = "kz.sdu.assignments.EXTRA_REPETITIONS";
    public static final String EXTRA_ONE_REPETITION = "kz.sdu.assignments.EXTRA_ONE_REPETITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repetitions = findViewById(R.id.repetitions_input);
        one_repetition = findViewById(R.id.one_repetition_input);

        repetitions.setMinValue(1);
        one_repetition.setMinValue(1);

        repetitions.setMaxValue(100);
        one_repetition.setMaxValue(100);
    }

    public void start(View view) {
        int repetitions_val = repetitions.getValue();
        int one_repetition_val = one_repetition.getValue();

        Intent intent = new Intent(this, ActionActivity.class);
        intent.putExtra(EXTRA_REPETITIONS, repetitions_val);
        intent.putExtra(EXTRA_ONE_REPETITION, one_repetition_val);
        startActivity(intent);
    }
}
