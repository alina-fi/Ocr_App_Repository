package com.example.android.farbjagd;

import java.util.List;

import com.example.android.photobyintent.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FarbjagdActivity extends Activity {

	private static final int ACTION_TAKE_PHOTO_S = 1;
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private Bitmap mImageBitmap;
	private float averageHue;	
	com.example.android.farbjagd.drawableView shapeView;	
	Button cameraButton;
	private String requiredColor = "blue";

	/* Call the camera action to take a photo */
	private void dispatchTakePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}
	

	/* Eventlistener for the photo-button */
	Button.OnClickListener mTakePicSOnClickListener = 
		new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
		}
	};


	/* Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/* Draw photo-button for the first color Ð blue */
		cameraButton = (Button) findViewById(R.id.btnIntendS);
		cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_blue, 0, 0);
		cameraButton.setText("FOTOGRAFIERE BLAU");
		setBtnListenerOrDisable( 
				cameraButton, 
				mTakePicSOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE
		);
		
		/* Draw view with path-elements */
		shapeView = (com.example.android.farbjagd.drawableView)findViewById(R.id.shapeView);
		shapeView.setBackgroundColor(0xffffffff);
		
	}

	/* Get the picture from the gallery and get the average color*/
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");	
			getAverageColor(mImageBitmap);
	    }
	}

	/* Some lifecycle callbacks so that the image can survive orientation change */
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		super.onSaveInstanceState(outState);
	}

	
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
	}

	
	/**
	 * Indicates whether the specified action can be used as an intent. This
	 * method queries the package manager for installed packages that can
	 * respond to an intent with the specified action. If no suitable package is
	 * found, this method returns false.
	 * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
	 *
	 * @param context The application's environment.
	 * @param action The Intent action to check for availability.
	 *
	 * @return True if an Intent with the specified action can be sent and
	 *         responded to, false otherwise.
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list =
			packageManager.queryIntentActivities(intent,
					PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void setBtnListenerOrDisable( 
			Button btn, 
			Button.OnClickListener onClickListener,
			String intentName
	) {
		if (isIntentAvailable(this, intentName)) {
			btn.setOnClickListener(onClickListener);        	
		} else {
			btn.setClickable(false);
		}
	}
	
	
	//Function to get average color of the bitmap
	private void getAverageColor(Bitmap bitmap){	
		
		/* Toast messages */
		Context context = getApplicationContext();
		CharSequence error = "Farbe leider falsch. Versuch es noch einmal!";
		int duration = Toast.LENGTH_LONG;
		Toast toastError = Toast.makeText(context, error, duration);
		
		
		/* Save all pixels in an array - ARGB values */
		int[] pixels = new int[bitmap.getHeight()*bitmap.getWidth()];
		bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		
		/* Convert all pixels in HSV and save the hue values in an array */
		float[] hsv = new float[3];
		int[] pixelsHSV = new int[pixels.length];  //Devide 100 because there are too much pixels
		try {
			for (int i=0; i<pixels.length; i++){
				android.graphics.Color.colorToHSV(pixels[i], hsv);
				pixelsHSV[i] = (int) hsv[0];
				Log.v("Msg4","pixelsHSV" + pixelsHSV[i] + " " +i);
			};
		  } catch (Exception e) {
		    System.out.println("error");
		  }
		Log.v("Msg2","Your Color:");
		
		int pixelAmount = 0;
		
		 
		/* Calculate average of hue values */
		/* hsv[0] corresponds to the hue value */
		int sumHue = 0;
		try {
			for (int i=0; i<pixelsHSV.length; i++){
			    pixelAmount++;
			    sumHue = sumHue + pixelsHSV[i];
			    Log.v("Msg5", sumHue + "");
			};
		  } catch (Exception e) {
		    System.out.println(e.toString());		
		}
		
		
		/* Save the average value in a global variable */
		averageHue = sumHue / pixelAmount;
		Log.v("Msg3","Your Color: " + averageHue);
		
		
		/* Save the HSV-color values in an array  (to use it for the painted image) */
		float[] paintedColor = new float[3];
		paintedColor[0]=averageHue; 
		paintedColor[1]=50; 
		paintedColor[2]=100;
		
		
		/* Check required color and act according to the result */
		cameraButton = (Button) findViewById(R.id.btnIntendS);
		
		if (requiredColor == "blue"){
			if (averageHue > 180 && averageHue < 270){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_red, 0, 0);
				cameraButton.setText("FOTOGRAFIERE ROT");
				requiredColor = "red";
				shapeView.mDrawables[3].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
				shapeView.mDrawables[4].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
				
			}
			else
				toastError.show();			
		}
		else if (requiredColor == "red"){
			if (averageHue > 300 || averageHue < 50){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_green, 0, 0);
				cameraButton.setText("FOTOGRAFIERE GR†N");
				requiredColor = "green";
				shapeView.mDrawables[1].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
				shapeView.mDrawables[2].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
			}
			else
				toastError.show();
		}
		else if (requiredColor == "green"){
			if (averageHue > 70 && averageHue < 170){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_yellow, 0, 0);
				cameraButton.setText("FOTOGRAFIERE GELB");
				requiredColor = "yellow";
				shapeView.mDrawables[0].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
			}
			else
				toastError.show();
		}
		else if (requiredColor == "yellow"){
			if (averageHue > 30 && averageHue < 80){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_violet, 0, 0);
				cameraButton.setText("FOTOGRAFIERE PINK");
				requiredColor = "pink";
				shapeView.mDrawables[7].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
				shapeView.mDrawables[8].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
			}
			else
				toastError.show();
		}
		else if (requiredColor == "pink"){
			if (averageHue > 260 && averageHue < 340){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_grey, 0, 0);
				cameraButton.setText("GUT GEMACHT!");
				shapeView.mDrawables[5].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
				shapeView.mDrawables[6].getPaint().setColor(android.graphics.Color.HSVToColor(paintedColor));
			}
			else
				toastError.show();
		}
		else
			toastError.show();
	}	

}