package com.example.deepanshu.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.deepanshu.inventoryapp.adapter.ShopCursorAdapter;
import com.example.deepanshu.inventoryapp.db.ShopHelper;
import com.example.deepanshu.inventoryapp.detail.DetailActivity;
import com.example.deepanshu.inventoryapp.model.ShopItem;

public class MainActivity extends AppCompatActivity {

    ShopHelper shopHelper;
    int lastVisibleItem = 0;
    private ShopCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shopHelper = new ShopHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add dummy data for testing
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = shopHelper.readShop();

        adapter = new ShopCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void addDummyData() {
        ShopItem gummibears = new ShopItem(
                "Gummibears",
                "10 €",
                45,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/gummibear");
        shopHelper.insertItem(gummibears);

        ShopItem peaches = new ShopItem(
                "Peaches",
                "10 €",
                24,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/peach");
        shopHelper.insertItem(peaches);

        ShopItem cherries = new ShopItem(
                "Cherries",
                "11 €",
                74,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/cherry");
        shopHelper.insertItem(cherries);

        ShopItem cola = new ShopItem(
                "Cola",
                "13 €",
                44,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/cola");
        shopHelper.insertItem(cola);

        ShopItem fruitSalad = new ShopItem(
                "Fruit salad",
                "20 €",
                34,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/fruit_salad");
        shopHelper.insertItem(fruitSalad);

        ShopItem smurfs = new ShopItem(
                "Smurfs",
                "12 €",
                26,
                "Haribo GmbH",
                "+49 000 000 0000",
                "haribo@sweet.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/smurfs");
        shopHelper.insertItem(smurfs);

        ShopItem fresquito = new ShopItem(
                "Fresquito",
                "9 €",
                54,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/fresquito");
        shopHelper.insertItem(fresquito);

        ShopItem hotChillies = new ShopItem(
                "Hot chillies",
                "13 €",
                12,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/hot_chillies");
        shopHelper.insertItem(hotChillies);

        ShopItem lolipopStrawberry = new ShopItem(
                "Lolipop strawberry",
                "12 €",
                62,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/lolipop");
        shopHelper.insertItem(lolipopStrawberry);

        ShopItem heartGummy = new ShopItem(
                "Heart gummy jellies",
                "13 €",
                22,
                "Fiesta S.A.",
                "+34 000 000 0000",
                "fiesta@dulce.com",
                "android.resource://com.example.deepanshu.inventoryapp/drawable/heart_gummy");
        shopHelper.insertItem(heartGummy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_dummy) {

            // add dummy data for testing
            addDummyData();
            adapter.swapCursor(shopHelper.readShop());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(shopHelper.readShop());
    }

    public void onClickItem(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void onClickSale(long id, int quantity) {
        shopHelper.orderOneItem(id, quantity);
        adapter.swapCursor(shopHelper.readShop());
    }
}
