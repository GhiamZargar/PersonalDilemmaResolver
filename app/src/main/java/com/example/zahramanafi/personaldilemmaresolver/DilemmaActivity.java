package com.example.zahramanafi.personaldilemmaresolver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class DilemmaActivity extends SingleFragmentActivity {

    private static final String EXTRA_DILEMMA_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID dilemmaId) {
        Intent intent = new Intent(packageContext, DilemmaActivity.class);
        intent.putExtra(EXTRA_DILEMMA_ID, dilemmaId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {


        UUID dilemmaId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DILEMMA_ID);
        return DilemmaFragment.newInstance(dilemmaId);
    }


}