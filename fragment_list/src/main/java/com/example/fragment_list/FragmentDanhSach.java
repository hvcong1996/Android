package com.example.fragment_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

// Vì sử dụng @android:id/list nên cần phải extend ListFragment chứ không extends theo kiểu thông thường
// Khi extends ListFragment thì ở fragment layout phải có list
public class FragmentDanhSach extends ListFragment {

    String[] arrayCity = {"Tay ninh", "Nha trang", "Hai phong", "Ha noi"};
    ArrayAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Init adapter
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayCity);
        // Set adapter to List
        setListAdapter(arrayAdapter);

        return inflater.inflate(R.layout.fragment_danh_sach, container, false);
    }

    // Event khi click item on listView
    // Event listView ở Fragment khác với Activity sẽ dùng listView.setOnItemClickListener
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Toast.makeText(getActivity(), arrayCity[position], Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
