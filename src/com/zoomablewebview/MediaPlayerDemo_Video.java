package com.zoomablewebview;

import java.io.IOException;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MediaPlayerDemo_Video extends Activity implements
		TextureView.SurfaceTextureListener {

	protected static final String TAG = "MediaPlayerDemo_Video";

	private MediaPlayer mMediaPlayer;

	private TextureView mPreview;

	private SeekBar seekBar;
	private static final int START_PROGRESS = 70;

	@Override
	public void onCreate(Bundle icicle) {
		setContentView(R.layout.activity_main);
		super.onCreate(icicle);
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setProgress(START_PROGRESS);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// ;

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onProgressChanged");
				mPreview.setScaleX((float) progress / START_PROGRESS);
				mPreview.setScaleY((float) progress / START_PROGRESS);
				mPreview.invalidate();

			}
		});
		// mPreview = new TextureView(this);
		mPreview = (TextureView) findViewById(R.id.textureView1);
		mPreview.setSurfaceTextureListener(this);
		// extras = getIntent().getExtras();
		// setContentView(mPreview);
	}

	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width,
			int height) {
		Surface s = new Surface(surface);

		try {
			mMediaPlayer = new MediaPlayer();
			mPreview.setScaleX((float) 4.5);
			// mPreview.setScrollX(2);
			mMediaPlayer.setDataSource("http://daily3gp.com/vids/747.3gp");
			mMediaPlayer.setSurface(s);
			mMediaPlayer.prepare();
			// mMediaPlayer.setOnBufferingUpdateListener(this);
			// mMediaPlayer.setOnCompletionListener(this);
			// mMediaPlayer.setOnPreparedListener(this);
			// mMediaPlayer.setOnVideoSizeChangedListener(this);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		// TODO Auto-generated method stub

	}
}