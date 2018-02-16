package com.example.zahramanafi.personaldilemmaresolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Shorai-4 on 2017-10-18.
 */

public class DilemmaFragment extends android.support.v4.app.Fragment {

    private static final String ARG_DILEMMA_ID = "dilemma_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 0;


    private EditText mTitleField;
    private EditText mEnterTitleField;
    private EditText mPositiveEmotion;
    private EditText mNegativEmotion;
    private Button StartDilemmaButton;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mTimeButton;


    private Dilemma mDilemma;

    public static DilemmaFragment newInstance(UUID dilemmaId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DILEMMA_ID, dilemmaId);
        DilemmaFragment fragment = new DilemmaFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDilemma = new Dilemma();


        UUID dilemmaId = (UUID) getArguments().getSerializable(ARG_DILEMMA_ID);


        mDilemma = DilemmaLab.get(getActivity()).getdilemma(dilemmaId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dilemma, container, false);

       /* mTitleField = (EditText)v.findViewById(R.id.dilemma_title);
        mTitleField.setText(mDilemma.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mDilemma.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });*/

        mEnterTitleField = (EditText)v.findViewById(R.id.enter_dilemma_title);
        mEnterTitleField.setText(mDilemma.getTitle());
        mEnterTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mDilemma.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mPositiveEmotion = (EditText)v.findViewById(R.id.positive_emotion);
        mPositiveEmotion.setText(mDilemma.getPositiveBtn());
        mPositiveEmotion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mDilemma.setPositiveBtn(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mNegativEmotion = (EditText)v.findViewById(R.id.negative_emotion);
        mNegativEmotion.setText(mDilemma.getNegativeBtn());
        mNegativEmotion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mDilemma.setNegativeBtn(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });


        mDateButton = (Button)v.findViewById(R.id.dilemma_date);
        updateDate();
        mDateButton.setText(mDilemma.getDate().toString());
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                //  DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mDilemma.getDate());
                dialog.setTargetFragment(DilemmaFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.dilemma_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Set the crime's solved property
                mDilemma.setSolved(isChecked);
            }
        });

       /* mTimeButton = (Button)v.findViewById(R.id.dilemma_time);
        mTimeButton.setText(mDilemma.getTime().toString());
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
               // TimePickerFragment dialog = new TimePickerFragment();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mDilemma.getTime());
                dialog.setTargetFragment(DilemmaFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);


                /*  FragmentManager manager = getFragmentManager();
                //  DatePickerFragment dialog = new DatePickerFragment();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mDilemma.getTime());
                dialog.show(manager, DIALOG_TIME);
            }
        });*/


        StartDilemmaButton = (Button) v.findViewById(R.id.start_btn);
        StartDilemmaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DilemmaListActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDilemma.setDate(date);
            updateDate();
            mDateButton.setText(mDilemma.getDate().toString());
        }
    }

    private void updateDate() {
        mDateButton.setText(mDilemma.getDate().toString());}
}
