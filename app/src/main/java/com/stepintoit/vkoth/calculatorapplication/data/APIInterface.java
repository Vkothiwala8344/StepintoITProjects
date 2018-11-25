package com.stepintoit.vkoth.calculatorapplication.data;

import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/Vkothiwala8344/StepintoITProjects/productData")
    Observable<ArrayList<ProductModel>> getProductList();


}
