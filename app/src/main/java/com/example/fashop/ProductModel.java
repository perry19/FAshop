package com.example.fashop;


public class ProductModel {

    private String productImage;
    private String price;
    private String productName;
    private String location;
    private String seller;

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public ProductModel() {
    }

    public ProductModel(String productImage, String price, String location, String productName,String seller) {
        this.productImage = productImage;
        this.price = price;
        this.location = location;
        this.productName = productName;

        this.seller = seller;
    }


}
