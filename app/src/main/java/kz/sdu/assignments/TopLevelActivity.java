package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopLevelActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] categories = CategoryAndProduct.categories;

    public static String EXTRA_CATEGORY = "kz.sdu.assignments.EXTRA_CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplevel);

        List<String> categoriesList = Arrays.asList(categories);

        ListView categories = findViewById(R.id.categories);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoriesList);
        categories.setAdapter(categoryAdapter);
        categories.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CategoryLevelActivity.class);
        intent.putExtra(EXTRA_CATEGORY, position);
        startActivity(intent);
    }
}
