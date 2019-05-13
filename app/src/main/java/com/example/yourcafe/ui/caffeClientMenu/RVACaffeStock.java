package com.example.yourcafe.ui.caffeClientMenu;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yourcafe.R;

import java.util.List;

public class RVACaffeStock extends RecyclerView.Adapter<RVACaffeStock.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView stockName;
        TextView stockDate;
        ImageView stockPhoto;
        TextView stockDescription;

        public Stock currentCaffe;

        ViewHolder(View v) {
            super(v);
            cv = (CardView)v.findViewById(R.id.cv_caffe_stock);
            stockName = (TextView)v.findViewById(R.id.stock_name);
            stockDate = (TextView)v.findViewById(R.id.stock_date);
            stockPhoto = (ImageView)v.findViewById(R.id.stock_photo);
            stockDescription = (TextView)v.findViewById(R.id.stock_description);
        }
    }

    List<Stock> stock;

    RVACaffeStock(List<Stock> stock){
        this.stock = stock;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVACaffeStock.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_caffe_stock, viewGroup, false);
        RVACaffeStock.ViewHolder vh = new RVACaffeStock.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RVACaffeStock.ViewHolder viewHolder, int i) {
        viewHolder.stockName.setText(stock.get(i).sName);
        viewHolder.stockDate.setText(stock.get(i).date);
        viewHolder.stockPhoto.setImageResource(stock.get(i).sPhotoId);
        viewHolder.stockDescription.setText(stock.get(i).description);

    }

    @Override
    public int getItemCount() {
        return stock.size();
    }

}
