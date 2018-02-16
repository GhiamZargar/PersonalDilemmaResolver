package com.example.zahramanafi.personaldilemmaresolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Shorai-4 on 2017-10-31.
 */

public class DilemmaPagerActivity extends AppCompatActivity {

    private static final String EXTRA_DILEMMA_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Dilemma> mDilemmas;

    public static Intent newIntent(Context packageContext, UUID dilemmaId) {
        Intent intent = new Intent(packageContext, DilemmaPagerActivity.class);
        intent.putExtra(EXTRA_DILEMMA_ID, dilemmaId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilemma_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_dilemma_pager_view_pager);
        mDilemmas = DilemmaLab.get(this).getDilemmas();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Dilemma dilemma = mDilemmas.get(position);
                return DilemmaFragment.newInstance(dilemma.getId());
            }
            @Override
            public int getCount() {
                return mDilemmas.size();
            }
        });


        UUID dilemmaId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DILEMMA_ID);

        for (int i = 0; i < mDilemmas.size(); i++) {
            if (mDilemmas.get(i).getId().equals(dilemmaId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}