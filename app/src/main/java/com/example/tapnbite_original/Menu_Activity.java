package com.example.tapnbite_original;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.tapnbite_original.Fragments.Cart_Fragment;
import com.example.tapnbite_original.Fragments.Home_Fragment;
import com.example.tapnbite_original.Fragments.OrderHistory_Fragment;
import com.example.tapnbite_original.Fragments.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Menu_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        getWindow().setStatusBarColor(ContextCompat.getColor(Menu_Activity.this, R.color.primary));
        getWindow().setNavigationBarColor(ContextCompat.getColor(Menu_Activity.this, R.color.white));


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.setSelectedItemId(R.id.nav_home);
        bottomNavigation.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new Home_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        int itemID = item.getItemId();

        Fragment selectedFragment = null;

        if (itemID == R.id.nav_home) {
            selectedFragment = new Home_Fragment();
        } else if (itemID == R.id.nav_orders) {
            selectedFragment = new OrderHistory_Fragment();
        } else if (itemID == R.id.nav_cart) {
            selectedFragment = new Cart_Fragment();
        } else if (itemID == R.id.nav_profile) {
            selectedFragment = new Profile_Fragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    };
}