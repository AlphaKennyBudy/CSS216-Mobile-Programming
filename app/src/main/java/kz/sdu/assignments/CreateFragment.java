package kz.sdu.assignments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class CreateFragment extends Fragment implements View.OnClickListener {
    private ApiRequest apiRequest;
    private Context context;
    private View view;

    public CreateFragment(Context context) {
        this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        apiRequest = new ApiRequest(this.context);
        view = getView();

        NumberPicker participants = view.findViewById(R.id.participants_input);
        participants.setMaxValue(1000);
        participants.setMinValue(1);

        MaterialButton submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onClick(View v) {
        RadioGroup agreement = view.findViewById(R.id.agreement);
        if (agreement.getCheckedRadioButtonId() == R.id.yes) {
            String activity = ((EditText) view.findViewById(R.id.activity_input)).getText().toString();
            if (activity.isEmpty())
                Toast.makeText(this.context, "You didn't provide an activity!", Toast.LENGTH_SHORT).show();
            else {
                String type = ((Spinner)view.findViewById(R.id.type_input)).getSelectedItem().toString();
                int participants = ((NumberPicker)view.findViewById(R.id.participants_input)).getValue();

                apiRequest.postActivity(activity,type,participants);
            }

        } else {
            Toast.makeText(this.context, "You didn't agree with our terms of usage", Toast.LENGTH_SHORT).show();
        }
    }
}