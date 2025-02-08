package com.example.tapnbite_original.Admin_Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.AdapterClass.FoodProductAdapter;
import com.example.tapnbite_original.ModelClass.FoodProductClass;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.databinding.ActivityMainBinding;
import com.example.tapnbite_original.databinding.FragmentFoodProductsBinding;

import java.util.ArrayList;
import java.util.List;

public class FoodProducts_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodProductAdapter adapter;
    private List<FoodProductClass> foodItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_products, container, false);

        recyclerView = view.findViewById(R.id.rvFoodProducts);

        displayFoodProduct();

        return view;
    }

    private void displayFoodProduct(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data
        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodProductClass("Chicken Adobo", "Main Meals", "75", "Available"));
        foodItemList.add(new FoodProductClass("Beef Adobo", "Main Meals", "75", "Low Stock"));
        foodItemList.add(new FoodProductClass("Pork Adobo", "Main Meals", "75", "Out Of Stock"));

        adapter = new FoodProductAdapter(getContext(), foodItemList);
        recyclerView.setAdapter(adapter);
    }
}