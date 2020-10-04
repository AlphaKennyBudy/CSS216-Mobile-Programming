package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateActivity extends AppCompatActivity {
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        NumberPicker participants = findViewById(R.id.participants_input);
        participants.setMaxValue(1000);
        participants.setMinValue(1);

        final Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        final Intent searchIntent = new Intent(this, SearchActivity.class);

        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboard_page:
                        startActivity(dashboardIntent);
                        break;
                    case R.id.search_page:
                        startActivity(searchIntent);
                        break;
                    case R.id.create_page:
                        break;
                }
                return true;
            }
        });

        apiRequest = new ApiRequest(this);
    }

    public void submit(View view) {
        RadioGroup agreement = findViewById(R.id.agreement);
        if (agreement.getCheckedRadioButtonId() == R.id.yes) {
            String activity = ((EditText) findViewById(R.id.activity_input)).getText().toString();
            if (activity.isEmpty())
                Toast.makeText(this, "You didn't provide an activity!", Toast.LENGTH_SHORT).show();
            else {
                String type = ((Spinner)findViewById(R.id.type_input)).getSelectedItem().toString();
                int participants = ((NumberPicker)findViewById(R.id.participants_input)).getValue();

                apiRequest.postActivity(activity,type,participants);
            }

        } else {
            Toast.makeText(this, "You didn't agree with our terms of usage", Toast.LENGTH_SHORT).show();
        }


    }
}