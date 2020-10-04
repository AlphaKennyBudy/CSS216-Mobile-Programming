package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Format;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;

public class SearchActivity extends AppCompatActivity {
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Intent dashboardIntent =  new Intent(this, DashboardActivity.class);
        final Intent createIntent = new Intent(this, CreateActivity.class);

        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                  case R.id.dashboard_page:
                      startActivity(dashboardIntent);
                      break;
                  case R.id.search_page:
                      break;
                  case R.id.create_page:
                      startActivity(createIntent);
                      break;
                  }
                return true;
            }
        });

        apiRequest = new ApiRequest(this);
    }

    public void search(View view) {
        Spinner spinner = findViewById(R.id.type);
        String query = spinner.getSelectedItem().toString();

        CodeView code = findViewById(R.id.code);
        code.setOptions(Options.Default.get(this).withTheme(ColorTheme.MONOKAI));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        code.setLayoutParams(params);

        CheckBox checkBox = findViewById(R.id.load_image);
        if (checkBox.isChecked()) {
            ImageView image = findViewById(R.id.res_image);
            apiRequest.getImage(image);
        }

        apiRequest.searchActivity(query, code);
    }
}