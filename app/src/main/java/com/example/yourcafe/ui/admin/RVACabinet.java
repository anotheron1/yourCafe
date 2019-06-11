package com.example.yourcafe.ui.admin;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yourcafe.R;

import java.util.List;

public class RVACabinet extends RecyclerView.Adapter {

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        CardView cv_edit_coupon;
        TextView coupon;
        TextView descr;
        ImageView photoId;


        ImageViewHolder(View v) {
            super(v);
            cv_edit_coupon = (CardView)v.findViewById(R.id.cv_edit_coupon);
            coupon = (TextView)v.findViewById(R.id.edit_name);
            descr = (TextView)v.findViewById(R.id.edit_description);
            photoId = (ImageView)v.findViewById(R.id.image_view_coupon);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        CardView cv_edit_coupon;
        TextView coupon;
        TextView descr;
        TextView condition;


        TextViewHolder(View v) {
            super(v);
            cv_edit_coupon = (CardView)v.findViewById(R.id.cv_edit_coupon2);
            coupon = (TextView)v.findViewById(R.id.edit_name2);
            descr = (TextView)v.findViewById(R.id.edit_description2);
            condition = (TextView)v.findViewById(R.id.edit_condition2);
        }
    }

    List<CardViewType> cvt;

    RVACabinet(List<CardViewType> cvt){
        this.cvt = cvt;
    }

    @Override
    public int getItemViewType(int position) {
        if (cvt.get(position) instanceof CardViewTextImage) {
            return CardViewType.IMAGE_CARD_TYPE;
        } else if (cvt.get(position) instanceof CardViewText) {
            return CardViewType.TEXT_CARD_TYPE;
        } else {
            return -1;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == CardViewType.IMAGE_CARD_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cv_edit_coupon, viewGroup, false);
            return new ImageViewHolder(view);
        }  else if (viewType == CardViewType.TEXT_CARD_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cv_edit_coupon2, viewGroup, false);
            return new TextViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ImageViewHolder) {
            ((ImageViewHolder) viewHolder).coupon
                    .setText(((CardViewTextImage) cvt.get(i)).getCoupon());
            ((ImageViewHolder) viewHolder).photoId
                    .setImageResource(((CardViewTextImage) cvt.get(i)).getPhotoId());
            ((ImageViewHolder) viewHolder).descr
                    .setText(((CardViewTextImage) cvt.get(i)).getDescr());
        } else if (viewHolder instanceof TextViewHolder) {
            ((TextViewHolder) viewHolder).coupon
                    .setText(((CardViewText) cvt.get(i)).getCoupon());
            ((TextViewHolder) viewHolder).descr
                    .setText(((CardViewText) cvt.get(i)).getDescr());
            ((TextViewHolder) viewHolder).condition
                    .setText(((CardViewText) cvt.get(i)).getCondition());
        }
    }
//    {
//        viewHolder.coupon.setText(cvt.get(i).coupon);
//        viewHolder.photoId.setImageResource(cvt.get(i).photoId);
//        viewHolder.descr.setText(cvt.get(i).descr);
//
//    }

    @Override
    public int getItemCount() {
        return cvt.size();
    }

}