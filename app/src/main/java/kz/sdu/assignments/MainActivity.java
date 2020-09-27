package kz.sdu.assignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridLayout imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageContainer = findViewById(R.id.imageContainer);
    }

    public void addImageView(View view) {
        ImageView imgView = new ImageView(this);
        int[] images = {
                R.drawable.card_back_blue1,
                R.drawable.card_back_blue2,
                R.drawable.card_back_blue3,
                R.drawable.card_back_blue4,
                R.drawable.card_back_blue5,
        };
        imgView.setBackgroundResource(images[new Random().nextInt(images.length)]);
        imageContainer.addView(imgView);
    }
}
