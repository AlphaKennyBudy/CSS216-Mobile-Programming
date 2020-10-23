package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new DashboardFragment(this));

        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);

        navbar.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
                switch (item.getItemId()) {
                  case R.id.dashboard_page:
                      fragment = new DashboardFragment(this);
                      break;
                  case R.id.search_page:
                      fragment = new SearchFragment(this);
                      break;
                  case R.id.create_page:
                      fragment = new CreateFragment(this);
                      break;
                  }
        return loadFragment(fragment);
    }
}
