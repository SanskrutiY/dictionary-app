package com.example.englishdictionary;

import android.content.Intent;
import android.database.Cursor;
import android.net.ipsec.ike.ChildSaProposal;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishdictionary.DBHelper;
import com.example.englishdictionary.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class History extends AppCompatActivity {

    ListView listView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        DB = new DBHelper(this);

        //view karayla
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            return;
        }
        
        ArrayList<String> history = new ArrayList<>();
        //Collections.sort(history, Collections.reverseOrder());


        while(res.moveToNext()){
            history.add(res.getString(0));
            //Collections.sort(history, Collections.reverseOrder());
            Collections.reverse(history);

        }

        for (String word: history) {
            //Toast.makeText(History.this, word, Toast.LENGTH_SHORT).show();
            listView = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(History.this, android.R.layout.simple_dropdown_item_1line, history);
            listView.setAdapter(adapter);
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                openMAinActivity(selectedItem);
            }
        });

        Button clearBtn = (Button) findViewById(R.id.clear_all);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deletedata("mine");
                ListView listView = (ListView) findViewById(R.id.list);
                history.clear();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(History.this, android.R.layout.simple_dropdown_item_1line, history);
                listView.setAdapter(adapter);
            }
        });


    }

    private void openMAinActivity(String historyWord) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("word", historyWord);
        startActivity(intent);
    }

}