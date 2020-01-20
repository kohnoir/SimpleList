package com.example.simplelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.zone_list);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;
        String Name = "Name";
        String Description = "Description";

        map = new HashMap<>();
        map.put(Name,"Сергей");
        map.put(Description,"Почему я сам должен искать инфу ?");
        arrayList.add(map);

        map = new HashMap<>();
        map.put(Name,"Андрей");
        map.put(Description,"А ты все равно уже заплатил,да и курс к концу подходит");
        arrayList.add(map);


        SimpleAdapter adapter = new SimpleAdapter(this ,arrayList , R.layout.simple_list ,
                new String[]{Name,Description},
                new int[]{R.id.firstTxt,R.id.secondTxt});
        listView.setAdapter(adapter);
    }
}
