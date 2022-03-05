package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomePage extends AppCompatActivity {
    private Button search;
    private Button historyBtn;
    private Button randomBtn;

    private String randomWord = "";

    private List<String> listOfWords = Arrays.asList(
            "bye",
            "night",
            "morning",
            "ok",
            "hello"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMAinActivity();
            }
        });

        historyBtn = (Button) findViewById((R.id.history));


        randomBtn = (Button) findViewById((R.id.random));
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int randomIndex = r.nextInt(listOfWords.size());
                randomWord = listOfWords.get(randomIndex);
//                Toast.makeText(HomePage.this, "Random: "+randomWord, Toast.LENGTH_SHORT).show();
                openMAinActivity();
            }
        });

    }

    private void openMAinActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("randomWord", this.randomWord);
        startActivity(intent);
    }
}