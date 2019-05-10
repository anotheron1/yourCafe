package com.example.yourcafe.ui.cafeCatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.yourcafe.R;
import com.example.yourcafe.ui.caffeClientMenu.ccmActivity;

import java.util.ArrayList;
import java.util.List;

public class CatalogueActivity extends AppCompatActivity {

    private List<Caffe> caffe, caffeFav;
    private RecyclerView rv, rvFav;
    private TabHost tabHost;
    private CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        setupTab(getString(R.string.action_sign_in), R.id.rv);
        setupTab(getString(R.string.registration_act_name), R.id.rvFav);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        rv=(RecyclerView)findViewById(R.id.rv);
        rvFav=(RecyclerView)findViewById(R.id.rvFav);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        LinearLayoutManager llmFav = new LinearLayoutManager(this);
        rvFav.setLayoutManager(llmFav);
        rvFav.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void setupTab(String title, int id) {
        TabHost.TabSpec spec = tabHost.newTabSpec(title);
        spec.setContent(id);
        spec.setIndicator(title);
        tabHost.addTab(spec);
    }

    private void initializeData(){
        caffe = new ArrayList<>();
        caffe.add(new Caffe("6/4", "Череповец, ул. Ленина 35", "Чай, Кофе", R.mipmap.sfcaffe));
        caffe.add(new Caffe("Енот Лиса", "Череповец, Советский пр. 30Б", "Чай, Кофе", R.mipmap.el));
        caffe.add(new Caffe("ЧайБар", "Череповец, Советский пр. 43", "Чай", R.mipmap.tb));

        caffeFav = new ArrayList<>();
        caffeFav.add(new Caffe("Енот Лиса", "Череповец, Советский пр. 30Б", "Чай, Кофе", R.mipmap.el));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(caffe);
        rv.setAdapter(adapter);
        RVAdapter adapterFav = new RVAdapter(caffeFav);
        rvFav.setAdapter(adapterFav);
    }
}
