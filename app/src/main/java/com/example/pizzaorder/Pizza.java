package com.example.pizzaorder;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza implements Serializable {

    public String name;

    public String size;

    public ArrayList<String> toppings;

    public double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static double getPriceWithoutName(String s){
        s = s.replace("$", "");
        return Double.parseDouble(s.substring(s.indexOf("|") + 1));
    }

    public static String getNameWithoutPrice(String s){
        s = s.replace("$", "");
        return s.substring(0, s.indexOf("|"));
    }
}
