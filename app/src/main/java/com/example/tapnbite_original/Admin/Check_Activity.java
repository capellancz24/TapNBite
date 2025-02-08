package com.example.tapnbite_original.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapnbite_original.AdapterClass.DailySalesAdapter;
import com.example.tapnbite_original.Admin_Fragments.Dashboard_Fragment;
import com.example.tapnbite_original.Fragments.Cart_Fragment;
import com.example.tapnbite_original.Menu_Activity;
import com.example.tapnbite_original.ModelClass.DailySalesClass;
import com.example.tapnbite_original.ModelClass.FoodProductClass;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.databinding.ActivityCheckBinding;
import com.example.tapnbite_original.databinding.FragmentFoodProductsBinding;

import java.util.ArrayList;

public class Check_Activity extends AppCompatActivity {

    ActivityCheckBinding binding;
    ListAdapter listAdapter;
    ArrayList<DailySalesClass> dataArrayList = new ArrayList<>();
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize the binding
        binding = ActivityCheckBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the content view to the root of the binding

        // Optional: Set up window insets if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.ibBack);
        backButtonClicked();


        getWindow().setStatusBarColor(ContextCompat.getColor(Check_Activity.this, R.color.primary));


        displayDailySales();
    }

    protected void onResume() {
        super.onResume();
        // Ensure the navigation bar is visible
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    private void backButtonClicked(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void displayDailySales(){
        //NOTE: THIS IS JUST A SAMPLE DATA FOR TESTING PURPOSES
        String [] date = {"Jan 29"};
        String [] salesC1 = {"1000"};
        String [] salesC2 = {"2000"};

        // Number of times to display the same item
        int numberOfItemsToDisplay = 5;

        // Loop to add the same item to the dataArrayList
        for (int i = 0; i < numberOfItemsToDisplay; i++) {
            // Create a new DailySales object (assuming you have a class for it)
            DailySalesClass dailySales = new DailySalesClass(date[0], salesC1[0], salesC2[0]);
            dataArrayList.add(dailySales);
        }

        listAdapter = new DailySalesAdapter(Check_Activity.this, dataArrayList);
        binding.lvDailySales.setAdapter(listAdapter);
    }
}