package com.example.yourcafe.ui.cafeCatalogue;

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
import com.example.yourcafe.ui.caffeClientMenu.ccmActivity;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView caffeName;
        TextView caffeAddress;
        TextView caffeAssortment;
        ImageView caffePhoto;
        Button caffeButton;
        public Caffe currentCaffe;

        ViewHolder(View v) {
            super(v);

            cv = (CardView)v.findViewById(R.id.cv);
            caffeName = (TextView)v.findViewById(R.id.caffe_name);
            caffeAddress = (TextView)v.findViewById(R.id.caffe_address);
            caffeAssortment = (TextView)v.findViewById(R.id.caffe_assortment);
            caffePhoto = (ImageView)v.findViewById(R.id.caffe_photo);
            caffeButton = (Button)v.findViewById(R.id.buttonCtg);
            caffeButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ccmActivity.class);
                    intent.putExtra("caffeName", caffeName.getText());
                    context.startActivity(intent);
                }
            });
        }
    }

    List<Caffe> caffe;

    RVAdapter(List<Caffe> caffe){
        this.caffe = caffe;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.caffeName.setText(caffe.get(i).name);
        viewHolder.caffeAddress.setText(caffe.get(i).address);
        viewHolder.caffeAssortment.setText(caffe.get(i).assortment);
        viewHolder.caffePhoto.setImageResource(caffe.get(i).photoId);
        viewHolder.currentCaffe = caffe.get(i);
    }

    @Override
    public int getItemCount() {
        return caffe.size();
    }
}