package com.example.user.photontdd.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.user.photontdd.R;
/**
 * Created by tSravani on 5/14/2017.
 */

public class BaseFragment extends Fragment {

    Spinner wSpinner;
    Spinner hSpinner;
    CommListener listener;

    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        wSpinner = (Spinner) view.findViewById(R.id.widthSpinner);
        hSpinner = (Spinner) view.findViewById(R.id.heightSpinner);

        String array[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.customspinner, array);

        wSpinner.setAdapter(arrayAdapter);

        hSpinner.setAdapter(arrayAdapter);

        view.findViewById(R.id.goButon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int w = wSpinner.getSelectedItemPosition()+1;
                int h = hSpinner.getSelectedItemPosition()+1;
                listener.genGrid(w,h);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (CommListener) activity;
    }

    public interface CommListener {
        public void genGrid(int w, int h);
    }

}
