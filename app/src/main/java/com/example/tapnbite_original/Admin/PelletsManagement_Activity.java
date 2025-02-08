package com.example.tapnbite_original.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tapnbite_original.R;

public class PelletsManagement_Activity extends AppCompatActivity {

    private ImageButton back;
    private TextView balanceAlert;
    private CardView balanceOverview, canteenDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pellets_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(PelletsManagement_Activity.this, R.color.primary));

        back = findViewById(R.id.ibBack);
        balanceAlert = findViewById(R.id.tvBalanceAlert);
        balanceOverview = findViewById(R.id.cvBalanceOverview);
        canteenDetails = findViewById(R.id.cvCanteenDetails);

        backButtonClicked();
        balanceAlertClicked();
    }

    protected void onResume() {
        super.onResume();
        // Ensure the navigation bar is visible
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    private void balanceAlertClicked(){
        balanceAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (balanceAlert.getText().toString().equals("Off")) {
                    balanceAlert.setText("On");
                    Toast.makeText(PelletsManagement_Activity.this, "Alert Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    balanceAlert.setText("Off");
                    Toast.makeText(PelletsManagement_Activity.this, "Alert Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void backButtonClicked(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}