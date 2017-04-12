package com.guanyin.sardar.pha.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.guanyin.sardar.pha.R;
import com.guanyin.sardar.pha.utils.Const;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class CircleView extends View {

    List<SmartPhoneOS> smartPhoneOSes;
    Paint mPaint = new Paint();
    RectF rectF = new RectF(100, 100, 700, 700);
    //    int[] colors = new int[]{R.color.first, R.color.forth, R.color.seventh, R.color.twelfth};
    int[] colors = new int[]{0xFFf47d00, 0Xff663ab5, 0Xff89c348, 0Xffc11859};

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆弧
        int i = 0;
        int startAngle = 0;
        for (SmartPhoneOS sp : smartPhoneOSes
                ) {
            mPaint.setColor(colors[i++]);
            if (sp.getName().equals("other")) {
                canvas.drawArc(rectF, startAngle, 360 - startAngle, true, mPaint);
            } else {
                canvas.drawArc(rectF, startAngle, sp.getAngle(), true, mPaint);
            }
            Const.log("angle", sp.getAngle() + "");
            startAngle += sp.getAngle();
        }
    }

    // 暴露设置数据的方法
    public void setData(List<SmartPhoneOS> smartPhoneOSes) {
        this.smartPhoneOSes = smartPhoneOSes;
        // 进行比例的确认
        float count = 0;
        for (SmartPhoneOS sp : smartPhoneOSes
                ) {
            count += sp.getCount();
        }
        for (SmartPhoneOS sp : smartPhoneOSes
                ) {
            sp.setAngle((sp.getCount() / count) * 360);
        }

    }

}
