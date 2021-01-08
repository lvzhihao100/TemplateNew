package com.gamerole.common.box.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DividerLineItemDecoration extends RecyclerView.ItemDecoration {


    //分割线画笔
    private Paint mPaint;

    //分割线距离左边距离
    private int leftPadding = 0;
    //分割线距离右边距离
    private int rightPadding = 0;

    private boolean isFirstDraw = true;
    private boolean isLastDraw = true;
    private int paintWidth = 1;
    private int paintColor = 0xfff3f3f3;
    private boolean isSecondToLastDraw = true;

    public DividerLineItemDecoration setPaintWidth(int paintWidth) {
        this.paintWidth = paintWidth;
//        mPaint.setStrokeWidth(paintWidth);
        return this;
    }

    public DividerLineItemDecoration setPaintColor(int paintColor) {
        this.paintColor = paintColor;
        mPaint.setColor(paintColor);
        return this;
    }

    public DividerLineItemDecoration setFirstDraw(boolean firstDraw) {
        isFirstDraw = firstDraw;
        return this;
    }

    public DividerLineItemDecoration setLastDraw(boolean lastDraw) {
        isLastDraw = lastDraw;
        return this;
    }

    public DividerLineItemDecoration setSecondToLastDraw(boolean secondToLastDraw) {
        isSecondToLastDraw = secondToLastDraw;
        return this;
    }

    public DividerLineItemDecoration setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
        return this;
    }

    public DividerLineItemDecoration setRightPadding(int rightPadding) {
        this.rightPadding = rightPadding;
        return this;
    }

    public DividerLineItemDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setStrokeWidth(paintWidth);
        mPaint.setColor(paintColor);
        mPaint.setAntiAlias(true);
    }

    public DividerLineItemDecoration setDividerColor(int dividerColor) {
        mPaint.setColor(dividerColor);
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            drawTop(c, parent, left, right, view, position);
            drawBottomLine(c, parent, left, right, view, position);
        }
    }

    /**
     * 绘制下线
     *
     * @param c
     * @param parent
     * @param left
     * @param right
     * @param view
     * @param position
     */
    private void drawBottomLine(Canvas c, RecyclerView parent, int left, int right, View view, int position) {
        //控制最后一个下线绘制
        if (isLastDraw && parent.getAdapter().getItemCount() == position + 1) {
            c.drawRect(left + leftPadding, view.getBottom(), right - rightPadding, view.getBottom() + paintWidth, mPaint);
        }
    }

    /**
     * 绘制上线
     *
     * @param c
     * @param parent
     * @param left
     * @param right
     * @param view
     * @param position
     */
    private void drawTop(Canvas c, RecyclerView parent, int left, int right, View view, int position) {
        //控制第一个上线不绘制
        if (!isFirstDraw && position == 0) {
            return;
        }

        //控制最后一个上线不绘制
        if (!isSecondToLastDraw && parent.getAdapter().getItemCount() == position + 1) {
            return;
        }

        c.drawRect(left + leftPadding, view.getTop() - paintWidth, right - rightPadding, view.getTop(), mPaint);
    }

    /**
     * 设置item分割线的size
     *
     * @param outRect outRect
     * @param view    view
     * @param parent  parent
     * @param state   state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        outRect.set(0, !isFirstDraw && position == 0 ? 0 : paintWidth, 0, isLastDraw && parent.getAdapter().getItemCount() == position + 1 ? paintWidth : 0);

    }
}