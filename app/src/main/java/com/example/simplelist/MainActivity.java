package com.example.simplelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.zone_list);
        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
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

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name,"Сергей");
        editor.putString(Description,"Почему я сам должен искать инфу ?");
        editor.putString(Name,"Андрей");
        editor.putString(Description,"А ты все равно уже заплатил,да и курс к концу подходит");
        editor.apply();


        final SimpleAdapter adapter = new SimpleAdapter(this ,arrayList , R.layout.simple_list ,
                new String[]{Name,Description},
                new int[]{R.id.firstTxt,R.id.secondTxt});
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
            }
        });
        final SwipeRefreshLayout swipeLayout = findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener (){
            @Override
            public void onRefresh(){
                adapter.notifyDataSetChanged();
            }
        });
    }
}
