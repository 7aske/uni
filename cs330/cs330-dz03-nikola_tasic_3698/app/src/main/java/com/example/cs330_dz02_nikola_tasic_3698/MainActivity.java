package com.example.cs330_dz02_nikola_tasic_3698;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int info = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button infoButton = findViewById(R.id.info_button);
        Button mapButton = findViewById(R.id.map_button);

        findViewById(R.id.toolbar_fit).setOnClickListener(view -> {
            showToast("Selected FIT");
            info = R.string.fit;
        });
        findViewById(R.id.toolbar_fam).setOnClickListener(view -> {
            showToast("Selected FAM");
            info = R.string.fam;
        });
        findViewById(R.id.toolbar_fdu).setOnClickListener(view -> {
            showToast("Selected FDU");
            info = R.string.fdu;
        });


        infoButton.setOnClickListener(view -> showToast(info));

        mapButton.setOnClickListener(view -> {
            double latitude = 43.3071551;
            double longitude = 21.9473654;
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        });

    }

    private void showToast(int resId) {
        Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    private void showToast(String string) {
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }
}