package com.example.zahramanafi.personaldilemmaresolver;
import android.support.v4.app.Fragment;

public class DilemmaListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DilemmaListFragment();
    }



}