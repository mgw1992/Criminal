package com.example.dad.criminalintent;

/**
 * Created by meaganwolf on 3/28/2016.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;
import java.util.UUID;

public class CrimeListActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    public static class CrimeListFragment extends Fragment {

        private RecyclerView mCrimeRecyclerView;
        private CrimeAdapter mAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

            mCrimeRecyclerView = (RecyclerView) view
                    .findViewById(R.id.crime_recycler_view);
            mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            updateUI();

            return view;
        }

        private void updateUI() {
            CrimeLab crimeLab = CrimeLab.get(getActivity());
            List<Crime> crimes = crimeLab.getCrimes();

            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }

        private class CrimeHolder extends RecyclerView.ViewHolder {

            public TextView mTitleTextView;

            private UUID crimeId;

            public void setCrimeId(UUID newCrimeId) {
                crimeId = newCrimeId;
            }

            public CrimeHolder(View itemView) {
                super(itemView);

                mTitleTextView = (TextView) itemView;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.dad.criminalintent.CrimeActivity");

                        startActivity(intent);

                    }
                });
            }

        }

        private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

            private List<Crime> mCrimes;

            public CrimeAdapter(List<Crime> crimes) {
                mCrimes = crimes;
            }

            @Override
            public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                return new CrimeHolder(view);
            }

            @Override
            public void onBindViewHolder(CrimeHolder holder, int position) {
                Crime crime = mCrimes.get(position);
                holder.mTitleTextView.setText(crime.getTitle());
                holder.setCrimeId(crime.getId());
            }

            @Override
            public int getItemCount() {
                return mCrimes.size();
            }


        }
    }

}
