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
import android.widget.ImageView;


public class PhotoIntentActivity extends Activity {

	private static final int ACTION_TAKE_PHOTO_S = 2;

	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private ImageView mImageView;
	private Bitmap mImageBitmap;

	private float averageHue;

	private void dispatchTakePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
		
		getAverageColor(mImageBitmap);
		
		mImageView.setImageBitmap(mImageBitmap);
		mImageView.setVisibility(View.VISIBLE);
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

		mImageView = (ImageView) findViewById(R.id.imageView1);
		mImageBitmap = null;

		Button picSBtn = (Button) findViewById(R.id.btnIntendS);
		setBtnListenerOrDisable( 
				picSBtn, 
				mTakePicSOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE
		);
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
		mImageView.setImageBitmap(mImageBitmap);
		mImageView.setVisibility(
				savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? 
						ImageView.VISIBLE : ImageView.INVISIBLE
		);
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
			btn.setText( 
				getText(R.string.cannot).toString() + " " + btn.getText());
			btn.setClickable(false);
		}
	}
	
	
	//function to get average color
	private void getAverageColor(Bitmap bitmap){
		/* Save all pixels in an Array - ARGB values */
		int[] pixels = new int[bitmap.getHeight()*bitmap.getWidth()];
		bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		/* Convert all pixels in HSV and save the HueValues in an Array */
		float[] hsv = new float[3];
		int[] pixelsHSV = new int[pixels.length];  //Devide 100 because there are too much pixels
		try {
			for (int i=0; i<=pixels.length; i++){
				android.graphics.Color.colorToHSV(pixels[i], hsv);
				pixelsHSV[i] = (int) hsv[0];
				Log.v("Msg4","pixelsHSV" + pixelsHSV[i] + " " +i);
			};
		  } catch (Exception e) {
		    System.out.println("error");
		  }
		Log.v("Msg2","Your Color:");
		 
		/* Calculate average of Hue Values (hsv[0] sind alle Hue-Farbwerte) */
		int sumHue = 0;
		try {
			for (int i=0; i<=pixelsHSV.length; i++){
				 sumHue = sumHue + pixelsHSV[i];
					Log.v("Msg5", sumHue + "");
			};
		  } catch (Exception e) {
		    System.out.println("error");		
		}
		
		/* Save the Average Value in the global variable */
		averageHue = sumHue / pixelsHSV.length;
		Log.v("Msg3","Your Color: " + averageHue);
	}	

}