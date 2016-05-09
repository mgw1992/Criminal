package com.example.dad.criminalintent; /**
 * Created by meaganwolf on 3/26/2016.
 */

import com.example.dad.criminalintent.CrimeActivity.Crime;
import java.util.List;
import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab extends CrimeActivity {
    private Crime mCrime;

    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return (CrimeLab) sCrimeLab;
    }

    public List<Crime> getCrimes(){
            return mCrimes;
        }
    Crime crime = new Crime();

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(crime);
        }

    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }


}
