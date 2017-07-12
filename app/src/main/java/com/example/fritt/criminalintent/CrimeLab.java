package com.example.fritt.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by fritt on 2017/3/29.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private Crime mCrime;

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();


    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return crime;
        }
        return null;
    }
    public int getIndex(UUID id){
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return mCrimes.indexOf(crime);
        }
        return  0;

    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }
}
