package com.example.tapnbite_original.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapnbite_original.AdapterClass.PeakHoursAdapter;
import com.example.tapnbite_original.AdapterClass.SalesRecordAdapter;
import com.example.tapnbite_original.AdapterClass.TopProductPerformanceAdapter;
import com.example.tapnbite_original.Admin_Fragments.Dashboard_Fragment;
import com.example.tapnbite_original.ModelClass.PeakHoursClass;
import com.example.tapnbite_original.ModelClass.SalesRecordsClass;
import com.example.tapnbite_original.ModelClass.TopProductPerformanceClass;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.databinding.ActivityAnalyticsBinding;

import java.util.ArrayList;

public class Analytics_Activity extends AppCompatActivity {
    ActivityAnalyticsBinding binding;
    ListAdapter listAdapter;
    ArrayList<TopProductPerformanceClass> dataArrayList = new ArrayList<TopProductPerformanceClass>();
    ArrayList<SalesRecordsClass> dataArrayList2 = new ArrayList<SalesRecordsClass>();
    ArrayList<PeakHoursClass> dataArrayList3 = new ArrayList<PeakHoursClass>();

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize the binding
        binding = ActivityAnalyticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the content view to the root of the binding

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(Analytics_Activity.this, R.color.primary));

        back = findViewById(R.id.ibBack);
        backButtonClicked();

        displayTopProductPerformance();
        displaySalesRecords();
        displayPeakHours();
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

    private void displayTopProductPerformance(){
        //NOTE: THIS IS JUST A SAMPLE DATA FOR TESTING PURPOSES
        String[] names = {"Sinigang", "Adobo"};
        String[] salePercents = {"60%", "20%"}; // Percentages as strings
        String[] trendingPercents = {"8%", "4%",}; // Sales numbers as strings
        int[] trendIcons = {R.drawable.ic_trendingdown, R.drawable.ic_trendingup}; // Array of image resources

        // Clear the dataArrayList to avoid duplicates
        dataArrayList.clear();

        // Loop to add only the first 2 items to the dataArrayList
        for (int i = 0; i < Math.min(2, names.length); i++) {
            // Create a new TopProductPerformance object
            TopProductPerformanceClass topProductPerformance = new TopProductPerformanceClass(names[i], salePercents[i], trendingPercents[i], trendIcons[i]);
            dataArrayList.add(topProductPerformance);
        }

        // Set the adapter to the ListView
        listAdapter = new TopProductPerformanceAdapter(this, dataArrayList);
        binding.lvTopProductPerformance.setAdapter(listAdapter); // Ensure this ID matches your layout
    }
    private void displaySalesRecords(){
        //NOTE: THIS IS JUST A SAMPLE DATA FOR TESTING PURPOSES
        String[] names = {"Adobo", "Sinigang", "Pancit", "Lechon", "Halo-Halo"};
        String[] quantities = {"99", "50", "30", "20", "10"}; // Quantities as strings
        String[] amounts = {"250", "150", "100", "300", "200"}; // Sales amounts as strings

        // Clear the existing data in the list
        dataArrayList2.clear();

        // Loop to add items to the dataArrayList
        for (int i = 0; i < names.length; i++) {
            // Create a new SalesRecordsClass object
            SalesRecordsClass salesRecord = new SalesRecordsClass(names[i], amounts[i], quantities[i]); // Assuming sales amount is an integer
            dataArrayList2.add(salesRecord);
        }

        // Set the adapter to the ListView
        listAdapter = new SalesRecordAdapter(this, dataArrayList2);
        binding.lvSalesRecords.setAdapter(listAdapter); // Ensure this ID matches your layout
    }
    private void displayPeakHours(){
        String[] hourStart = {"11:30 AM", "3:00"};
        String[] hourEnd = {"12:30 NN", "4:00 PM"};
        String[] breakName = {"Lunch Rush", "Afternoon Break"};
        String[] orders = {"100", "200"};

        // Clear the existing data in the list
        dataArrayList3.clear();

        // Loop to add items to the dataArrayList3
        for (int i = 0; i < Math.min(2, breakName.length); i++) {
            // Create a new TopProductPerformance object
            PeakHoursClass peakHoursClass = new PeakHoursClass(hourStart[i], hourEnd[i], breakName[i], orders[i]);
            dataArrayList3.add(peakHoursClass);
        }

        listAdapter = new PeakHoursAdapter(this, dataArrayList3);
        binding.lvPeakHours.setAdapter(listAdapter); // Ensure this ID matches your layout
    }
}