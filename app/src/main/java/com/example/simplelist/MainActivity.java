package com.example.simplelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCES = "MyPrefs";
    SwipeRefreshLayout swipeLayout;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    SimpleAdapter adapter;
    HashMap<String, String> map;
    String[] values = prepareContent();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.zone_list);
        initSharedPreferences();

        map = new HashMap<>();
        for (int i = 0; i < prepareContent().length; i++) {
            String string = String.valueOf(i);
            map.put(string, prepareContent()[i]);
            arrayList.add(map);
        }


        BaseAdapter listContentAdapter = createAdapter(values);
        listView.setAdapter(listContentAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        swipeLayout = findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initSharedPreferences() {
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        for (int i = 0; i < prepareContent().length; i++) {
            String string = String.valueOf(i);
            sharedPreferences.edit()
                    .putString(string, prepareContent()[i]);
        }
    }

    @NonNull
    private BaseAdapter createAdapter(String[] values) {
        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
    }

    @NonNull
    private String[] prepareContent() {
        return getString(R.string.large_text).split("\n\n");
    }
}
