package bongpan.andong.amsterdam.locker;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class LockScreenDrawThread extends Thread
{
	private LockScreenSurfaceView mSurfaceView;	
	private SurfaceHolder mSurfaceHolder;
	
	private boolean bRunnable = false;

	private SlideSelector mSliderSelector;
	
	public LockScreenDrawThread(SurfaceHolder surfaceHolder, LockScreenSurfaceView surfaceView) {
		this.mSurfaceView = surfaceView;		
		this.mSurfaceHolder = surfaceHolder;
	}
	
	public void setRunnable(boolean bRunnable) {
		this.bRunnable = bRunnable;
	}
	
	@SuppressLint("WrongCall")
	public void run() {
		while (bRunnable) {
			Canvas canvas = null;	
			try {
				canvas = mSurfaceHolder.lockCanvas(null);  // canvas 참조 얻음
				synchronized(mSurfaceHolder) {
					mSurfaceView.onDraw(canvas);
				}
			}
			finally {				
				if (canvas != null)					
					mSurfaceHolder.unlockCanvasAndPost(canvas);  // back buffer에 그려진 비트팹을 스크린에 그린다
	        }
		}
	}		
	
	public SlideSelector getSlideSelector() {
		return mSliderSelector;
	}
}
