package com.taske.duncan.util;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.taske.duncan.R;

public class DrawerUtil {
    public static ActionBarDrawerToggle initDrawerAndToggle(AppCompatActivity appCompatActivity, @IdRes int res) {
        DrawerLayout drawerLayout = (DrawerLayout) appCompatActivity.findViewById(res);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(appCompatActivity, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toggle;
    }
}
