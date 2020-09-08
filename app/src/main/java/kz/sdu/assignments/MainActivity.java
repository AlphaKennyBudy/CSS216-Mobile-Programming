package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private boolean isTextVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.product_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView ProductText = (TextView) findViewById(R.id.best_product);
        ProductText.setText(String.valueOf(position + 1));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        TextView ProductText = (TextView) findViewById(R.id.best_product);
        ProductText.setText("");
    }
}
