package com.example.tapnbite_original.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapnbite_original.R;

public class Notification_Activity extends AppCompatActivity {

    private TextView newOrders, orderUpdate;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(Notification_Activity.this, R.color.primary));

        back = findViewById(R.id.ibBack);
        newOrders = findViewById(R.id.tvNewOrders);
        orderUpdate = findViewById(R.id.tvOrderUpdates);

        backButtonClicked();
        notificationButtonsClicked();
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

    private void notificationButtonsClicked(){
        newOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newOrders.getText().toString().equals("Off")) {
                    newOrders.setText("On");
                    Toast.makeText(Notification_Activity.this, "Push Notification Enalbed", Toast.LENGTH_SHORT).show();
                } else {
                    newOrders.setText("Off");
                    Toast.makeText(Notification_Activity.this, "Push Notification Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderUpdate.getText().toString().equals("Off")) {
                    orderUpdate.setText("On");
                    Toast.makeText(Notification_Activity.this, "Status Changes Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    orderUpdate.setText("Off");
                    Toast.makeText(Notification_Activity.this, "Status Changes Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}