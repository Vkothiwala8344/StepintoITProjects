
package com.stepintoit.vkoth.calculatorapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dimensions {

    @SerializedName("length")
    @Expose
    private float length;
    @SerializedName("width")
    @Expose
    private float width;
    @SerializedName("height")
    @Expose
    private Double height;

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

}
