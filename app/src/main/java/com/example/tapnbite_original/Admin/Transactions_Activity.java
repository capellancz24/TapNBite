package com.example.tapnbite_original.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapnbite_original.AdapterClass.SalesRecordAdapter;
import com.example.tapnbite_original.AdapterClass.TransactionsAdapter;
import com.example.tapnbite_original.Admin_Fragments.Dashboard_Fragment;
import com.example.tapnbite_original.ModelClass.DailySalesClass;
import com.example.tapnbite_original.ModelClass.SalesRecordsClass;
import com.example.tapnbite_original.ModelClass.TopProductPerformanceClass;
import com.example.tapnbite_original.ModelClass.TransactionsClass;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.databinding.ActivityAnalyticsBinding;
import com.example.tapnbite_original.databinding.ActivityTransactionsBinding;

import java.util.ArrayList;

public class Transactions_Activity extends AppCompatActivity {

    ActivityTransactionsBinding binding;
    ListAdapter listAdapter;
    ArrayList<TransactionsClass> dataArrayList = new ArrayList<TransactionsClass>();
    private ImageButton back;
    private Spinner canteens, items;
    private String[] canteenItems = {"All Canteens", "Canteen 1", "Canteen 2"};
    private String[] productItems = {"All Items", "Meals", "Beverages"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize the binding
        binding = ActivityTransactionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the content view to the root of the binding

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(Transactions_Activity.this, R.color.primary));

        back = findViewById(R.id.ibBack);
        canteens = findViewById(R.id.spinnerCanteens);
        items = findViewById(R.id.spinnerItems);

        backButtonClicked();
        spinnerCanteens();
        displayTransactions();
    }

    @Override
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

    private void spinnerCanteens() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> canteenAdapater = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, canteenItems);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productItems);

        // Specify the layout to use when the list of choices appears
        canteenAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        canteens.setAdapter(canteenAdapater);
        items.setAdapter(itemAdapter);

        // Set up the canteen item selection listener
        canteens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Do something with the selected item
                Toast.makeText(Transactions_Activity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set up the product item selection listener
        items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Do something with the selected item
                Toast.makeText(Transactions_Activity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void displayTransactions(){
        //NOTE: THIS IS JUST A SAMPLE DATA FOR TESTING PURPOSES
        String[] orderNum = {"#10001", "#10002"};
        String[] canteenNum = {"Canteen 1", "Canteen 2"};
        String[] date = {"2025-10-01", "2025-10-02"};
        String[] Num = {"150", "200"};

        // Clear the existing data in the list
        dataArrayList.clear();

        // Number of times to display the same item
        int numberOfItemsToDisplay = 6;

        // Loop to add items to the dataArrayList
        for (int i = 0; i < numberOfItemsToDisplay; i++) {
            // Use modulo to cycle through the available data
            int index = i % orderNum.length; // This will cycle through 0 and 1
            TransactionsClass transactionsClass = new TransactionsClass(orderNum[index], canteenNum[index], date[index], Num[index]);
            dataArrayList.add(transactionsClass);
        }

        // Set the adapter to the ListView
        listAdapter = new TransactionsAdapter(this, dataArrayList);
        binding.lvTransactions.setAdapter(listAdapter); // Ensure this ID matches your layout
    }
}