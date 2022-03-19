package com.example.englishdictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
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
            "hello",
            "consider",
            "minute",
            "accord",
            "evident",
            "intend",
            "practice",
            "concern",
            "commit",
            "issue",
            "approach",
            "establish",
            "utter",
            "conduct",
            "engage",
            "obtain",
            "scarce",
            "policy",
            "stock",
            "concept",
            "court",
            "experience",
            "subject",
            "appoint",
            "vain",
            "project",
            "commission",
            "coast",
            "post",
            "level",
            "render",
            "appeal",
            "dwell",
            "grant",
            "entertain",
            "inspire",
            "skill",
            "convention",
            "furnish",
            "novel",
            "undertake",
            "majority",
            "intimidate",
            "mien",
            "alacrity",
            "seethe",
            "scrutinize",
            "diffident",
            "pique",
            "expiate"
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
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });


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
        intent.putExtra("word", this.randomWord);
        startActivity(intent);
    }

    private void openHistoryActivity() {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(HomePage.this, about.class));
                return true;
            case R.id.notes:
                Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}