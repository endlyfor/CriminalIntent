package com.example.fritt.criminalintent;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by fritt on 2017/3/30.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return  new CrimeListFragment();

    }
}
