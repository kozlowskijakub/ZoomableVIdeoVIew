package com.zoomablewebview;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity2 extends Activity implements SurfaceTextureListener {

	private static final String TAG = "MainActivity2";
	private TextureView myTexture;
	private Camera mCamera;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myTexture = new TextureView(this);
		myTexture.setSurfaceTextureListener(this);
		setContentView(myTexture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1,
			int arg2) {
		mCamera = Camera.open();
		Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
		myTexture.setLayoutParams(new FrameLayout.LayoutParams(
				previewSize.width, previewSize.height, Gravity.CENTER));
		try {
			mCamera.setPreviewTexture(arg0);
		} catch (IOException t) {
		}
		mCamera.startPreview();
		myTexture.setAlpha(0.5f);
		myTexture.setRotation(45.0f);
		Log.d(TAG, "onSurfaceTextureAvailable");
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
		mCamera.stopPreview();
		mCamera.release();
		Log.d(TAG, "onSurfaceTextureDestroyed");
		return true;
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onSurfaceTextureSizeChanged");
		Toast.makeText(this, "onSurfaceTextureSizeChanged", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
		Log.d(TAG, "onSurfaceTextureUpdated");
		Toast.makeText(this, "onSurfaceTextureUpdated", Toast.LENGTH_SHORT)
				.show();
	}
}