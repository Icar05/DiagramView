package com.Icar05.diagramview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;





public class DiagramView extends View {




    Paint paint = new Paint();

    private int mTopColor;

    private int mBottomColor;

    private int mHorizontalLinesColor;

    private int bottomGradientTopColor;

    private DiagramDataHolder dataHolder = null;

    private int stepsValue = 4;

    private List<Integer> content = new ArrayList<>();




    public DiagramView(Context context) {
        this(context,null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiagramView);


        mTopColor = typedArray.getColor(R.styleable.DiagramView_mTopColor, -1);
        if(mTopColor == -1){
            mTopColor = getResources().getColor(R.color.defaultTopColor);
        }

        mBottomColor = typedArray.getColor(R.styleable.DiagramView_mBottomColor, -1);
        if(mBottomColor == -1){
            mBottomColor = getResources().getColor(R.color.defaultBottomColor);
        }

        mHorizontalLinesColor = typedArray.getColor(R.styleable.DiagramView_mHorizontalLinesColor, -1);
        if(mHorizontalLinesColor == -1){
            mHorizontalLinesColor = getResources().getColor(R.color.defaulthorizontalLinesColor);
        }

        bottomGradientTopColor = getResources().getColor(R.color.bottomGradientColor);

        setBackgroundColor(Color.BLACK);
        typedArray.recycle();
    }


    public void setContent(@NonNull DiagramDataHolder adapter) {
            this.dataHolder = adapter;
            if(dataHolder.setup()){
                this.content = dataHolder.getContent();
                this.stepsValue = adapter.getMaxStep();
                invalidate();
            }else{
                throw new DiagramException(dataHolder.getErrorMessage());
            }
    }


    public DiagramDataHolder getDataHolder() {
        return dataHolder;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackgroundGradient(canvas);
        drawLines(canvas);
        drawPointsByCount(canvas);

    }


    private  void drawBackgroundGradient(Canvas canvas) {

        Paint gradientPaint = new Paint();

        Shader shader = new LinearGradient(0, 0, 0, getHeight(),
                Color.TRANSPARENT,  mBottomColor, Shader.TileMode.MIRROR);

        gradientPaint.setShader(shader);
        gradientPaint.setColor(bottomGradientTopColor);

        canvas.drawRect(0, getHeight()/2, getWidth(),getHeight() , gradientPaint);
    }




    private  Float[] calculateAxesY() {

        Float[] values = new Float[stepsValue];

        float lineY = paint.getStrokeWidth();
        float offsetY = (getHeight()/stepsValue);


        for(int i = 0; i<stepsValue; i++){
            values[i] = lineY;
            lineY += offsetY;
        }

        return values;
    }

    private  void drawLines(Canvas canvas){


        preparePaintToDrawLines();

        float lineY = paint.getStrokeWidth();
        float offsetY = (getHeight()/stepsValue);


        for(int i = 0; i<stepsValue; i++){
            drawLine(canvas,lineY );
            lineY += offsetY;
        }

        canvas.drawLine(0, getHeight() - paint.getStrokeWidth(), getWidth(),
                getHeight()- paint.getStrokeWidth(), paint);

    }

    private void preparePaintToDrawLines() {
        int lineStrokeWidht = 2;
        paint.setStrokeWidth(lineStrokeWidht);
        paint.setColor(mHorizontalLinesColor);
    }


    //this code will draw lines

    private  void drawPointsByCount(Canvas canvas) {

        if(dataHolder == null || content.size() <=0){
            return;
        }

        preparePaintToDrawPoints();

        float itemSize = calculateItemSize();


        int startX = 0;

        Float[] axesY  = calculateAxesY();

            for (int i = 0; i< content.size(); i++){

                int currentStep = content.get(i);

                    float startY = axesY[currentStep-1];
                    canvas.drawLine(startX, startY, startX +=(Math.ceil(itemSize)), startY, paint);

                    // draw vertical lines
                    if(i != 0){
                        int previousStep =  content.get(i -1);

                        float prevY = axesY[ content.get(i -1) -1];
                        float x = startX - itemSize;

                        if(previousStep != currentStep){
                            drawVerticalLine(x, prevY, startY, canvas);
                        }
                    }

            }
    }

    private float calculateItemSize() {
        return (float) getWidth()/content.size();
    }


    private void drawVerticalLine(float startX, float startY, float endY,  Canvas canvas) {
        canvas.drawLine(startX, startY, startX, endY, paint);
    }

    private void drawLine(Canvas canvas, float lineY) {
        canvas.drawLine(0, lineY, getWidth(), lineY, paint);
    }

    private void preparePaintToDrawPoints() {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        Shader shader = new LinearGradient(0, 0, 0, getHeight(), mTopColor,  mBottomColor, Shader.TileMode.REPEAT /*or REPEAT*/);
        paint.setShader(shader);
    }
}
