package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apiRequest = new ApiRequest(this);

        final Intent searchIntent =  new Intent(this, SearchActivity.class);
        final Intent createIntent = new Intent(this, CreateActivity.class);

        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                  case R.id.dashboard_page:
                      break;
                  case R.id.search_page:
                      startActivity(searchIntent);
                      break;
                  case R.id.create_page:
                      startActivity(createIntent);
                      break;
                  }
                return true;
            }
        });

        loadCards();
    }

    private void loadCards() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        GridLayout dashboardContainer = findViewById(R.id.dashboard_container);
        for (int i = 0; i < 9; i++) {
            MaterialCardView card = new MaterialCardView(this);
            card.setLayoutParams(new RelativeLayout.LayoutParams(width / 3, height / 3));

            RelativeLayout container = new RelativeLayout(this);
            container.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            card.addView(container);

            TextView text = new TextView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            text.setLayoutParams(params);
            text.setBackgroundColor(0x80000000);
            text.setTypeface(Typeface.DEFAULT_BOLD);
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);


            ImageView image = new ImageView(this);
            image.setScaleType(ImageView.ScaleType.FIT_XY);

            container.addView(image);
            container.addView(text);

            apiRequest.getRandomActivity(text, image);

            dashboardContainer.addView(card);

            Log.d("VIEW #", "" + i);
        }
    }


}
