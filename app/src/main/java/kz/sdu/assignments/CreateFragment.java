package kz.sdu.assignments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
    private Bundle savedInstance = null;

    private RadioGroup agreement;
    private EditText activity;
    private Spinner type;
    private NumberPicker participants;

    private int agreementVal;
    private String activityVal;
    private int typeVal;
    private int participantsVal;

    public CreateFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiRequest = new ApiRequest(this.context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view = getView();

        participants = view.findViewById(R.id.participants_input);
        participants.setMaxValue(1000);
        participants.setMinValue(1);

        agreement = view.findViewById(R.id.agreement);
        activity = view.findViewById(R.id.activity_input);
        type = view.findViewById(R.id.type_input);

        if (savedInstanceState != null) savedInstance = savedInstanceState;
    }

    @Override
    public void onStart() {
        super.onStart();
        MaterialButton submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        if (savedInstance != null) {
            agreementVal = savedInstance.getInt("agreementVal");
            activityVal = savedInstance.getString("activityVal");
            typeVal = savedInstance.getInt("typeVal");
            participantsVal = savedInstance.getInt("participantsVal");

            agreement.check(agreementVal);
            activity.setText(activityVal);
            type.setSelection(typeVal);
            participants.setValue(participantsVal);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(context, "Loading previous saved instance", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        final AlertDialog.Builder alertResult = new AlertDialog.Builder(context);
        alertResult.setTitle("Are you sure?");
        alertResult.setMessage("We love you. Come back sooner! We've saved your inputs!");
        alertResult.setCancelable(false);
        alertResult.setPositiveButton("Yeah, I'll return later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertResult.create();
        alertDialog.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        agreementVal = agreement.getCheckedRadioButtonId();
        activityVal = activity.getText().toString();
        typeVal = type.getSelectedItemPosition();
        participantsVal = participants.getValue();
        Log.d("createfragment", "stopping");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        agreement = null;
        activity = null;
        type = null;
        participants = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        apiRequest = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("agreementVal", agreementVal);
        outState.putString("activityVal", activityVal);
        outState.putInt("typeVal", typeVal);
        outState.putInt("participantsVal", participantsVal);
    }

    @Override
    public void onClick(View v) {
        if (agreement.getCheckedRadioButtonId() == R.id.yes) {
            String activityVal = activity.getText().toString();
            if (activityVal.isEmpty())
                Toast.makeText(this.context, "You didn't provide an activity!", Toast.LENGTH_SHORT).show();
            else {
                String typeVal = type.getSelectedItem().toString();
                int participantsVal = participants.getValue();

                apiRequest.postActivity(activityVal, typeVal, participantsVal);

                activity.setText("");
                agreement.clearCheck();
            }

        } else {
            Toast.makeText(this.context, "You didn't agree with our terms of usage", Toast.LENGTH_SHORT).show();
        }
    }
}