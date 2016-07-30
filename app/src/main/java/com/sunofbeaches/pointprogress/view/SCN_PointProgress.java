package com.sunofbeaches.pointprogress.view;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;


public class SCN_PointProgress extends Drawable {


    //点的个数
    private final int mCount;
    private final Activity mActivity;
    //画笔
    private Paint mPaint;
    //透明度
    private int mAlpha = 255;
    //动画的时间
    private int mDuration = 500;
    //默认聚焦点是第1个
    private int mFocusPoint = 0;

    //普通点的半径(单位dp)
    private int mNormalPintRadius = 3;

    //聚焦点的半径
    private int mFocusPointRadius = 5;


    //点的半径
    private float mRadius = mNormalPintRadius;


    /************************************************
     * 构造方法
     * 参数：count 点的个数
     * 异常：参数非法异常，如果点的个数少于3个则会抛出异常。
     ************************************************/
    public SCN_PointProgress(Activity activity, int count) {
        this.mActivity = activity;
        if (count < 2) {
            throw new IllegalArgumentException("只少给三个点，要么不画！");
        }


        this.mCount = count;

        //准备画笔
        mPaint = new Paint();

        //设置画笔的相关属性
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(mAlpha);
        mPaint.setAntiAlias(true);


        AnimationRunnable runnable = new AnimationRunnable();

        new Thread(runnable).start();
    }


    @Override
    public void draw(Canvas canvas) {

        Rect rect = getBounds();

        int width = rect.width();

        //计算出点的距离
        int delayWidth = width / (mCount + 1);

        //打开的话，画三个点哈！

        for (int i = 0; i < mCount; i++) {

            if (i == mFocusPoint) {
                mRadius = Utils.Dip2px(mFocusPointRadius, mActivity);
            } else {
                mRadius = Utils.Dip2px(mNormalPintRadius, mActivity);
            }

            canvas.drawCircle((i + 1) * delayWidth, rect.centerY(), mRadius, mPaint);

        }

        mFocusPoint++;


        if (mFocusPoint >= 3) {
            mFocusPoint = 0;
        }


    }

    private class AnimationRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(mDuration);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        invalidateSelf();
                    }
                });
            }
        }

    }


    @Override
    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mPaint.getAlpha();
    }


}
