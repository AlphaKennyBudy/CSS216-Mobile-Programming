package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String name = getIntent().getStringExtra(CategoryLevelActivity.EXTRA_PRODUCT_NAME);
        int price = getIntent().getIntExtra(CategoryLevelActivity.EXTRA_PRODUCT_PRICE, 0);
        int img = getIntent().getIntExtra(CategoryLevelActivity.EXTRA_PRODUCT_IMAGE, R.drawable.akatsuki);

        ImageView pi = findViewById(R.id.product_image);
        pi.setImageResource(img);

        TextView pn = findViewById(R.id.product_name);
        pn.setText(name);

        TextView pr = findViewById(R.id.product_price);
        pr.setText(String.format("KZT %d", price));
    }
}