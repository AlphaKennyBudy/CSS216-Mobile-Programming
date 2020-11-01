package kz.sdu.assignments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class DashboardFragment extends Fragment {

    private ApiRequest apiRequest;
    private Context context;
    private View view;

    public DashboardFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
        apiRequest = new ApiRequest(context);
        loadCards();
    }

    private void loadCards() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        GridLayout dashboardContainer = view.findViewById(R.id.dashboard_container);
        for (int i = 0; i < 9; i++) {
//            MaterialCardView card = new MaterialCardView(context);
//            card.setLayoutParams(new RelativeLayout.LayoutParams(width / 3, height / 3));
//
//            RelativeLayout container = new RelativeLayout(context);
//            container.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            card.addView(container);
//
//            TextView text = new TextView(context);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            text.setLayoutParams(params);
//            text.setBackgroundColor(0x80000000);
//            text.setTypeface(Typeface.DEFAULT_BOLD);
//            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//
//
//            ImageView image = new ImageView(context);
//            image.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            container.addView(image);
//            container.addView(text);
//
//            apiRequest.getRandomActivity(text, image);
//
//            dashboardContainer.addView(card);
//
//            Log.d("VIEW #", "" + i);
            FrameLayout containerLayout = new FrameLayout(context);
            dashboardContainer.addView(containerLayout);
            ViewGroup.LayoutParams params = containerLayout.getLayoutParams();
            params.width = width / 3;
            params.height = height / 3;
            containerLayout.setLayoutParams(params);
            containerLayout.setId(7777+i);
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            CardFragment cardFragment = new CardFragment(context);
            ft.replace(7777+i, cardFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}