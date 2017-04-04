package com.example.deepanshu.inventoryapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deepanshu.inventoryapp.model.ShopItem;

/**
 * Created by deepanshu on 4/4/17.
 */

public class ShopHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "shopinventory.db";
    public final static int DB_VERSION = 2;

    public ShopHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShopContract.ShopEntry.CREATE_TABLE_SHOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(ShopItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ShopContract.ShopEntry.COLUMN_NAME, item.getProductName());
        values.put(ShopContract.ShopEntry.COLUMN_PRICE, item.getPrice());
        values.put(ShopContract.ShopEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(ShopContract.ShopEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(ShopContract.ShopEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(ShopContract.ShopEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(ShopContract.ShopEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(ShopContract.ShopEntry.TABLE_NAME, null, values);
    }

    public Cursor readShop() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                ShopContract.ShopEntry._ID,
                ShopContract.ShopEntry.COLUMN_NAME,
                ShopContract.ShopEntry.COLUMN_PRICE,
                ShopContract.ShopEntry.COLUMN_QUANTITY,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_NAME,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_PHONE,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_EMAIL,
                ShopContract.ShopEntry.COLUMN_IMAGE
        };
        return db.query(ShopContract.ShopEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                ShopContract.ShopEntry._ID,
                ShopContract.ShopEntry.COLUMN_NAME,
                ShopContract.ShopEntry.COLUMN_PRICE,
                ShopContract.ShopEntry.COLUMN_QUANTITY,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_NAME,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_PHONE,
                ShopContract.ShopEntry.COLUMN_SUPPLIER_EMAIL,
                ShopContract.ShopEntry.COLUMN_IMAGE
        };
        String selection = ShopContract.ShopEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};

        return db.query(ShopContract.ShopEntry.TABLE_NAME, projection, selection,
                selectionArgs, null, null, null);
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ShopContract.ShopEntry.COLUMN_QUANTITY, quantity);
        String selection = ShopContract.ShopEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(currentItemId)};
        db.update(ShopContract.ShopEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void orderOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity - 1;
        }
        ContentValues values = new ContentValues();
        values.put(ShopContract.ShopEntry.COLUMN_QUANTITY, newQuantity);
        String selection = ShopContract.ShopEntry._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};
        db.update(ShopContract.ShopEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
