package com.example.fritt.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by fritt on 2017/3/30.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private static final int REQUEST_CRIME=1;
    private int position;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = layoutInflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter==null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
          //  mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemChanged(position);
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       // public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;


        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            mDateTextView=(TextView)itemView.findViewById(R.id.list_item_Date_text_view);
            mSolvedCheckBox=(CheckBox)itemView.findViewById(R.id.list_item_solved_check_box);

        }

        public void bindCrime(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(crime.getDate().toString());
            mSolvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mCrime.getTitle()+" clicked", Toast.LENGTH_SHORT).show();
            Intent intent=CrimeActivity.newIntent(getActivity(),mCrime.getId());
            //startActivity(intent);
            startActivityForResult(intent,REQUEST_CRIME);
        }
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==REQUEST_CRIME){
            if(resultCode==RESULT_OK){
                position=CrimeFragment.getReturnReslut(data);
            }
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);

            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
           holder.bindCrime(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }




    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }


}
