package com.stepintoit.vkoth.calculatorapplication.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "stepintoit_db";
    public static final String TABLE_PRODUCT = "product_info";
    public static final String TABLE_IMAGES = "images_links";
    public static final String TABLE_TAGS = "tags";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String DESCRIPTION = "description";
    public static final String WEIGHT = "weight";
    public static final String IMAGE_ID = "image_id";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String WEB_ADDRESS = "web_address";
    public static final String PRICE = "price";
    public static final String TAG_ID = "tag_id";
    public static final String LENGTH = "length";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String COLUMN_IMG_ID = "image_id";
    public static final String COLUMN_IMG_LINK = "link";
    public static final String COLUMN_TAG_ID = "tag_id";
    public static final String COLUMN_TAG = "tags";



    private static final String CREATE_TABLE =
            "create table product_info (product_id varchar(10) primary key, product_name varchar(20),description varchar(50),weight varchar(10),phone_number varchar(20)," +
                    "web_address varchar(50), price Integer, length double, width double, height double, latitude double, longitude double, image_id integer, tag_id integer )";

    private static final String CREATE_IMAGE =
            "create table images_links (image_id integer,link varchar(100))";

    private static final String CREATE_TAGS =
            "create table tags (tag_id integer,tags varchar(100))";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_IMAGE);
            db.execSQL(CREATE_TAGS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
