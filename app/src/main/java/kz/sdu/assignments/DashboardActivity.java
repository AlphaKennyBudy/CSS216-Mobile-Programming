package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONObject;

import static android.widget.RelativeLayout.*;

public class DashboardActivity extends AppCompatActivity {
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apiRequest = new ApiRequest(this);

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
