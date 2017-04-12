package com.guanyin.sardar.pha.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.guanyin.sardar.pha.R;


public class MyView extends View {

    private Paint mPaint = new Paint();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制颜色
//        canvas.drawColor(Color.BLUE);
        // 绘制点
//        canvas.drawPoint(200, 200, mPaint);
        // 绘制直线
//        canvas.drawLine(300, 300, 500, 600, mPaint);
        // 绘制矩形  共三种  左上角和右下角
//        canvas.drawRect(100, 100, 80, 400, mPaint);

//        Rect rect = new Rect(300, 300, 600, 700);
//        canvas.drawRect(rect, mPaint);

        // 绘制圆角矩形
//        RectF rectF = new RectF(400, 500, 800, 600);
//        canvas.drawRoundRect(rectF, 30, 30, mPaint);

        // 绘制椭圆
//        canvas.drawOval(rectF,mPaint);

        // 绘制圆
//        canvas.drawCircle(800,800,400,mPaint);

        // 绘制圆弧
//        canvas.drawArc();

        // 画布的位移

//        canvas.drawCircle(200,200,100,mPaint);
//
//        canvas.translate(200,200);
//        canvas.drawCircle(200,200,100,mPaint);
//
//        canvas.translate(200,200);
//        canvas.drawCircle(200,200,100,mPaint);

        // 画布的缩放
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(3);
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        canvas.drawRect(400, 0, 0, 300, mPaint);

//        mPaint.setColor(Color.RED);
//        canvas.scale(0.5f, 0.5f);
//        canvas.drawRect(400, 0, 0, 300, mPaint);

//        mPaint.setColor(Color.BLACK);
//        canvas.scale(1.5f, 1.5f, 200,0 );
//        canvas.drawRect(400, 0, 0, 300, mPaint);

        // 画布的旋转
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        canvas.drawRect(300, 0, 0, 400, mPaint);

//        canvas.rotate(180);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(300, 0, 0, 400, mPaint);

//        canvas.rotate(180, 150, 0);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(300, 0, 0, 400, mPaint);


        super.onDraw(canvas);
    }
}
