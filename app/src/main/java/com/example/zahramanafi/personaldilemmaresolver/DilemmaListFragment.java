package com.example.zahramanafi.personaldilemmaresolver;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Shorai-4 on 2017-10-19.
 */

public class DilemmaListFragment extends android.support.v4.app.Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mDilemmaRecyclerView;
    private DilemmaAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dilemma_list, container, false);
        mDilemmaRecyclerView = (RecyclerView) view
                .findViewById(R.id.dilemma_recycler_view);
        mDilemmaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dilemma_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_dilemma:
                Dilemma dilemma = new Dilemma();
                DilemmaLab.get(getActivity()).addDilemma(dilemma);
                Intent intent = DilemmaPagerActivity
                        .newIntent(getActivity(), dilemma.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        DilemmaLab dilemmaLab = DilemmaLab.get(getActivity());
        int dilemmaCount = dilemmaLab.getDilemmas().size();
        @SuppressLint("StringFormatMatches") String subtitle = getString(R.string.subtitle_format, dilemmaCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        DilemmaLab dilemmaLab = DilemmaLab.get(getActivity());
        List<Dilemma> dilemmas = dilemmaLab.getDilemmas();

        if (mAdapter == null) {
            mAdapter = new DilemmaAdapter(dilemmas);
            mDilemmaRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();

    }

    // Page 182
    private class DilemmaHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener

    {

        private     Dilemma mDilemma;

        private TextView mTitleTextView;
        private Button mPositiveButton; // connect to diagram class
        private Button mNegativeButton;
        private Button mDateButton2;
        private CheckBox mSolvedCheckBox;
        //private Button mTimeButton2;


        public DilemmaHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleview);
            mPositiveButton =(Button) itemView.findViewById(R.id.positive);
            mNegativeButton =(Button) itemView.findViewById(R.id.negative);
            mDateButton2 = (Button) itemView.findViewById(R.id.dilemma_date2);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_dilemma_solved_check_box);
            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Set the crime's solved property
                    mDilemma.setSolved(isChecked);
                }
            });
            // mTimeButton2 = (Button) itemView.findViewById(R.id.dilemma_time2);
        }

        public void bindDilemma(Dilemma dilemma) {
            mDilemma = dilemma;
            mTitleTextView.setText(mDilemma.getTitle());
            mPositiveButton.setText(mDilemma.getPositiveBtn().toString());
            mNegativeButton.setText(mDilemma.getNegativeBtn().toString());
            mDateButton2.setText(mDilemma.getDate().toString());
            mDateButton2.setEnabled(false);
            // mTimeButton2.setText(mDilemma.getTime().toString());
            // mTimeButton2.setEnabled(false);
            mSolvedCheckBox.setChecked(mDilemma.isSolved());
            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Set the crime's solved property
                    mDilemma.setSolved(isChecked);
                }
            });



        }

        @Override
        public void onClick(View v) {
            /*Toast.makeText(getActivity(),
                    mDilemma.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();*/

            Intent intent = DilemmaPagerActivity.newIntent(getActivity(), mDilemma.getId());
            startActivity(intent);

        }

    }

    private class DilemmaAdapter extends RecyclerView.Adapter<DilemmaHolder> {
        private List<Dilemma> mDilemmas;
        public DilemmaAdapter(List<Dilemma> dilemmas) {
            mDilemmas = dilemmas;
        }


        @Override
        public DilemmaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_dilemma, parent, false);
            return new DilemmaHolder(view);
        }
        @Override
        public void onBindViewHolder(DilemmaHolder holder, int position) {
            Dilemma dilemma = mDilemmas.get(position);
            holder.bindDilemma(dilemma);
        }
        @Override
        public int getItemCount() {
            return mDilemmas.size();
        }
    }



}
// page 175