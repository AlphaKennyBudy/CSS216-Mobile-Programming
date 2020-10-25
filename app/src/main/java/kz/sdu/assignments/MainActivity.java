package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ApiRequest apiRequest;
    private Fragment searchFragment;
    private Fragment dashboardFragment;
    private Fragment createFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = new SearchFragment(this);
        dashboardFragment = new DashboardFragment(this);
        createFragment = new CreateFragment();

        loadFragment(dashboardFragment);

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
                fragment = dashboardFragment;
                break;
            case R.id.search_page:
                fragment = searchFragment;
                break;
            case R.id.create_page:
                fragment = createFragment;
                break;
        }
        return loadFragment(fragment);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        getSupportFragmentManager().putFragment(outState, "myfragment", searchFragment);
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle inState) {
//        Toast.makeText(this, "Loading fragment", Toast.LENGTH_SHORT).show();
//        searchFragment = getSupportFragmentManager().getFragment(inState, "myfragment");
//    }

}
