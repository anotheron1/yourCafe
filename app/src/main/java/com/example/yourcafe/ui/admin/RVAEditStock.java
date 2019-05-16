package com.example.yourcafe.ui.admin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yourcafe.R;
import com.example.yourcafe.ui.caffeClientMenu.Stock;

import java.util.List;

public class RVAEditStock extends RecyclerView.Adapter<RVAEditStock.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView stockName;
        TextView stockDate;
        ImageView stockPhoto;
        TextView stockDescription;
        Button editBtn;

        ViewHolder(View v) {
            super(v);
            cv = (CardView)v.findViewById(R.id.cv_edit_stock);
            stockName = (TextView)v.findViewById(R.id.edit_stock_name);
            stockDate = (TextView)v.findViewById(R.id.edit_stock_date);
            stockPhoto = (ImageView)v.findViewById(R.id.edit_stock_photo);
            stockDescription = (TextView)v.findViewById(R.id.edit_stock_description);
        }
    }

    List<Stock> stock;

    RVAEditStock(List<Stock> stock){
        this.stock = stock;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RVAEditStock.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_edit_stock, viewGroup, false);
        RVAEditStock.ViewHolder vh = new RVAEditStock.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RVAEditStock.ViewHolder viewHolder, int i) {
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
