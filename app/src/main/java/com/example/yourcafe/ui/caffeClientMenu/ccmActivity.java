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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ccmActivity extends AppCompatActivity {

    private TabHost tabHostCcm;
    private RecyclerView rvStock;
    private List<Stock> stock;
    int caffe_id;
    String client_id;
    String response;
    final ObjectMapper mapper = new ObjectMapper();
    List<Menu> menuData = new ArrayList<>();
    GetMenu req = new GetMenu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffe_client_menu);
        caffe_id = getIntent().getIntExtra("caffe_id", caffe_id);
        client_id = getIntent().getStringExtra("client_id");
        try {
            response = "[" + req.run("https://yourcaffeweb.herokuapp.com/Interaction/GetMenu?caffe_id=" + caffe_id + "&client_id=" + client_id) + "]";
            if (response.equals("[null]")) {
                response = "[{\"id\":1,\"client_id\":\"1234567890\",\"client_qr\":null,\"caffe_id\":\"1\",\"all_cup\":\"7\",\"fill_cup\":\"3\"}]";
            }
            menuData = mapper.readValue(response, new TypeReference<List<Menu>>() {});
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
//        Toast.makeText(getApplicationContext(), caffeName, Toast.LENGTH_LONG).show();
//        Toolbar toolbar = findViewById(R.id.toolbar_stock1);
        //получаем параметры клиента (куэр, айди, количество чашек), парсим, отрисовываем
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
        fill();
    }

    public void fill(){
        String all_cup = null, fill_cup = "0", qr;
        for (int g = 0; g < menuData.size(); g++) {

            all_cup = menuData.get(g).getAll_cup();
            fill_cup = menuData.get(g).getFill_cup();
            qr = menuData.get(g).getClient_qr();
        }
        switch (fill_cup) {
            case "0":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "1":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "2":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "3":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "4":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "5":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.unfill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "6":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.unfill_cup);
                break;
            case "7":
                tabHostCcm.getCurrentView().findViewById(R.id.imageView6).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView7).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView8).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView9).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView10).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView11).setBackgroundResource(R.drawable.fill_cup);
                tabHostCcm.getCurrentView().findViewById(R.id.imageView12).setBackgroundResource(R.drawable.fill_cup);
                break;
            default:
                break;
        }
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