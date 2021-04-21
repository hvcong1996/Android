package com.example.fragment_send_data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment implements View.OnClickListener {

    TextView txtFragmentATitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        InitView(view);

        return view;
    }

    private void InitView(View view){
        txtFragmentATitle = (TextView) view.findViewById(R.id.textViewTitle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textViewTitle:

                break;
        }
    }
}
