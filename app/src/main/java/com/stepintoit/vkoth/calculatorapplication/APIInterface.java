package com.stepintoit.vkoth.calculatorapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

interface APIInterface {

    @GET("/Vkothiwala8344/StepintoITProjects/productData")
    Call<ArrayList<ProductModel>> getProductList();


}
