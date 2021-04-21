package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment implements View.OnClickListener{

    TextView txtFragBTitle;
    EditText edtInput;
    Button btnFragBSetToMainActivity, btnFragBSetToFragA;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);
        InitView(view);

        // Không sử dụng return super mà sẽ return về Layout Fragment
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void InitView(View view){
        txtFragBTitle = (TextView) view.findViewById(R.id.textViewFragmentBTitle);
        edtInput = (EditText) view.findViewById(R.id.editTextInputNameB);
        btnFragBSetToFragA = (Button) view.findViewById(R.id.buttonFragBSetToFragA);
        btnFragBSetToFragA.setOnClickListener(FragmentB.this);
        btnFragBSetToMainActivity = (Button) view.findViewById(R.id.buttonFragBSetToMainActivity);
        btnFragBSetToMainActivity.setOnClickListener(FragmentB.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFragBSetToMainActivity:
                Toast.makeText(getActivity(), "Set to MainActivity: " + edtInput.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonFragBSetToFragA:
                // Get FragA Title
                TextView txtFragATitle = (TextView) getActivity().findViewById(R.id.textViewFragmentATitle);
                String dataInput = edtInput.getText().toString();
                txtFragATitle.setText(dataInput);

                Toast.makeText(getActivity(), "Set to FragA: " + edtInput.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}