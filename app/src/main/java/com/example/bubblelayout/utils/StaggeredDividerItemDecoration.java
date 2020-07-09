package com.example.bubblelayout.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class StaggeredDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int interval;

    public StaggeredDividerItemDecoration(Context context, int interval) {
        this.context = context;
        this.interval = interval;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
//        int interval = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                this.interval, context.getResources().getDisplayMetrics());
//        if (position == 0 || position == 1) {
//            outRect.top = interval;
//        }
        outRect.top = interval;
        // 中间间隔
        if (position % 2 == 0) {
            outRect.left = interval;
            outRect.right = interval;
        } else {
            // item为奇数位，设置其左间隔为5dp
            outRect.right = interval;
            outRect.left = 0;
        }
        // 下方间隔
        outRect.bottom = interval;
    }
}
