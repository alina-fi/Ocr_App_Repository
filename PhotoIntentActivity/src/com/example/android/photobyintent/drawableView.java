package com.example.android.photobyintent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

public class drawableView extends View {
    private ShapeDrawable mDrawableBody;
    private ShapeDrawable mDrawableUpperRightWing;
    private ShapeDrawable mDrawableLowerRightWing;
    private ShapeDrawable mDrawableUpperLeftWing;
    private ShapeDrawable mDrawableLowerLeftWing;
    private ShapeDrawable mDrawableUpperLeftPattern;
    private ShapeDrawable mDrawableUpperRightPattern;

    

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
    lowerRightWing.lineTo(82, 78);
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
    lowerLeftWing.lineTo(18, 78);
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
    
    //mDrawables = new ShapeDrawable[3];
    mDrawableBody = new ShapeDrawable(new PathShape(body, 100, 130));
    mDrawableBody.getPaint().setColor(0xFF00FF00);

    mDrawableUpperRightWing = new ShapeDrawable(new PathShape(upperRightWing, 100, 130));
    mDrawableUpperRightWing.getPaint().setColor(0xFF0000FF);
    //mDrawableUpperRightWing.getPaint().setStyle(Style.STROKE);

    mDrawableLowerRightWing = new ShapeDrawable(new PathShape(lowerRightWing, 100, 130));
    mDrawableLowerRightWing.getPaint().setColor(0xFFFF0000);

    mDrawableUpperLeftWing = new ShapeDrawable(new PathShape(upperLeftWing, 100, 130));
    mDrawableUpperLeftWing.getPaint().setColor(0xFF0000FF);

    mDrawableLowerLeftWing = new ShapeDrawable(new PathShape(lowerLeftWing, 100, 130));
    mDrawableLowerLeftWing.getPaint().setColor(0xFFFF0000);
    
    mDrawableUpperLeftPattern = new ShapeDrawable(new PathShape(upperLeftPattern, 100, 130));
    mDrawableUpperLeftPattern.getPaint().setColor(0xFFFF0000);
    
    mDrawableUpperRightPattern = new ShapeDrawable(new PathShape(upperRightPattern, 100, 130));
    mDrawableUpperRightPattern.getPaint().setColor(0xFFFF0000);
    }

    protected void onDraw(Canvas canvas) {

    	mDrawableBody.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableBody.draw(canvas);

    	mDrawableUpperRightWing.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableUpperRightWing.draw(canvas);
    	
    	mDrawableLowerRightWing.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableLowerRightWing.draw(canvas);
    	
    	mDrawableUpperLeftWing.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableUpperLeftWing.draw(canvas);
    	
    	mDrawableLowerLeftWing.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableLowerLeftWing.draw(canvas);
    	
    	mDrawableUpperLeftPattern.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableUpperLeftPattern.draw(canvas);
    	
    	mDrawableUpperRightPattern.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    	mDrawableUpperRightPattern.draw(canvas);
    	}
}