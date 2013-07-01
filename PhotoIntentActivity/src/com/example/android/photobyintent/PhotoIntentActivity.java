package com.example.android.photobyintent;

import java.util.List;

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


public class PhotoIntentActivity extends Activity {

	private static final int ACTION_TAKE_PHOTO_S = 2;

	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private Bitmap mImageBitmap;

	private float averageHue;
	
	com.example.android.photobyintent.drawableView shapeView;
	
	Button cameraButton;
	private String requiredColor = "blue";

	private void dispatchTakePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
		
		getAverageColor(mImageBitmap);
	}

	Button.OnClickListener mTakePicSOnClickListener = 
		new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
		}
	};


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		cameraButton = (Button) findViewById(R.id.btnIntendS);
		cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_blue, 0, 0);
		cameraButton.setText("take a blue photo");
		setBtnListenerOrDisable( 
				cameraButton, 
				mTakePicSOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE
		);
		
		/** draw view with pathShapes */
		shapeView = (com.example.android.photobyintent.drawableView)findViewById(R.id.shapeView);
		shapeView.setBackgroundColor(0xffffffff);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
				handleSmallCameraPhoto(data);
	    }
	}

	// Some lifecycle callbacks so that the image can survive orientation change
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		super.onSaveInstanceState(outState);
	}

	@Override
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
	
	
	//function to get average color
	private void getAverageColor(Bitmap bitmap){	
		
		/* Toast Messages */
		Context context = getApplicationContext();
		CharSequence error = "Sorry, wrong color. Try again";
		int duration = Toast.LENGTH_LONG;
		Toast toastError = Toast.makeText(context, error, duration);
		
		
		/* Save all pixels in an Array - ARGB values */
		int[] pixels = new int[bitmap.getHeight()*bitmap.getWidth()];
		bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		/* Convert all pixels in HSV and save the HueValues in an Array */
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
		 
		/* Calculate average of Hue Values (hsv[0] sind alle Hue-Farbwerte) */
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
		
		/* Save the Average Value in the global variable */
		averageHue = sumHue / pixelAmount;
		Log.v("Msg3","Your Color: " + averageHue);
		
		/* Check required Color and act according the result */
		cameraButton = (Button) findViewById(R.id.btnIntendS);
		
		if (requiredColor == "blue"){
			if (averageHue > 180 && averageHue < 270){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_red, 0, 0);
				cameraButton.setText("take a red photo");
				requiredColor = "red";
				shapeView.mDrawables[3].getPaint().setColor(0xFF0000FF);
				shapeView.mDrawables[4].getPaint().setColor(0xFF0000FF);
				
			}
			else
				toastError.show();			
		}
		else if (requiredColor == "red"){
			if (averageHue > 300 || averageHue < 50){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_green, 0, 0);
				cameraButton.setText("take a green photo");
				requiredColor = "green";
				shapeView.mDrawables[1].getPaint().setColor(0xFFFF0000);
				shapeView.mDrawables[2].getPaint().setColor(0xFFFF0000);
			}
			else
				toastError.show();
		}
		else if (requiredColor == "green"){
			if (averageHue > 70 && averageHue < 170){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_yellow, 0, 0);
				cameraButton.setText("take a yellow photo");
				requiredColor = "yellow";
				shapeView.mDrawables[0].getPaint().setColor(0xFF00FF00);
			}
			else
				toastError.show();
		}
		else if (requiredColor == "yellow"){
			if (averageHue > 30 && averageHue < 80){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_violet, 0, 0);
				cameraButton.setText("take a violet photo");
				requiredColor = "violet";
				shapeView.mDrawables[7].getPaint().setColor(0xFFFFFF00);
				shapeView.mDrawables[8].getPaint().setColor(0xFFFFFF00);
			}
			else
				toastError.show();
		}
		else if (requiredColor == "violet"){
			if (averageHue > 260 && averageHue < 340){
				cameraButton.setCompoundDrawablesWithIntrinsicBounds (0, R.drawable.button_grey, 0, 0);
				cameraButton.setText("well done!");
				shapeView.mDrawables[5].getPaint().setColor(0xFFFF00FF);
				shapeView.mDrawables[6].getPaint().setColor(0xFFFF00FF);
			}
			else
				toastError.show();
		}
		else
			toastError.show();
	}	

}