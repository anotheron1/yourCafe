package com.example.yourcafe.ui.caffeClientMenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourcafe.R;

import java.util.ArrayList;
import java.util.List;

public class ccmActivity extends AppCompatActivity {

    private TabHost tabHostCcm;
    private RecyclerView rvStock;
    private List<Stock> stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffe_client_menu);
        String caffeName = getIntent().getStringExtra("caffeName");
        Toast.makeText(getApplicationContext(), caffeName, Toast.LENGTH_LONG).show();
//        Toolbar toolbar = findViewById(R.id.toolbar_stock1);

        tabHostCcm = findViewById(R.id.tabHostCcm);
        tabHostCcm.setup();
        setupTab(/*getString(R.string.caffe_menu_first_tab)*/"", R.id.containerCaffeMenu);
        setupTab(/*getString(R.string.caffe_menu_second_tab)*/"", R.id.rv_caffe_stock);
        setTabIcon(tabHostCcm, 0, R.drawable.bar_selector); //for Tab 1
        setTabIcon(tabHostCcm, 1, R.drawable.sale_selector); //for Tab 2
        for (int i = 0; i < tabHostCcm.getTabWidget().getChildCount(); i++) {
            TextView tv = tabHostCcm.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        rvStock=(RecyclerView)findViewById(R.id.rv_caffe_stock);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvStock.setLayoutManager(llm);
        rvStock.setHasFixedSize(true);
        rvStock.addItemDecoration(new SpaceItemDecoration(20));

        initializeData();
        initializeAdapter();
    }

    public void toolbarBack(View view) {
        this.finish();
    }

    public void toolbarNotify(View view) {

    }

    public void toolbarShare(View view) {

    }

    public void setTabIcon(TabHost tabHost, int tabIndex, int iconResource) {
        ImageView tabImageView = (ImageView) tabHost.getTabWidget().getChildTabViewAt(tabIndex).findViewById(android.R.id.icon);
        tabImageView.setVisibility(View.VISIBLE);
        tabImageView.setImageResource(iconResource);
    }

    private void setupTab(String title, int id) {
        TabHost.TabSpec spec = tabHostCcm.newTabSpec(title);
        spec.setContent(id);
        spec.setIndicator(title);
        tabHostCcm.addTab(spec);
    }

    private void initializeData(){
        stock = new ArrayList<>();
        stock.add(new Stock("30% скидка на Американо!", "до 20.04.2019", "У нас вкусный кофе", R.drawable.stock1));
        stock.add(new Stock("Первый заказ бесплатно!", "до 21.04.2019", "Добро пожаловать!", R.drawable.stock1));
    }

    private void initializeAdapter(){

        RVACaffeStock adapter = new RVACaffeStock(stock);
//        adapter.getItemId(toolbar);
        rvStock.setAdapter(adapter);

    }
}