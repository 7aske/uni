package com.example.cs330_dz01_nikola_tasic_3698;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class OpenUrlDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String[] faculties = {"FIT", "FAM", "FDU"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Pick faculty")
                .setItems(faculties, (dialog, which) -> {
                    final String facc = faculties[which];
                    Intent intent = null;
                    switch (facc) {
                        case "FIT":
                            intent = getOpenUrlIntent("https://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/");
                            break;
                        case "FAM":
                            intent = getOpenUrlIntent("https://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/");
                            break;
                        case "FDU":
                            intent = getOpenUrlIntent("https://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/");
                            break;
                    }
                    if (intent != null) {
                        startActivity(intent);
                    }
                });
        return builder.create();
    }

    private Intent getOpenUrlIntent(String url){
       return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }
}
