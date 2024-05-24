package com.example.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnCheesePizza;

    private Button btnMargaritaPizza;

    private Button btnHawaiianPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnCheesePizza = findViewById(R.id.btnCheesePizza);
        btnMargaritaPizza = findViewById(R.id.btnMargaritaPizza);
        btnHawaiianPizza = findViewById(R.id.btnHawaiianPizza);

        btnCheesePizza.setOnClickListener(v -> {
            setPizzaName(btnCheesePizza.getText().toString());
        });

        btnMargaritaPizza.setOnClickListener(v -> {
            setPizzaName(btnMargaritaPizza.getText().toString());
        });

        btnHawaiianPizza.setOnClickListener(v -> {
            setPizzaName(btnHawaiianPizza.getText().toString());
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setPizzaName(String text){
         Pizza pizza = new Pizza();
         pizza.setName(Pizza.getNameWithoutPrice(text));
         pizza.setPrice(Pizza.getPriceWithoutName(text));
        Intent intent = new Intent(this, SizeActivity.class);
        intent.putExtra("pizza", pizza);
        startActivity(intent);
    }
}