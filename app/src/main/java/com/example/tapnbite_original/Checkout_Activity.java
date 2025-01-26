package com.example.tapnbite_original;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.Fragments.Cart_Fragment;

public class Checkout_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(Checkout_Activity.this, R.color.primary));
        getWindow().setNavigationBarColor(ContextCompat.getColor(Checkout_Activity.this, R.color.white));

        /*--------------------------------------- Buttons ---------------------------------------*/

        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> {
            Fragment fragment = new Cart_Fragment();

            FragmentTransaction fn = getSupportFragmentManager().beginTransaction();
            fn.replace(R.id.main, fragment).commit();
        });

        Button placeOrder = findViewById(R.id.btnPlaceOrder);

        /*--------------------------------------- Buttons ---------------------------------------*/


        /*------------------------------------ Recycler View ------------------------------------*/

        RecyclerView orderSummary = findViewById(R.id.rvOrderSummaryList);

        /*------------------------------------ Recycler View ------------------------------------*/


        /*-------------------------------------- TextView ---------------------------------------*/

        TextView totalPrice = findViewById(R.id.tvTotalPrice);

        /*-------------------------------------- TextView ---------------------------------------*/



    }
}