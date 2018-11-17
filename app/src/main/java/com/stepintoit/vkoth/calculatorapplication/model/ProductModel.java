
package com.stepintoit.vkoth.calculatorapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel implements Serializable {

    @SerializedName("productId")
    @Expose
    private String productId;

    public boolean isFavouriteFlag() {
        return favouriteFlag;
    }

    public void setFavouriteFlag(boolean favouriteFlag) {
        this.favouriteFlag = favouriteFlag;
    }

    @SerializedName("favouriteFlag")
    @Expose
    private boolean favouriteFlag = false;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("images")
    @Expose
    private ArrayList<String> images = null;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags = null;
    @SerializedName("dimensions")
    @Expose
    private Dimensions dimensions;
    @SerializedName("warehouseLocation")
    @Expose
    private WarehouseLocation warehouseLocation;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public WarehouseLocation getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(WarehouseLocation warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

}
