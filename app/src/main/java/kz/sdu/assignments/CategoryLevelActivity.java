package kz.sdu.assignments;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryLevelActivity extends ListActivity {
    private CategoryAndProduct cap;
    private int categoryId;

    public static String EXTRA_PRODUCT_NAME = "kz.sdu.assignments.EXTRA_PRODUCT_NAME";
    public static String EXTRA_PRODUCT_PRICE = "kz.sdu.assignments.EXTRA_PRODUCT_PRICE";
    public static String EXTRA_PRODUCT_IMAGE = "kz.sdu.assignments.EXTRA_PRODUCT_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cap = new CategoryAndProduct();
        categoryId = getIntent().getIntExtra(TopLevelActivity.EXTRA_CATEGORY,0);
        List<String> products = cap.getProductsName(categoryId);
        ArrayAdapter<String> productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        setListAdapter(productsAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String name = cap.getProductName(categoryId,position);
        int price = cap.getProductPrice(categoryId,position);
        int img = cap.getProductImg(categoryId,position);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_NAME, name);
        intent.putExtra(EXTRA_PRODUCT_PRICE,price);
        intent.putExtra(EXTRA_PRODUCT_IMAGE,img);
        startActivity(intent);
    }
}