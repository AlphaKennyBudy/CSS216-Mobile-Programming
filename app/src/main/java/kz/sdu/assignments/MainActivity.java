package kz.sdu.assignments;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridLayout imageContainer;
    private int[] images = {
            R.drawable.card_clubs2,
            R.drawable.card_clubs3,
            R.drawable.card_clubs4,
            R.drawable.card_clubs5,
            R.drawable.card_clubs6,
    };
    private HashMap<Integer, Integer> cards;
    private ArrayList<Integer> orders;
    private HashMap<Integer, CardView> cardViews;
    private int choice;
    private int[] counter;
    private boolean isStopped = false;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageContainer = findViewById(R.id.imageContainer);

        if (savedInstanceState != null) {
            this.cards = (HashMap<Integer, Integer>) savedInstanceState.getSerializable("cards");
            this.orders = (ArrayList<Integer>) savedInstanceState.getSerializable("orders");
            this.counter = savedInstanceState.getIntArray("counter");
            this.choice = savedInstanceState.getInt("choice");
            this.isStopped = savedInstanceState.getBoolean("isStopped");
        } else {
            this.choice = 1;
            this.counter = new int[]{1};
            generateCards();
    }

        spawnCards();
        showCards();

        TextView result = findViewById(R.id.result);
        result.setText(String.valueOf(this.choice - 1));

        cardViews.forEach((order, view) -> {
                    view.setOnClickListener(e -> {
                        AlertDialog.Builder alertResult = new AlertDialog.Builder(this);
                        alertResult.setTitle("End game");
                        alertResult.setCancelable(false);
                        alertResult.setPositiveButton("Try again!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                        alertResult.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        if (view.showCard(this.choice)) {
                            result.setText(String.valueOf(this.choice));
                            this.choice++;
                            if (this.choice == 10) {
                                alertResult.setMessage("You Won! Congrats!");
                                AlertDialog alertDialog = alertResult.create();
                                alertDialog.show();
                            }
                        } else {
                            alertResult.setMessage("You lost");
                            AlertDialog alertDialog = alertResult.create();
                            alertDialog.show();
                        }
                    });

                    if (view.getOrder() < this.choice) {
                        view.showCard(this.choice);
                    }
                }
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.isStopped = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.isStopped = false;
        showCards();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         AlertDialog.Builder alertResult = new AlertDialog.Builder(this);
         alertResult.setTitle("Are you sure?");
         alertResult.setMessage("We love you. Come back sooner!");
         alertResult.setCancelable(false);
         alertResult.setPositiveButton("Yeah, I'll return later", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 finish();
             }
         });
         AlertDialog alertDialog = alertResult.create();
         alertDialog.show();
    }

    public void generateCards() {
        this.cards = new HashMap<>();
        this.orders = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 9; i++) {
            this.cards.put(i, images[random.nextInt(images.length)]);
            this.orders.add(i);
        }
        Collections.shuffle(orders);
    }

    public void spawnCards() {
        cardViews = new HashMap<>();
        for (int i : orders) {
            addImageView(this.cards.get(i), i);
        }
    }

    public void showCards() {
        final Handler showCardHandler = new Handler();
        showCardHandler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (!isStopped) {
                    if (counter[0] == 10) {
                        cardViews.forEach((order, view) -> {
                            view.hideCard();
                        });
                        counter[0]++;
                    }
                    if (counter[0] < 10) {
                        cardViews.forEach((order, view) -> {
                            view.hideCard();
                        });
                        Objects.requireNonNull(cardViews.get(counter[0])).showCard(counter[0]);
                        counter[0]++;
                        showCardHandler.postDelayed(this, 2000);
                    }
                }
            }
        });
    }

    public void addImageView(int uri, int order) {
        CardView imgView = new CardView(this, uri, order);
        cardViews.put(order, imgView);
        imageContainer.addView(imgView);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("cards", this.cards);
        outState.putSerializable("orders", this.orders);
        outState.putIntArray("counter", this.counter);
        outState.putInt("choice", this.choice);
        outState.putBoolean("isStopper", this.isStopped);
    }
}
