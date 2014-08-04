package bongpan.andong.amsterdam.locker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import bongpan.andong.amsterdam.R;
import bongpan.andong.amsterdam.util.BitmapUtils;
import bongpan.andong.amsterdam.util.Size;

public class LockScreenSurfaceView extends SurfaceView 
		implements SurfaceHolder.Callback {
	// 태그
	private Context mContext;	
	private LockScreenDrawThread mDrawThread;	
	private SurfaceHolder mSurfaceHolder;
	
	private SlideSelector mSlideSelector;
	
	public static final float BASE_SURFACE_SIZE_WIDTH = 720.0f;
	public static final float BASE_SURFACE_SIZE_HEIGHT = 1230.0f;
	public static final float ASPECT_RATIO = BASE_SURFACE_SIZE_WIDTH / BASE_SURFACE_SIZE_HEIGHT;
	
	public static final float DEFAULT_AD_RATIO = 0.87f;
	
	private static Bitmap backgroundBitmap = null;
//	private SparseArray<FloatingObject> foregroundBitmap = new SparseArray<FloatingObject>();
	
	public Size surfaceSize;
	public Size adSize;
	
	public int width, height;
	
	public boolean bBlock = false;
	
	private Paint paint = new Paint();
	
	
	
	
	//-- RENEWAL V7 --//
	//-- RENEWAL V7 --//	
	private boolean scaleEnable = false;
	//-- RENEWAL V7 --//
	//-- RENEWAL V7 --//	
	
	public LockScreenSurfaceView(Context context) {
		super(context);
		mContext = context;
		mSurfaceHolder = LockScreenSurfaceView.this.getHolder();
		mSurfaceHolder.addCallback(this);		
		mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
		initLayout();
	}
	
	/**
	 * 
	 */
	public void initLayout() {	
		paint.setAntiAlias(false);
		paint.setFilterBitmap(false);		
		paint.setDither(true);
	}
	
	public int getStatusBarHeight() {
	    int result = 0;
	    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	    if (resourceId > 0)
	        result = getResources().getDimensionPixelSize(resourceId);
	    return result;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 갤럭시 S4 4.4 freezing bug fix
		synchronized (this) {
			if (mDrawThread == null || !mDrawThread.isAlive()) {
				mDrawThread = new LockScreenDrawThread(mSurfaceHolder, this);		
				try {
					mDrawThread.setRunnable(true);
					mDrawThread.start();
				}
				catch (Exception e) {
					mDrawThread = null;
				}
			}
		}

//		boolean result = false;		
		try {
			Point point = new Point((int) event.getX(), (int) event.getY());
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				if (mSlideSelector.hitTest(point)) {
					mSlideSelector.setPressed(true);
				}
				break;
			case MotionEvent.ACTION_UP:
				if (mSlideSelector.isTouchMoved() == true) {
					if (mSlideSelector.isActionSlotSelected()) {
						doAction();
						mSlideSelector.setPressed(false);						
						mSlideSelector.returnBack();
						break;
					}
					if (mSlideSelector.isUnlockSlotSelected()) {
						doUnlock();
						mSlideSelector.setPressed(false);						
						mSlideSelector.returnBack();
						break;
					}
					if (mSlideSelector.isQuickLauncherEnable()) {
						int index = mSlideSelector.getCustomSlotId();
						if (index >= 0) {
							doCustomAction(index);
							mSlideSelector.setPressed(false);						
							mSlideSelector.returnBack();
							break;
						}
					}
					mSlideSelector.setPressed(false);						
					mSlideSelector.returnBack();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (mSlideSelector.isTouchMoved() == true) {
					mSlideSelector.move(point.x, point.y);
				}
				break;
			}		
			
//			if (mSlideSelector.isTouchMoved() == true)
//				result = true;
		}
		catch (Exception e) {
			
		}		
		
		return true;
	}	
	
	public boolean isScaleEnable() {
		return scaleEnable;
	}
	
	/**
	 * 
	 */
	@Override
    public void onDraw(Canvas canvas) {
		if (canvas != null) {
			drawReal(canvas);
		}
	} 
	
	/**
	 * 
	 */
	public void drawManually() {
		Canvas canvas = mSurfaceHolder.lockCanvas();
		if (canvas != null) {
			drawReal(canvas);
			mSurfaceHolder.unlockCanvasAndPost(canvas);
		}
	}
	
	/**
	 * 
	 * @param canvas
	 */
	public void drawReal(Canvas canvas) {	
    	mSlideSelector.drawSimple(canvas);
	}
	
	/**
	 * 
	 * @param canvas
	 */
	public void drawBackground(Canvas canvas) {
		int wt = canvas.getWidth(), ht = canvas.getHeight();		
		Rect rt = new Rect(0, 0, wt, ht);
		try {
			if (backgroundBitmap == null) 
				backgroundBitmap = BitmapUtils.createBitmap(mContext.getResources(), 
						R.drawable.bg_lockscreen);
			if (canvas != null)
				canvas.drawBitmap(backgroundBitmap, null, rt, paint);
		} 
		catch (Exception e) {}
	}

	/**
	 * 
	 */
	public void doAction() {
//		mDrawThread.setRunnable(false); // 이 경우 액션을 취소할 수도 있기 때문에 중단하지 않음
		((DisableLockerActivity) mContext).doAction();
	}
	
	/**
	 * 
	 */
	public void doUnlock() {
		mDrawThread.setRunnable(false);
		((DisableLockerActivity) mContext).unlock();
	}
	
	/**
	 * 
	 * @param index
	 */
	public void doCustomAction(int index) {
		mDrawThread.setRunnable(false);
		((DisableLockerActivity) mContext).doAction();
	}
	
	/**
	 * 
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// do nothing yet
	}

	/**
	 * 
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//-- RENEWAL V7 --//
		//-- RENEWAL V7 --//
		int wt = getWidth(), ht = getHeight();
		
		float ratio = (float) wt / (float) ht; 
		if (ratio < 0.7) {
			scaleEnable = true;
		}
		//-- RENEWAL V7 --//
		//-- RENEWAL V7 --//
		
		width = this.getWidth();
    	height = this.getHeight();
    	
		BitmapUtils.setRatio(width / BASE_SURFACE_SIZE_WIDTH, height / BASE_SURFACE_SIZE_HEIGHT);
		surfaceSize = new Size(width, height);
		int ad_h = (int)(height * DEFAULT_AD_RATIO);
		adSize = new Size((int)(width * DEFAULT_AD_RATIO), ad_h);		
						
		if (mSlideSelector == null) {
			mSlideSelector = new SlideSelector(this);
	    	// TODO: base size를 넘기지 않고 생성자에 w, h를 전달하여 해결 			
			mSlideSelector.setBaseSize(surfaceSize, adSize);			
			mSlideSelector.setBitmap(mContext);		
		}	
		drawManually();
	}

	/**
	 * 
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		this.stopDrawThread();
	}
	
	synchronized public void stopDrawThread() {
		boolean retry = true;
		if (mDrawThread != null) {
			mDrawThread.setRunnable(false);
			while (retry) {
				try { 
					mDrawThread.join();
					retry = false;
				}
				catch (InterruptedException e) {
					// Try interrupt again
				}
				break;
			}
			mDrawThread = null;
			drawManually();
		}
	}
	
	public void freeMemory() {
	}
	
	public void rearrangeControls() {
		if (mSlideSelector != null) 
			mSlideSelector.rearrangeControls();
	}	
	
	public void reloadData() {
	}
	
}
