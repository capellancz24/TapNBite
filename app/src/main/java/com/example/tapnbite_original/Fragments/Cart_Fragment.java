package com.example.tapnbite_original.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.Checkout_Activity;
import com.example.tapnbite_original.R;

public class Cart_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Fragment to Activity
        Button checkout = view.findViewById(R.id.btnCheckout);
        checkout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Checkout_Activity.class);
            startActivity(intent);
        });

        RecyclerView cart = view.findViewById(R.id.rvCart);

        return view;
    }
}