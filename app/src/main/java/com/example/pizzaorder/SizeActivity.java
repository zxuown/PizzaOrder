package com.example.pizzaorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SizeActivity extends AppCompatActivity {
    private TextView tvMessage;
    private RadioGroup rGrSizes;
    private Button btnNext;
    private CheckBox[] checkBoxes;
    private ArrayList<String> toppings = new ArrayList<>();
    private Pizza pizza;
    private Intent intent;
    private double previousSizePrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_size);
        pizza = (Pizza) getIntent().getSerializableExtra("pizza");
        intent = new Intent(this, OrderActivity.class);
        if (pizza != null) {
            rGrSizes = findViewById(R.id.rGrSizes);
            rGrSizes.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton selectedRadioButton = findViewById(checkedId);
                String sizeText = selectedRadioButton.getText().toString();
                radioBtnChanged(sizeText);
            });

            checkBoxes = new CheckBox[]{
                    findViewById(R.id.cBoxCheese),
                    findViewById(R.id.cBoxSausage),
                    findViewById(R.id.cBoxMushrooms),
            };

            for (CheckBox cBox : checkBoxes) {
                cBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    checkBoxChanged(cBox.getText().toString(), isChecked);
                });
            }

            btnNext = findViewById(R.id.btnNext);
            btnNext.setOnClickListener(v -> {
                startActivity(intent);
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void radioBtnChanged(String text){
        double newSizePrice = Pizza.getPriceWithoutName(text);

        pizza.setPrice(pizza.getPrice() - previousSizePrice + newSizePrice);
        previousSizePrice = newSizePrice;

        pizza.setSize(Pizza.getNameWithoutPrice(text));
        intent.putExtra("pizza", pizza);
    }

    public void checkBoxChanged(String text, boolean isChecked){
        if (isChecked){
            toppings.add(Pizza.getNameWithoutPrice(text).toLowerCase());
            pizza.setPrice(pizza.getPrice() + Pizza.getPriceWithoutName(text));
        } else {
            toppings.remove(Pizza.getNameWithoutPrice(text).toLowerCase());
            pizza.setPrice(pizza.getPrice() - Pizza.getPriceWithoutName(text));
        }
        pizza.setToppings(toppings);
        intent.putExtra("pizza", pizza);
    }
}
