package top.est7.muggle.CommonView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by est7.
 * <p>
 * Created Time : 2016/11/30 1:04.
 * <p>
 * Description : File in top.est7.muggle , Project in Muggle
 * <p>
 * Content:三阶贝塞尔曲线
 */

public class ThirdOrderBezier extends View {

    private Paint mPaintBezier;
    private Paint mPaintAuxiliary;
    private Paint mPaintAuxiliaryText;

    public ThirdOrderBezier(Context context) {
        super(context);
        initView();
    }

    public ThirdOrderBezier(Context context, AttributeSet attrs) {

        super(context, attrs);
        initView();
    }

    public ThirdOrderBezier(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        //Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿

        //Paint.Style.FILL：填充内部
        //Paint.Style.FILL_AND_STROKE  ：填充内部和描边
        //Paint.Style.STROKE  ：描边
        mPaintBezier.setStyle(Paint.Style.STROKE);
        mPaintBezier.setStrokeWidth(8);

        mPaintAuxiliary = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAuxiliary.setStyle(Paint.Style.STROKE);
        mPaintAuxiliary.setStrokeWidth(2);

        mPaintAuxiliaryText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAuxiliaryText.setStyle(Paint.Style.STROKE);
        mPaintAuxiliaryText.setTextSize(20);

    }

    private Path mPath = new Path();

    //曲线的两个端点
    private float mStartPointX;
    private float mStartPointY;
    private float mEndPointX;
    private float mEndPointY;

    //贝塞尔曲线的控制点1
    private float mAuxiliaryX1;
    private float mAuxiliaryY1;

    //贝塞尔曲线的控制点2
    private float mAuxiliaryX2;
    private float mAuxiliaryY2;


    //onFinishInflate-->onSizeChanged-->onDraw
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //onSizeChanged,w=720,h=1080,oldw=0,oldh=0

        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = w / 4 * 3;
        mEndPointY = h / 2 - 200;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 辅助点
        canvas.drawPoint(mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliary);
        canvas.drawText("控制点", mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliaryText);
        canvas.drawText("起始点", mStartPointX, mStartPointY, mPaintAuxiliaryText);
        canvas.drawText("终止点", mEndPointX, mEndPointY, mPaintAuxiliaryText);
        //辅助线
        canvas.drawLine(mStartPointX, mStartPointY, mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliary);
        canvas.drawLine(mEndPointX, mEndPointY, mAuxiliaryX1, mAuxiliaryY1, mPaintAuxiliary);

        //重置
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);

        //重点三阶贝塞尔曲线
        mPath.cubicTo(mAuxiliaryX1, mAuxiliaryY1, mAuxiliaryX2, mAuxiliaryY2, mEndPointX, mEndPointY);//绝对坐标
        //mPath.rCubicTo();指的是相对坐标
        canvas.drawPath(mPath, mPaintBezier);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_POINTER_INDEX_MASK:
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mAuxiliaryX1 = event.getX();
                    mAuxiliaryY1 = event.getY();
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mAuxiliaryX2 = event.getX();
                    mAuxiliaryY2 = event.getY();
                    invalidate();
                }
                break;

        }
        return true;
    }
}

