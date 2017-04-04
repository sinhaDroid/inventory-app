package com.example.deepanshu.inventoryapp.db;

import android.provider.BaseColumns;

/**
 * Created by deepanshu on 4/4/17.
 */

public class ShopContract {

    public ShopContract() {
    }

    public static final class ShopEntry implements BaseColumns {

        public static final String TABLE_NAME = "shop";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "shop_name";
        public static final String COLUMN_SUPPLIER_PHONE = "shop_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "shop_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_SHOP = "CREATE TABLE " +
                ShopContract.ShopEntry.TABLE_NAME + "(" +
                ShopContract.ShopEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ShopContract.ShopEntry.COLUMN_NAME + " TEXT NOT NULL," +
                ShopContract.ShopEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                ShopContract.ShopEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                ShopContract.ShopEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                ShopContract.ShopEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                ShopContract.ShopEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                ShopEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
