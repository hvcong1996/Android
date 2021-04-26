package com.example.fragment_baitap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentStudentDetail extends Fragment {

    TextView txtHoTen, txtNamSinh, txtDiaChi, txtEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student_detail, container, false);

        InitView(view);

        return view;
    }

    private void InitView(View view){
        txtHoTen = (TextView) view.findViewById(R.id.textViewDetail_FullName);
        txtNamSinh = (TextView) view.findViewById(R.id.textViewDetail_NamSinh);
        txtDiaChi = (TextView) view.findViewById(R.id.textViewDetail_DiaChi);
        txtEmail = (TextView) view.findViewById(R.id.textViewDetail_Email);
    }

    public void SetInfo(Student student){
        txtHoTen.setText("Họ tên: " + student.getHoTen());
        txtNamSinh.setText("Năm sinh: " + student.getNamSinh());
        txtDiaChi.setText("Địa chỉ: " + student.getDiaChi());
        txtEmail.setText("Email: " + student.getEmail());
    }
}
