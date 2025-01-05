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

import com.example.tapnbite_original.Checkout2_Activity;
import com.example.tapnbite_original.R;

public class Cart_Fragment extends Fragment {

    View view;
    private Button checkoutButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize the checkout button (Fragment to Activity)
        checkoutButton = view.findViewById(R.id.checkoutBtn);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Checkout2_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}