package com.example.openmarket;

public class StoreItem {
    private String item, fullName,address;
    private String price;

    public StoreItem(){

    }

    public StoreItem(String item, String fullName,String address, String price) {
        this.item = item;
        this.fullName = fullName;
        this.address = address;
        this.price = price;
    }



    public void setItem(String item) {
        this.item = item;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFullName() {
        return fullName;
    }


    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public String getItem() {
        return item;
    }
}
