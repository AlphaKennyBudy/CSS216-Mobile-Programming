package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {
    private final String boredAPI = "https://www.boredapi.com/api/";
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apiRequest = new ApiRequest(this);

        GridLayout dashboardContainer = findViewById(R.id.dashboard_container);
        for (int i = 0; i < 15; i++) {
//            MaterialCardView card = new MaterialCardView(this);

            TextView text = new TextView(this);
            apiRequest.getRandomActivity(text);
//            card.addView(text);

            text.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dashboardContainer.addView(text);

            Log.d("VIEW #", ""+i);
        }
    }
}
