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

import com.example.tapnbite_original.AdapterClass.FoodProductAdapter;
import com.example.tapnbite_original.ModelClass.FoodProductClass;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.databinding.ActivityMainBinding;
import com.example.tapnbite_original.databinding.FragmentFoodProductsBinding;

import java.util.ArrayList;

public class FoodProducts_Fragment extends Fragment {

    FragmentFoodProductsBinding binding;
    ListAdapter listAdapter;
    ArrayList<FoodProductClass> dataArrayList = new ArrayList<>();
    FoodProductClass foodProductClass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentFoodProductsBinding.inflate(inflater, container, false);

        displayFoodProduct();


        return binding.getRoot();
    }

    private void displayFoodProduct(){
        //NOTE: THIS IS JUST A SAMPLE DATA FOR TESTING PURPOSES
        String [] foodName = {"Chicken Adobo", "Siomai Rice", "Mango Ice Cream", "Iced Tea", "Water"};
        String [] foodCategory = {"Main Meals", "Snacks", "Desserts", "Beverages"};
        String [] pelletPrice = {"25", "45", "60", "75"};
        String [] stockStatus = {"Available", "Low Stock", "Out Of Stock"};

        int length = Math.min(Math.min(foodName.length, foodCategory.length), Math.min(pelletPrice.length, stockStatus.length));

        for (int i = 0; i < length; i++) {
            foodProductClass = new FoodProductClass(foodName[i], foodCategory[i], pelletPrice[i], stockStatus[i]);
            dataArrayList.add(foodProductClass);
        }

        listAdapter = new FoodProductAdapter(getActivity(), dataArrayList);
        binding.lvFoodProduct.setAdapter(listAdapter);

    }
}