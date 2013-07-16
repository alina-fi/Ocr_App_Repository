package com.example.android.farbjagd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

public class drawableView extends View {

    public ShapeDrawable mDrawables[] = new ShapeDrawable[9];
    public ShapeDrawable mDrawablesStrokes[] = new ShapeDrawable[9];
   

    public drawableView(Context context,AttributeSet attrs) {
    super(context,attrs);
    
    /*---------------------------------*/
    /*body green */
    /*---------------------------------*/
    Path body = new Path();
    body.moveTo(53, 38);
    body.cubicTo(52, 36, 48, 36, 47, 38);
    body.cubicTo(40, 23, 25, 17, 20, 15);
    body.cubicTo(16, 10, 13, 18, 18, 17);
    body.cubicTo(25, 18, 44, 30, 46, 42);
    body.lineTo(46, 90);
    body.cubicTo(48, 100, 52, 100, 54, 90);
    body.lineTo(54, 90);
    body.lineTo(54, 42);
    body.cubicTo(56, 30, 75, 18, 82, 17);
    body.cubicTo(87, 18, 84, 10, 80, 15);
    body.cubicTo(75, 17, 60, 23, 53, 38);
    body.close();
    
    /*---------------------------------*/
    /*upperRightWing red */
    /*---------------------------------*/
    Path upperRightWing = new Path();
    upperRightWing.moveTo(54, 44);
    upperRightWing.cubicTo(63, 30, 76, 21, 88, 28);
    upperRightWing.cubicTo(98, 34, 97, 52, 86, 60);
    upperRightWing.cubicTo(80, 65, 70, 68, 64, 68);
    upperRightWing.lineTo(54, 68);
    upperRightWing.close();
    
    /*---------------------------------*/
    /*lowerRightWing blue */
    /*---------------------------------*/
    Path lowerRightWing = new Path();
    lowerRightWing.moveTo(54, 68);
    lowerRightWing.lineTo(64, 68);
    lowerRightWing.cubicTo(79, 72, 95, 84, 92, 103);
    lowerRightWing.cubicTo(90, 114, 65, 118, 54, 90);
    lowerRightWing.close();

    /*---------------------------------*/
    /*upperLeftWing red */
    /*---------------------------------*/
    Path upperLeftWing = new Path();
    upperLeftWing.moveTo(46, 44);
    upperLeftWing.cubicTo(37, 30, 24, 21, 12, 28);
    upperLeftWing.cubicTo(2, 34, 3, 52, 14, 60);
    upperLeftWing.cubicTo(20, 65, 30, 68, 36, 68);
    upperLeftWing.lineTo(46, 68);
    upperLeftWing.close();
    
    /*---------------------------------*/
    /*lowerLeftWing blue */
    /*---------------------------------*/
    Path lowerLeftWing = new Path();
    lowerLeftWing.moveTo(46, 68);
    lowerLeftWing.lineTo(36, 68);
    lowerLeftWing.cubicTo(21, 72, 5, 84, 8, 103);
    lowerLeftWing.cubicTo(10, 114, 35, 118, 46, 90);
    lowerLeftWing.close();    

    /*---------------------------------*/
    /*upperLeftPattern blue */
    /*---------------------------------*/
    Path upperLeftPattern = new Path();
    upperLeftPattern.moveTo(15, 34);
    upperLeftPattern.cubicTo(10, 39, 10, 47, 18, 55);
    upperLeftPattern.cubicTo(26, 55, 34, 47, 34, 39);
    upperLeftPattern.cubicTo(29, 34, 22, 31, 15, 34);
    upperLeftPattern.close();

    /*---------------------------------*/
    /*upperRightPattern blue */
    /*---------------------------------*/
    Path upperRightPattern = new Path();
    upperRightPattern.moveTo(85, 34);
    upperRightPattern.cubicTo(90, 39, 90, 47, 82, 55);
    upperRightPattern.cubicTo(74, 55, 66, 47, 66, 39);
    upperRightPattern.cubicTo(71, 34, 78, 31, 85, 34);
    upperRightPattern.close();
    
    /*---------------------------------*/
    /*lowerLeftPattern red */
    /*---------------------------------*/
    Path lowerLeftPattern = new Path();
    lowerLeftPattern.moveTo(19, 103);
    lowerLeftPattern.cubicTo(12, 100, 14, 85, 25, 85);
    lowerLeftPattern.cubicTo(29, 85, 37, 87, 34, 98);
    lowerLeftPattern.cubicTo(32, 103, 25, 106, 19, 103);
    lowerLeftPattern.close();

    /*---------------------------------*/
    /*lowerRightPattern red */
    /*---------------------------------*/
    Path lowerRightPattern = new Path();
    lowerRightPattern.moveTo(81, 103);
    lowerRightPattern.cubicTo(88, 100, 86, 85, 75, 85);
    lowerRightPattern.cubicTo(71, 85, 63, 87, 66, 98);
    lowerRightPattern.cubicTo(68, 103, 75, 106, 81, 103);
    lowerRightPattern.close();
    
    /* body */
    mDrawables[0] = new ShapeDrawable(new PathShape(body, 100, 130));
    mDrawablesStrokes[0] = new ShapeDrawable(new PathShape(body, 100, 130));
    
    /* wings */
    mDrawables[1] = new ShapeDrawable(new PathShape(upperRightWing, 100, 130));    
    mDrawablesStrokes[1] = new ShapeDrawable(new PathShape(upperRightWing, 100, 130));

    mDrawables[2] = new ShapeDrawable(new PathShape(upperLeftWing, 100, 130));
    mDrawablesStrokes[2] = new ShapeDrawable(new PathShape(upperLeftWing, 100, 130));
    
    mDrawables[3] = new ShapeDrawable(new PathShape(lowerRightWing, 100, 130));
    mDrawablesStrokes[3] = new ShapeDrawable(new PathShape(lowerRightWing, 100, 130));
    
    mDrawables[4] = new ShapeDrawable(new PathShape(lowerLeftWing, 100, 130));
    mDrawablesStrokes[4] = new ShapeDrawable(new PathShape(lowerLeftWing, 100, 130));
    
    /* pattern */
    mDrawables[5] = new ShapeDrawable(new PathShape(lowerRightPattern, 100, 130));
    mDrawablesStrokes[5] = new ShapeDrawable(new PathShape(lowerRightPattern, 100, 130));

    mDrawables[6] = new ShapeDrawable(new PathShape(lowerLeftPattern, 100, 130));
    mDrawablesStrokes[6] = new ShapeDrawable(new PathShape(lowerLeftPattern, 100, 130));
    
    mDrawables[7] = new ShapeDrawable(new PathShape(upperLeftPattern, 100, 130));
    mDrawablesStrokes[7] = new ShapeDrawable(new PathShape(upperLeftPattern, 100, 130));
    
    mDrawables[8] = new ShapeDrawable(new PathShape(upperRightPattern, 100, 130));
    mDrawablesStrokes[8] = new ShapeDrawable(new PathShape(upperRightPattern, 100, 130));

    

    
    /* give all shapes the color white */
    for (int i = 0; i<=8; i++){
    	mDrawables[i].getPaint().setColor(0xFFFFFFFF);
    }
    
    /* give all shapes a black stroke */
    for (int j = 0; j<=8; j++){
    	mDrawablesStrokes[j].getPaint().setColor(0xFF000000);
        mDrawablesStrokes[j].getPaint().setStyle(Style.STROKE);
    }
}

    protected void onDraw(Canvas canvas) {
    	
        for (int i = 0; i<=8; i++){
        	mDrawables[i].setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        	mDrawables[i].draw(canvas);
        	
        	mDrawablesStrokes[i].setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        	mDrawablesStrokes[i].draw(canvas);
        }
    }
}