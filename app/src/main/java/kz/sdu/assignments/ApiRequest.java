package kz.sdu.assignments;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiRequest {
    private final String boredAPI = "https://www.boredapi.com/api/";
    private RequestQueue queue;

    public ApiRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void getRandomActivity(final TextView view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, boredAPI + "activity", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                view.setText(response.toString());
                Log.d("API_REQUEST", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }
}
