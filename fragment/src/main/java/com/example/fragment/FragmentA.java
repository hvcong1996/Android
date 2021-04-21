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

public class FragmentA extends Fragment implements View.OnClickListener{

    TextView txtFragATitle;
    EditText edtInput;
    Button btnFragASetToMainActivity, btnFragASetToFragB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);
        InitView(view);

        // Không sử dụng return super mà sẽ return về Layout Fragment
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void InitView(View view){
        txtFragATitle = (TextView) view.findViewById(R.id.textViewFragmentATitle);
        edtInput = (EditText) view.findViewById(R.id.editTextInputNameA);

        btnFragASetToMainActivity = (Button) view.findViewById(R.id.buttonFragASetToMainActivity);
        btnFragASetToMainActivity.setOnClickListener(FragmentA.this);

        btnFragASetToFragB = (Button) view.findViewById(R.id.buttonFragASetToFragB);
        btnFragASetToFragB.setOnClickListener(FragmentA.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFragASetToMainActivity:
                // Get MainActivity title
                TextView txtActivityMainTitle = (TextView) getActivity().findViewById(R.id.textViewMainActivityTitle);
                txtActivityMainTitle.setText(edtInput.getText().toString());
//                Toast.makeText(getActivity(), "Set to MainActivity: " + txtActivityMainTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonFragASetToFragB:
                Toast.makeText(getActivity(), "Set to FragB: " + edtInput.getText().toString(), Toast.LENGTH_SHORT).show();
                break;    
        }
    }

    public void SetFragATitle(String str){
        txtFragATitle.setText(str);
    }
}
