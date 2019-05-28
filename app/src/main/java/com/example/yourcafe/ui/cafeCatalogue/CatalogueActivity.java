package com.example.yourcafe.ui.cafeCatalogue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;
import android.widget.TextView;
import com.example.yourcafe.R;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CatalogueActivity extends AppCompatActivity {

    private List<Caffe> caffe, caffeFav;
    private List<CaffeData> cData;
    private RecyclerView rv, rvFav;
    private TabHost tabHost;
    private String response;
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        cData = new ArrayList<>();

        GetReq req = new GetReq();
        try {
            response = req.run("https://yourcaffeweb.herokuapp.com/Catalogue");
//            Reader reader = new StringReader(response);
            cData = mapper.readValue(response, new TypeReference<List<CaffeData>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }



        tabHost = findViewById(R.id.tabHostCvt);
        tabHost.setup();
        setupTab(getString(R.string.ctg_ctg), R.id.rvCvt);
        setupTab(getString(R.string.ctg_fav), R.id.rvFav);
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        rv=(RecyclerView)findViewById(R.id.rvCvt);
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
        for (int i = 0; i < cData.size(); i++) {
            String name, address, assortment;
            name = cData.get(i).getName();
            address = cData.get(i).getAddress();
            assortment = cData.get(i).getAssortment();

            caffe.add(new Caffe(name, address, assortment, R.mipmap.sfcaffe));
        }
//        caffe.add(new Caffe("6/4", "Череповец, ул. Ленина 35", "Чай, Кофе", R.mipmap.sfcaffe));
//        caffe.add(new Caffe("Енот Лиса", "Череповец, Советский пр. 30Б", "Чай, Кофе", R.mipmap.el));
//        caffe.add(new Caffe("ЧайБар", "Череповец, Советский пр. 43", "Чай", R.mipmap.tb));

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
