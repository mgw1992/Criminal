package com.example.dad.criminalintent;

import android.support.v4.app.FragmentActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;


/**
 * Created by meaganwolf on 3/28/2016.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract android.support.v4.app.Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


}
