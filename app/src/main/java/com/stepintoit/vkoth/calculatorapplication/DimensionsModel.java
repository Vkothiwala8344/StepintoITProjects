package com.stepintoit.vkoth.calculatorapplication;

import java.io.Serializable;

public class DimensionsModel implements Serializable{

    private float length;
    private float width;
    private float height;

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
