package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isTextVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toggleText(View view) {
        TextView InfoText = (TextView) findViewById(R.id.info);
        if (!isTextVisible) {
            Resources resourses = getResources();
            String text = resourses.getString((R.string.name)) + "\n" + resourses.getString(R.string.surname) + "\n" + resourses.getString(R.string.ID);
            InfoText.setText(text);
        } else {
            InfoText.setText("");
        }
        isTextVisible = !isTextVisible;
    }
}
