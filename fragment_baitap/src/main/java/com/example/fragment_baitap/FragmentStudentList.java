package com.example.fragment_baitap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class FragmentStudentList extends ListFragment {

    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter;

    IStudent iStudent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        InitData();

        iStudent = (IStudent) getActivity();

        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    private void InitData(){
        studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("FullName 1", 1996, "Dia chi 1", "Email 1"));
        studentArrayList.add(new Student("FullName 2", 1996, "Dia chi 2", "Email 2"));
        studentArrayList.add(new Student("FullName 3", 1996, "Dia chi 3", "Email 3"));
        studentArrayList.add(new Student("FullName 4", 1996, "Dia chi 4", "Email 4"));
        studentArrayList.add(new Student("FullName 5", 1996, "Dia chi 5", "Email 5"));
        studentArrayList.add(new Student("FullName 6", 1996, "Dia chi 6", "Email 6"));
        studentArrayList.add(new Student("FullName 7", 1996, "Dia chi 7", "Email 7"));
        studentArrayList.add(new Student("FullName 8", 1996, "Dia chi 8", "Email 8"));
        studentArrayList.add(new Student("FullName 9", 1996, "Dia chi 9", "Email 9"));
        studentArrayList.add(new Student("FullName 10", 1996, "Dia chi 10", "Email 10"));

        studentAdapter = new StudentAdapter(getActivity(), R.layout.row_student, studentArrayList);
        setListAdapter(studentAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        // Sử dụng interface để thực hiện get Student info từ fragment student list sang MainActivity
        iStudent.GetInfo(studentArrayList.get(position));

        super.onListItemClick(l, v, position, id);
    }
}
