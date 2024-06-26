package com.example.pizzaorder;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        Pizza pizza = (Pizza) getIntent().getSerializableExtra("pizza");

        tvResult = findViewById(R.id.tvRes);
        if (pizza != null) {
            List<String> toppings = pizza.getToppings();
            String toppingsText = toppings != null ? String.join(", ", toppings) : "None";
            tvResult.setText("Pizza: " + pizza.getName() + "\nSize: " + pizza.getSize()
                    + "\nToppings: " + toppingsText
                    +"\nPrice: " + pizza.getPrice() + "$");
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
