package com.example.deepanshu.inventoryapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepanshu.inventoryapp.MainActivity;
import com.example.deepanshu.inventoryapp.R;
import com.example.deepanshu.inventoryapp.db.ShopContract;

/**
 * Created by deepanshu on 4/4/17.
 */

public class ShopCursorAdapter extends CursorAdapter {

    private MainActivity mainActivity;

    public ShopCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.mainActivity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        ImageView sale = (ImageView) view.findViewById(R.id.sale);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        String name = cursor.getString(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_PRICE));

        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(ShopContract.ShopEntry.COLUMN_IMAGE))));

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(ShopContract.ShopEntry._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onClickItem(id);
            }
        });

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onClickSale(id, quantity);
            }
        });
    }
}
