package kz.sdu.assignments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    private ApiRequest apiRequest;
    private View view;
    private Context context;

    public SearchFragment(Context context) {
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
        apiRequest = new ApiRequest(this.context);

        MaterialButton btn = view.findViewById(R.id.search_btn);
        btn.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onClick(View v) {
        Spinner spinner = view.findViewById(R.id.type);
        String query = spinner.getSelectedItem().toString();

        CodeView code = view.findViewById(R.id.code);
        code.setOptions(Options.Default.get(this.context).withTheme(ColorTheme.MONOKAI));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        code.setLayoutParams(params);

        CheckBox checkBox = view.findViewById(R.id.load_image);
        if (checkBox.isChecked()) {
            ImageView image = view.findViewById(R.id.res_image);
            apiRequest.getImage(image);
        }

        apiRequest.searchActivity(query, code);
    }
}