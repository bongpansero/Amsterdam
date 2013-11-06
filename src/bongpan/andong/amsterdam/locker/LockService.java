package bongpan.andong.amsterdam.locker;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import bongpan.andong.amsterdam.util.LogUtil;

public class LockService extends Service{
	
	private static WindowManager localWindowManager;
	private static FrameLayout mLockView;
	private static int serviceCount = 0;
	private	WindowManager.LayoutParams mParams;
	private View currentView;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mParams = new WindowManager.LayoutParams();
		mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
		mParams.height = WindowManager.LayoutParams.MATCH_PARENT;
		mParams.format = PixelFormat.TRANSLUCENT;
		mParams.flags = 
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
		mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
		// WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE //8
		// WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED // 2^19
		// WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD // 2^22
		@SuppressWarnings("deprecation")
		Notification localNotification = new Notification(0, null, System.currentTimeMillis());
	    localNotification.flags = 32;
	    startForeground(9987, localNotification);
	    setReceivers();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		removeAllView();
		initBlockView();
	    int delay = LockScreenView.DEFAULT_DELAY;
	    	setBlock("test msg", "test sender", delay);
		return START_STICKY;
	}
	
	public static void removeAllView()
	{
		try {
			if(localWindowManager != null)
				localWindowManager.removeView(mLockView);
		} catch (Exception e) {
			LogUtil.e("RemoveView Error", e.toString());
		}
	}
	
	private void initBlockView()
	{
		if(mLockView == null)
	    	mLockView = new FrameLayout(getApplicationContext());
		mLockView.removeAllViews();
	}
	
	private void setBlock(String message, String sender, int delay)
	{
		localWindowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
    	mLockView = new FrameLayout(getApplicationContext());
		currentView = new LockScreenView(getApplicationContext(), this, message, sender, delay);
    	mLockView.addView(currentView);
	    mLockView.setVisibility(0);
	    localWindowManager.addView(mLockView, mParams);
	}
	
	private void setReceivers()
	{
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		BroadcastReceiver mReceiver = new ScreenReceiver();
		try {
			unregisterReceiver(mReceiver);
		} catch (Exception e) {
		}
		registerReceiver(mReceiver, filter);
	}
	
	synchronized public static int getServiceCount() {
		return serviceCount;
	}

}
