package be.ferrara.project2022.mpm.controllers;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.ferrara.project2022.mpm.models.Project;
import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Step;

public class StepActivity extends AppCompatActivity implements StepFragment.Listener {
    public static final String PROJECT_NAME = "PROJECT_NAME";
    public static final int REQUEST_CODE = 10;
    private EditText mProjectNameEditText;
    private EditText mDescriptionEditText;
    private Button mAddStepButton;
    private LinearLayout mLayout;
    private StepFragment fragment;
    private TextView mTotalPointTextView;
    private Project mProject;
    private final int mIdContainer = R.id.horizontal_layout;
    private Step mStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        initializeComponent();
        mProject = (Project) getIntent().getSerializableExtra(PROJECT_NAME);

        setTextView();
        updateUI();
        clickAddStepButton();
        setEditText();
    }

    private void clickAddStepButton() {
        mAddStepButton.setOnClickListener(view -> {
            mStep = new Step();
            mProject.addStep(mStep);
            addFragment(mStep);
        });
    }

    private void updateUI() {
        mLayout.removeAllViews();
        for (Step step : mProject.getStepList()) {
            addFragment(step);
        }
    }

    private void setTextView() {
        mProjectNameEditText.setText(mProject.getName());
        mDescriptionEditText.setText(mProject.getDescription());
        mTotalPointTextView.setText(mProject.getTotalPoint()+"/20");
    }

    private void setEditText() {
        mProjectNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mProject.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mProject.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(PROJECT_NAME, mProject);
        this.setResult(REQUEST_CODE, intent);
        super.onBackPressed();
    }

    private void addFragment(Step step) {
            FragmentManager fm = getSupportFragmentManager();
            fragment = new StepFragment();
            fragment.setListener(this);
            fragment.setStep(step);
            fm.beginTransaction().add(mIdContainer, fragment).commit();
    }

    private void initializeComponent() {
        mDescriptionEditText = findViewById(R.id.description_text_input);
        mProjectNameEditText = findViewById(R.id.project_name_text_input);
        mAddStepButton = findViewById(R.id.add_step_button);
        mLayout = findViewById(R.id.horizontal_layout);
        mTotalPointTextView = findViewById(R.id.total_points_text_view);
    }

    @Override
    public void returnStep(Step step) {
        if(mStep !=null)
            mProject.updateStep(step);
    }
}