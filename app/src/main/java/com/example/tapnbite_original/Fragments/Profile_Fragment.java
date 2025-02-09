package com.example.tapnbite_original.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tapnbite_original.FAQs_Activity;
import com.example.tapnbite_original.LoginAccountStudent_Activity;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.TermsAndConditions_Activity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Profile_Fragment extends Fragment {

    private ImageButton aboutus, faqs, termsAndCondition, privacyPolicy, dropdown;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        /*-------------------------------------- CardView ---------------------------------------*/

        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());


        CardView aboutUs = view.findViewById(R.id.cvAboutUs);
        aboutUs.setOnClickListener(v -> {
            View view3 = getLayoutInflater().inflate(R.layout.dialog_aboutapp, null);

            Button close = view3.findViewById(R.id.closebtn);
            close.setOnClickListener(v1 -> dialog.dismiss());
            dialog.show();
            dialog.setContentView(view3);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        });

        CardView faqs = view.findViewById(R.id.cvFAQs);
        faqs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FAQs_Activity.class);
            startActivity(intent);
        });

        CardView termsAndConditions = view.findViewById(R.id.cvTermsAndConditions);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsAndConditions_Activity.class);
                startActivity(intent);
            }
        });

        CardView privacyPolicy = view.findViewById(R.id.cvPrivacyPolicy);
        privacyPolicy.setOnClickListener(v -> {

        });

        CardView logout = view.findViewById(R.id.cvLogout);
        logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Logout")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            handleLogout();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog1, int which) {
                            dialog1.dismiss(); // Close the dialog
                        }
                    })
                    .setCancelable(false) // Prevent closing the dialog by tapping outside
                    .show();
        });


        /*-------------------------------------- CardView ---------------------------------------*/


        /*--------------------------------------- Buttons ---------------------------------------*/

        Button topUp = view.findViewById(R.id.btnTopUp);

        /*--------------------------------------- Buttons ---------------------------------------*/


        /*-------------------------------------- TextView ---------------------------------------*/

        TextView tvUserName = view.findViewById(R.id.tvUserName);
        TextView tvUserID = view.findViewById(R.id.tvUserID);
        TextView tvPelletBalance = view.findViewById(R.id.tvPelletBalance);

        /*-------------------------------------- TextView ---------------------------------------*/


        return view;
    }

    private void handleLogout() {
        // Clear user session data
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // Clear all data
        editor.apply(); // Apply changes

        // Navigate to login screen
        Intent intent = new Intent(getActivity(), LoginAccountStudent_Activity.class);
        startActivity(intent);
        getActivity().finish(); // Finish the current activity
    };

}