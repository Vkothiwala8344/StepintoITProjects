package com.stepintoit.vkoth.calculatorapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.stepintoit.vkoth.calculatorapplication.model.Dimensions;
import com.stepintoit.vkoth.calculatorapplication.model.ProductModel;
import com.stepintoit.vkoth.calculatorapplication.model.WarehouseLocation;

import java.util.ArrayList;

public class DatabaseHelper {


    private static DatabaseHelper databaseHelperInstance;
    private static SQLiteDatabase sqLiteDatabase;

    private DatabaseHelper() {
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelperInstance == null) {
            databaseHelperInstance = new DatabaseHelper();
            sqLiteDatabase = new DatabaseOpenHelper(context).getWritableDatabase();
        }
        return databaseHelperInstance;
    }

    public String retrieveDatabase() {
        Cursor cursor = sqLiteDatabase.query(DatabaseOpenHelper.TABLE_PRODUCT, null, null, null, null, null, null);
        String id = "";

        if (cursor.moveToFirst()) {
            id = cursor.getString(cursor.getColumnIndex("product_id"));

        }
        Log.d("DATABSE","status="+id);
        return id;
    }

    public void insertProduct(ArrayList<ProductModel> productList) {

        for (int i = 0; i < productList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseOpenHelper.PRODUCT_ID, productList.get(i).getProductId());
            contentValues.put(DatabaseOpenHelper.PRODUCT_NAME, productList.get(i).getName());
            contentValues.put(DatabaseOpenHelper.DESCRIPTION, productList.get(i).getDescription());
            contentValues.put(DatabaseOpenHelper.WEIGHT, productList.get(i).getWeight());
            contentValues.put(DatabaseOpenHelper.PHONE_NUMBER, productList.get(i).getPhone());
            contentValues.put(DatabaseOpenHelper.WEB_ADDRESS, productList.get(i).getWeb());
            contentValues.put(DatabaseOpenHelper.PRICE, productList.get(i).getPrice());
            contentValues.put(DatabaseOpenHelper.LENGTH, productList.get(i).getDimensions().getLength());
            contentValues.put(DatabaseOpenHelper.WIDTH, productList.get(i).getDimensions().getWidth());
            contentValues.put(DatabaseOpenHelper.HEIGHT, productList.get(i).getDimensions().getHeight());
            contentValues.put(DatabaseOpenHelper.LATITUDE, productList.get(i).getWarehouseLocation().getLatitude());
            contentValues.put(DatabaseOpenHelper.LONGITUDE, productList.get(i).getWarehouseLocation().getLongitude());
            contentValues.put(DatabaseOpenHelper.IMAGE_ID, i + 1);
            contentValues.put(DatabaseOpenHelper.TAG_ID, i + 1);
            sqLiteDatabase.insert(DatabaseOpenHelper.TABLE_PRODUCT, null, contentValues);
        }
    }

    public void insertImages(ArrayList<ProductModel> productList) {

        for (int i = 0; i < productList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            for (int a = 0; a < productList.get(i).getImages().size(); a++) {
                contentValues.put(DatabaseOpenHelper.COLUMN_IMG_ID, i + 1);
                contentValues.put(DatabaseOpenHelper.COLUMN_IMG_LINK, productList.get(i).getImages().get(a));
                sqLiteDatabase.insert(DatabaseOpenHelper.TABLE_IMAGES, null, contentValues);
            }
        }
    }

    public void insertTags(ArrayList<ProductModel> productList) {
        for (int i = 0; i < productList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            for (int a = 0; a < productList.get(i).getTags().size(); a++) {
                contentValues.put(DatabaseOpenHelper.COLUMN_TAG_ID, i + 1);
                contentValues.put(DatabaseOpenHelper.COLUMN_TAG, productList.get(i).getTags().get(a));
                sqLiteDatabase.insert(DatabaseOpenHelper.TABLE_TAGS, null, contentValues);
            }
        }
    }

    public ArrayList<ProductModel> fetchProduct() {

        ArrayList<ProductModel> productList = null;
        Cursor cursor = sqLiteDatabase.query(DatabaseOpenHelper.TABLE_PRODUCT   , null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ProductModel productModel = new ProductModel();

                String id = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PRODUCT_ID));
                productModel.setName(id);

                String name = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PRODUCT_NAME));
                productModel.setName(name);

                String desc = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.DESCRIPTION));
                productModel.setDescription(desc);

                String weight = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.WEIGHT));
                productModel.setWeight(weight);

                 String phone = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PHONE_NUMBER));
                 productModel.setPhone(phone);

                 String web = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.WEB_ADDRESS));
                 productModel.setWeb(web);

                 String price = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.PRICE));
                 productModel.setPrice(Integer.parseInt(price));

                 String length =cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.LENGTH));
                 String width=cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.WIDTH));
                 String height=cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.HEIGHT));
                Dimensions dimensions=new Dimensions();
                dimensions.setHeight(Double.parseDouble(height));
                dimensions.setLength(Float.parseFloat(length));
                dimensions.setWidth(Float.parseFloat(width));
                 productModel.setDimensions(dimensions);

                 String latitude = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.LATITUDE));
                 String longitude = cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.LONGITUDE));
                WarehouseLocation warehouseLocation = new WarehouseLocation();
                warehouseLocation.setLatitude(Double.parseDouble(latitude));
                warehouseLocation.setLatitude(Double.parseDouble(longitude));
                productModel.setWarehouseLocation(warehouseLocation);

                Integer imageId = cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.IMAGE_ID));
                Integer tagId = cursor.getInt(cursor.getColumnIndex(DatabaseOpenHelper.TAG_ID));

                Cursor imgCursor = sqLiteDatabase.rawQuery("select link from image_links where image_id = 1",null);



            } while (cursor.moveToNext());
        }
        return productList;
    }


}
