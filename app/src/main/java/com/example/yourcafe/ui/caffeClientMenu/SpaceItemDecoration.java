package com.example.yourcafe.ui.caffeClientMenu;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration
{
    private int space;

    public SpaceItemDecoration(int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        //добавить переданное кол-во пикселей отступа снизу
        outRect.bottom = space;
    }
}