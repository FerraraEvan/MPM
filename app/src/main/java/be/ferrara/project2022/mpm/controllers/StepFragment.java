package be.ferrara.project2022.mpm.controllers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Step;

public class StepFragment extends androidx.fragment.app.Fragment {
    private Step mStep;
    private EditText mNameField;
    private Spinner mPointSpinner;
    private LinearLayout mLayout;
    String[] points={"0","1","2","3","4","5","6","7","8","9","10"};
    private Listener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step,container,false);
        initializeComponent(v);
        mNameField.setText(mStep.getName());
        mPointSpinner.setSelection(mStep.getPoints());
        setText();
        initializeSpinner(points);
        return v;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setStep(Step mStep) {
        this.mStep = mStep;
    }

    private void initializeComponent(View v) {
        mNameField =  v.findViewById(R.id.name_step_edit_text);
        mPointSpinner = v.findViewById(R.id.points_spinner);
    }

    private void initializeSpinner(String[] points) {
        ArrayAdapter ad = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, points);
        ad.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        mPointSpinner.setAdapter(ad);
        mPointSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listener.returnPoint(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });


    }

    private void setText() {
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.returnName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });
    }

    interface Listener{
        void returnPoint(int point);
        void returnName(String name);
    }
}
