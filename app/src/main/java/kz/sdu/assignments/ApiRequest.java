package kz.sdu.assignments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.kbiakov.codeview.CodeView;


public class ApiRequest {
    private final String boredAPI = "https://www.boredapi.com/api/";
    private final String waifuAPI = "https://waifu.pics/api/sfw/waifu";
    private RequestQueue queue;

    public ApiRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void searchActivity(String type, final CodeView text) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, boredAPI + "activity?type=" + type, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                text.setCode(response.toString(), "json");
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

    public void getRandomActivity(final TextView text, final ImageView image) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, boredAPI + "activity", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    text.setText(String.format("Let's %s! It requiers only %d people to do!", response.getString("activity"), response.getInt("participants")));
                    getImage(image);
                    Log.d("API_REQUEST", response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void getImage(final ImageView image) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, waifuAPI, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String url = response.getString("url");
                    ImageRequest request = new ImageRequest(url,
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    image.setImageBitmap(bitmap);
                                }
                            }, 0, 0, null,
                            new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                    queue.add(request);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
