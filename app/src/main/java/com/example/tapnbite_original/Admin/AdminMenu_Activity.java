package com.example.tapnbite_original.Admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;


import com.example.tapnbite_original.Admin_Fragments.Dashboard_Fragment;
import com.example.tapnbite_original.Admin_Fragments.FoodProducts_Fragment;
import com.example.tapnbite_original.Admin_Fragments.Setting_Fragment;
import com.example.tapnbite_original.Menu_Activity;
import com.example.tapnbite_original.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminMenu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminmenu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWindow().setNavigationBarColor(ContextCompat.getColor(AdminMenu_Activity.this, R.color.white));
        getWindow().setStatusBarColor(ContextCompat.getColor(AdminMenu_Activity.this, R.color.primary));


        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.setSelectedItemId(R.id.nav_dashboard);
        bottomNavigation.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new Dashboard_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        int itemID = item.getItemId();

        Fragment selectedFragment = null;

        if (itemID == R.id.nav_dashboard) {
            selectedFragment = new Dashboard_Fragment();
        } else if (itemID == R.id.nav_foodproducts) {
            selectedFragment = new FoodProducts_Fragment();
        } else if (itemID == R.id.nav_settings) {
            selectedFragment = new Setting_Fragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    };
}