package com.example.madproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView introTextView ;
    private Button textOption2,textOption3;
    private boolean isOptionsVisible = false;

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private MyAdapter adapter;
    private List<DataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        introTextView =findViewById(R.id.introTextView);
        textOption2 = findViewById(R.id.textOption2);
        textOption3 = findViewById(R.id.textOption3);

        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DatabaseHelper(this);
        dataList = new ArrayList<>();

        // Populate the dataList with data from the database
        getDataFromDatabase();

        adapter = new MyAdapter(dataList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set an OnClickListener for the ImageView to trigger the animation
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> toggleOptionsVisibility());

        textOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getDataFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"id", "name"};

        Cursor cursor = db.query("my_table", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            dataList.add(new DataModel(id, name));
        }

        cursor.close();
    }

    private void toggleOptionsVisibility() {
        if (isOptionsVisible) {
            // Hide the options with a slide-up animation
            hideOptions();
        } else {
            // Show the options with a slide-down animation
            showOptions();
        }

        isOptionsVisible = !isOptionsVisible;
    }

    private void showOptions() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation slideDown = new TranslateAnimation(0, 0, -500, 0); // Slide down by 500 pixels
        slideDown.setDuration(500); // Animation duration in milliseconds
        animationSet.addAnimation(slideDown);

        Animation fade = new AlphaAnimation(0, 1); // Fade-in effect
        fade.setDuration(500); // Animation duration in milliseconds
        animationSet.addAnimation(fade);


        textOption2.setVisibility(View.VISIBLE);
        textOption3.setVisibility(View.VISIBLE);


        textOption2.startAnimation(animationSet);
        textOption3.startAnimation(animationSet);
    }

    private void hideOptions() {
        AnimationSet animationSet = new AnimationSet(true);
        Animation slideUp = new TranslateAnimation(0, 0, 0, -500); // Slide up by 500 pixels
        slideUp.setDuration(500); // Animation duration in milliseconds
        animationSet.addAnimation(slideUp);

        Animation fade = new AlphaAnimation(1, 0); // Fade-out effect
        fade.setDuration(500); // Animation duration in milliseconds
        animationSet.addAnimation(fade);

        textOption2.startAnimation(animationSet);
        textOption3.startAnimation(animationSet);

        textOption2.setVisibility(View.GONE);
        textOption3.setVisibility(View.GONE);
    }
    private void Answer() {

        Toast.makeText(this, "In Progress!", Toast.LENGTH_SHORT).show();
    }
}
