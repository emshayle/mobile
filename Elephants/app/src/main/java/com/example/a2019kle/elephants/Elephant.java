package com.example.a2019kle.elephants;

import java.util.Objects;

/**
 * Created by 2019kle on 10/23/2017.
 */

public class Elephant{
    private String name;
    private int favorite_number;

    public Elephant (String n){
        name = n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase();
        favorite_number= (int)(Math.random()*1000 +1);
    }

    public void evolve(){
        favorite_number= (int)(Math.random()*1000 +1);
    }

    public String getName() {
        return name;
    }

    public int getFavorite_number() {
        return favorite_number;
    }

    public void setFavorite_number(int favorite_number) {
        this.favorite_number = favorite_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name + "'s fave number is: " + favorite_number;
    }
}
