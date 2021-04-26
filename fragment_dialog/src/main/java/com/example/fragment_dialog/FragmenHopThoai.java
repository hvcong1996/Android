package com.example.fragment_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmenHopThoai extends DialogFragment {

    IDeleteData deleteData;

    @Override
    public void onAttach(@NonNull Context context) {

        // Get activity(activity implements IDeleteData)
        deleteData = (IDeleteData) getActivity();

        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Thông báo");
        dialogBuilder.setMessage("Bạn có muốn xóa không?");
        dialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData.Delete(true);
            }
        });
        dialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData.Delete(false);
            }
        });
        dialogBuilder.show();

        // Convert AlertDialog.Builder to Dialog
        Dialog dialog = dialogBuilder.create();
        return dialog;
    }
}
