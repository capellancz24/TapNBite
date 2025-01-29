package com.example.tapnbite_original.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.Notification_Activity;
import com.example.tapnbite_original.R;
import com.example.tapnbite_original.Search_Activity;

import org.w3c.dom.Text;

public class Home_Fragment extends Fragment {

    private CardView notification, meals, drinks, snacks, desserts;
    private TextView canteenNum, seeAll;
    private EditText search;
    private RecyclerView popularFoods;
    private ImageView filter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        search = view.findViewById(R.id.etSearch);
        search.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Intent intent = new Intent(getActivity(), Search_Activity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        popularFoods = view.findViewById(R.id.rvPopularFoods);


        /*------------------------------------- Card View ---------------------------------------*/

        notification = view.findViewById(R.id.cvNotification);
        notification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Notification_Activity.class);
            startActivity(intent);
        });

        meals = view.findViewById(R.id.cvMeals);
        meals.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Meals Clicked", Toast.LENGTH_SHORT).show();
        });

        drinks = view.findViewById(R.id.cvDrinks);
        drinks.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Drinks Clicked", Toast.LENGTH_SHORT).show();
        });

        snacks = view.findViewById(R.id.cvSnacks);
        snacks.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Snacks Clicked", Toast.LENGTH_SHORT).show();
        });

        desserts = view.findViewById(R.id.cvDesserts);
        desserts.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Desserts Clicked", Toast.LENGTH_SHORT).show();
        });

        filter = view.findViewById(R.id.ivFilter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
        });

        /*------------------------------------- Card View ---------------------------------------*/


        /*-------------------------------------- TextView ---------------------------------------*/

        canteenNum = view.findViewById(R.id.tvCanteenNum);

        seeAll = view.findViewById(R.id.tvSeeAll);
        seeAll.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Search_Activity.class);

            intent.putExtra("nav_title", "Popular Foods"); // Pass the title as an extra

            startActivity(intent);
        });

        /*-------------------------------------- TextView ---------------------------------------*/


        return view;
    }

    private void showOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an Option")
                .setMessage("Please select one of the following options:")
                .setPositiveButton("Canteen 2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleOption2();
                    }
                })
                .setNegativeButton("Canteen 1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleOption1();
                    }
                })
                .setCancelable(true) // Allow the dialog to be canceled
                .show();
    }


    private void handleOption1() {
        // For example, show a toast or navigate to another screen
        if (canteenNum.getText().equals("Canteen 1")){
            Toast.makeText(getActivity(), "Already Selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Canteen 1 Selected", Toast.LENGTH_SHORT).show();
            canteenNum.setText("Canteen 1");
        }
    }

    private void handleOption2() {
        // For example, show a toast or navigate to another screen
        if (canteenNum.getText().equals("Canteen 2")){
            Toast.makeText(getActivity(), "Already Selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Canteen 2 Selected", Toast.LENGTH_SHORT).show();
            canteenNum.setText("Canteen 2");
        }
    }
}