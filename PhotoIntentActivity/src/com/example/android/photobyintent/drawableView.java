package com.example.android.photobyintent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

public class drawableView extends View {

    public ShapeDrawable mDrawables[] = new ShapeDrawable[7];
    public ShapeDrawable mDrawablesStrokes[] = new ShapeDrawable[7];
   

    public drawableView(Context context,AttributeSet attrs) {
    super(context,attrs);
    
    /*---------------------------------*/
    /*body green */
    /*---------------------------------*/
    Path body = new Path();
    body.moveTo(48, 40);
    body.lineTo(44, 30);
    body.lineTo(42, 32);
    body.lineTo(46, 42);
    body.lineTo(46, 90);
    body.lineTo(48, 98);
    body.lineTo(50, 102);
    body.lineTo(52, 98);
    body.lineTo(54, 90);
    body.lineTo(54, 42);
    body.lineTo(58, 32);
    body.lineTo(56, 30);
    body.lineTo(52, 40);
    body.close();
    
    /*---------------------------------*/
    /*upperRightWing blue */
    /*---------------------------------*/
    Path upperRightWing = new Path();
    upperRightWing.moveTo(54, 44);
    upperRightWing.lineTo(60, 34);
    upperRightWing.lineTo(70, 26);
    upperRightWing.lineTo(80, 24);
    upperRightWing.lineTo(88, 28);
    upperRightWing.lineTo(92, 38);
    upperRightWing.lineTo(92, 48);
    upperRightWing.lineTo(86, 58);
    upperRightWing.lineTo(74, 66);
    upperRightWing.lineTo(64, 68);
    upperRightWing.lineTo(54, 68);
    upperRightWing.close();
    
    /*---------------------------------*/
    /*lowerRightWing red */
    /*---------------------------------*/
    Path lowerRightWing = new Path();
    lowerRightWing.moveTo(54, 68);
    lowerRightWing.lineTo(64, 68);
    lowerRightWing.lineTo(76, 72);
    lowerRightWing.lineTo(85, 78);
    lowerRightWing.lineTo(88, 84);
    lowerRightWing.lineTo(88, 96);
    lowerRightWing.lineTo(82, 104);
    lowerRightWing.lineTo(74, 108);
    lowerRightWing.lineTo(68, 108);
    lowerRightWing.lineTo(62, 104);
    lowerRightWing.lineTo(54, 90);
    lowerRightWing.close();

    /*---------------------------------*/
    /*upperLeftWing blue */
    /*---------------------------------*/
    Path upperLeftWing = new Path();
    upperLeftWing.moveTo(46, 44);
    upperLeftWing.lineTo(40, 34);
    upperLeftWing.lineTo(30, 26);
    upperLeftWing.lineTo(20, 24);
    upperLeftWing.lineTo(12, 28);
    upperLeftWing.lineTo(8, 38);
    upperLeftWing.lineTo(8, 48);
    upperLeftWing.lineTo(14, 58);
    upperLeftWing.lineTo(26, 66);
    upperLeftWing.lineTo(36, 68);
    upperLeftWing.lineTo(46, 68);
    upperLeftWing.close();
    
    /*---------------------------------*/
    /*lowerLeftWing red */
    /*---------------------------------*/
    Path lowerLeftWing = new Path();
    lowerLeftWing.moveTo(46, 68);
    lowerLeftWing.lineTo(36, 68);
    lowerLeftWing.lineTo(24, 72);
    lowerLeftWing.lineTo(15, 78);
    lowerLeftWing.lineTo(12, 84);
    lowerLeftWing.lineTo(12, 96);
    lowerLeftWing.lineTo(18, 104);
    lowerLeftWing.lineTo(26, 108);
    lowerLeftWing.lineTo(32, 108);
    lowerLeftWing.lineTo(38, 104);
    lowerLeftWing.lineTo(46, 90);
    lowerLeftWing.close();    

    /*---------------------------------*/
    /*upperLeftPattern red */
    /*---------------------------------*/
    Path upperLeftPattern = new Path();
    upperLeftPattern.moveTo(21, 31);
    upperLeftPattern.lineTo(13, 39);
    upperLeftPattern.lineTo(13, 47);
    upperLeftPattern.lineTo(21, 55);
    upperLeftPattern.lineTo(29, 55);
    upperLeftPattern.lineTo(37, 47);
    upperLeftPattern.lineTo(37, 39);
    upperLeftPattern.lineTo(29, 31);
    upperLeftPattern.close();

    /*---------------------------------*/
    /*upperRightPattern red */
    /*---------------------------------*/
    Path upperRightPattern = new Path();
    upperRightPattern.moveTo(79, 31);
    upperRightPattern.lineTo(87, 39);
    upperRightPattern.lineTo(87, 47);
    upperRightPattern.lineTo(79, 55);
    upperRightPattern.lineTo(71, 55);
    upperRightPattern.lineTo(63, 47);
    upperRightPattern.lineTo(63, 39);
    upperRightPattern.lineTo(71, 31);
    upperRightPattern.close();
    
    //body green
    mDrawables[0] = new ShapeDrawable(new PathShape(body, 100, 130));
    mDrawablesStrokes[0] = new ShapeDrawable(new PathShape(body, 100, 130));
    
    //red
    mDrawables[1] = new ShapeDrawable(new PathShape(upperRightWing, 100, 130));    
    mDrawablesStrokes[1] = new ShapeDrawable(new PathShape(upperRightWing, 100, 130));

    mDrawables[2] = new ShapeDrawable(new PathShape(upperLeftWing, 100, 130));
    mDrawablesStrokes[2] = new ShapeDrawable(new PathShape(upperLeftWing, 100, 130));
    
    //blue
    mDrawables[3] = new ShapeDrawable(new PathShape(lowerRightWing, 100, 130));
    mDrawablesStrokes[3] = new ShapeDrawable(new PathShape(lowerRightWing, 100, 130));
    
    mDrawables[4] = new ShapeDrawable(new PathShape(lowerLeftWing, 100, 130));
    mDrawablesStrokes[4] = new ShapeDrawable(new PathShape(lowerLeftWing, 100, 130));
    
    mDrawables[5] = new ShapeDrawable(new PathShape(upperLeftPattern, 100, 130));
    mDrawablesStrokes[5] = new ShapeDrawable(new PathShape(upperLeftPattern, 100, 130));
    
    mDrawables[6] = new ShapeDrawable(new PathShape(upperRightPattern, 100, 130));
    mDrawablesStrokes[6] = new ShapeDrawable(new PathShape(upperRightPattern, 100, 130));

    

    
    //give all Shapes the color white
    for (int i = 0; i<=6; i++){
    	mDrawables[i].getPaint().setColor(0xFFFFFFFF);
    }
    
    //give all Shapes a black stroke
    for (int j = 0; j<=6; j++){
    	mDrawablesStrokes[j].getPaint().setColor(0xFF000000);
        mDrawablesStrokes[j].getPaint().setStyle(Style.STROKE);
    }
}

    protected void onDraw(Canvas canvas) {
    	
        for (int i = 0; i<=6; i++){
        	mDrawables[i].setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        	mDrawables[i].draw(canvas);
        	
        	mDrawablesStrokes[i].setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        	mDrawablesStrokes[i].draw(canvas);
        }
    }
}