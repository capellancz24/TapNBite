package com.example.tapnbite_original.Admin_Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tapnbite_original.Admin.CanteenDetails_Activity;
import com.example.tapnbite_original.Admin.Check_Activity;
import com.example.tapnbite_original.Admin.Notification_Activity;
import com.example.tapnbite_original.Admin.PelletsManagement_Activity;
import com.example.tapnbite_original.Admin.SecuritySetting_Activity;
import com.example.tapnbite_original.LoginAccountStudent_Activity;
import com.example.tapnbite_original.R;


public class Setting_Fragment extends Fragment {

    CardView canteendetails, pelletsManagement, notification, securitySetting, logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        canteendetails = view.findViewById(R.id.cvCanteenDetails);
        pelletsManagement = view.findViewById(R.id.cvPelletsManagement);
        notification = view.findViewById(R.id.cvNotification);
        securitySetting = view.findViewById(R.id.cvSecuritySetting);
        logout = view.findViewById(R.id.cvLogout);

        settingButtonsClicked();

        return view;
    }

    private void settingButtonsClicked(){
        canteendetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CanteenDetails_Activity.class);
                startActivity(intent);
            }
        });

        pelletsManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PelletsManagement_Activity.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Notification_Activity.class);
                startActivity(intent);
            }
        });

        securitySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecuritySetting_Activity.class);
                startActivity(intent);
            }
        });

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