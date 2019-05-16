package com.example.yourcafe.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.yourcafe.R;
import com.example.yourcafe.ui.caffeClientMenu.Stock;

import java.util.ArrayList;
import java.util.List;

public class CabinetActivity extends AppCompatActivity {
    private List<CardViewType> cvt;
    private List<Stock> stock;
    private RecyclerView rvCvt, rvCvt2;
    private TabHost tabHostCvt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tabHostCvt = findViewById(R.id.tabHostCvt);
        tabHostCvt.setup();
        setupTab(getString(R.string.cab_edit), R.id.rvCvt);
        setupTab(getString(R.string.cab_edit_stock), R.id.rv_edit_stock);
        for (int i = 0; i < tabHostCvt.getTabWidget().getChildCount(); i++) {
            TextView tv = tabHostCvt.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        rvCvt=(RecyclerView)findViewById(R.id.rvCvt);
        rvCvt2=(RecyclerView)findViewById(R.id.rv_edit_stock);

        LinearLayoutManager llmCvt = new LinearLayoutManager(this);
        rvCvt.setLayoutManager(llmCvt);
        rvCvt.setHasFixedSize(true);
        LinearLayoutManager llmCvt2 = new LinearLayoutManager(this);
        rvCvt2.setLayoutManager(llmCvt2);
        rvCvt2.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupTab(String title, int id) {
        TabHost.TabSpec spec = tabHostCvt.newTabSpec(title);
        spec.setContent(id);
        spec.setIndicator(title);
        tabHostCvt.addTab(spec);
    }

    private void initializeData(){
        cvt = new ArrayList<>();
        cvt.add(new CardViewTextImage(getString(R.string.cab_edit_cupoun), R.mipmap.sfcaffe, getString(R.string.cab_edit_descr)));
        cvt.add(new CardViewText(getString(R.string.cab_edit_text), getString(R.string.cab_edit_condition), getString(R.string.cab_edit_restriction)));

        stock = new ArrayList<>();
        stock.add(new Stock("Первый заказ бесплатно!", "до 21.04.2019", "Добро пожаловать!", R.mipmap.el));
    }

    private void initializeAdapter(){
        RVACabinet adapterCvt = new RVACabinet(cvt);
        rvCvt.setAdapter(adapterCvt);
        RVAEditStock adapterCvt2 = new RVAEditStock(stock);
        rvCvt2.setAdapter(adapterCvt2);
    }

    public void addStock(View view) {
        Intent intent = new Intent(CabinetActivity.this, AddStockActivity.class);
        startActivity(intent);
    }

    public void edtStcBttn(View view) {
        CustomDialogClass cdd=new CustomDialogClass(CabinetActivity.this);
        cdd.show();
    }

}
