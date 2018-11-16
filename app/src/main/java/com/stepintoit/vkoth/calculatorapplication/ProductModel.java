package com.stepintoit.vkoth.calculatorapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable {

    private String productId;
    private String productName;
    private String productDescription;
    private ArrayList<String> productImage;
    private String phoneNumber;
    private String webAddress;
    private int price;
    private ArrayList<String> tags;
    private DimensionsModel dimensionsModel;
    private WarehouseLocationModel warehouseLocationModel;

    private boolean favouriteFlag = false;

    public boolean isFavouriteFlag() {
        return favouriteFlag;
    }

    public void setFavouriteFlag(boolean favouriteFlag) {
        this.favouriteFlag = favouriteFlag;
    }


    public DimensionsModel getDimensionsModel() {
        return dimensionsModel;
    }

    public void setDimensionsModel(DimensionsModel dimensionsModel) {
        this.dimensionsModel = dimensionsModel;
    }

    public WarehouseLocationModel getWarehouseLocationModel() {
        return warehouseLocationModel;
    }

    public void setWarehouseLocationModel(WarehouseLocationModel warehouseLocationModel) {
        this.warehouseLocationModel = warehouseLocationModel;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getProductImage() {
        return productImage;
    }

    public void setProductImage(ArrayList<String> productImage) {
        this.productImage = productImage;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
