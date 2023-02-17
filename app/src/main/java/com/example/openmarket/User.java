package com.example.openmarket;

public class User {

    public String email,fullName,item,price,password,address;

    public User(){

    }


    public User(String email, String fullName, String item, String price,
                String password, String address) {

        this.email = email;
        this.fullName = fullName;
        this.item = item;
        this.price = price;
        this.password = password;
        this.address = address;
    }
}
