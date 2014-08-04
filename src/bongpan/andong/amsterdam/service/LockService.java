package bongpan.andong.amsterdam.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import bongpan.andong.amsterdam.R;
import bongpan.andong.amsterdam.locker.DisableLockerActivity;
import bongpan.andong.amsterdam.main.MainActivity;
import bongpan.andong.amsterdam.util.LogUtil;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class LockService extends Service{
	
	private static int serviceCount = 0;
	private static int activityCount = 0;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(getApplicationContext());
			notiBuilder.setSmallIcon(R.drawable.ic_launcher);
			notiBuilder.setContentTitle("Test");
			notiBuilder.setContentText("Test");
			notiBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
			notiBuilder.setPriority(Notification.PRIORITY_MIN);
			Intent inAppIntent = new Intent(getApplicationContext(), MainActivity.class);
			inAppIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, inAppIntent,0);
			notiBuilder.setContentIntent(pi);
			Notification noti = notiBuilder.build();
			noti.flags = Notification.FLAG_NO_CLEAR;
			startForeground(9987, noti);
			if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.JELLY_BEAN_MR2) {
	        	new Handler().post(new Runnable() {
	        		public void run() {
	        			try {
	        				((NotificationManager) LockService.this.getSystemService("notification")).notify(9987, new Notification());
	        			} catch (Exception e) {}
	        		}
	        	});
	        }
		} catch (Exception e) {
			LogUtil.e("startForeground Exception", e.toString());
		}
	    setReceivers();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent != null)
		{
			Bundle data = intent.getExtras();
			if(data != null)
			{
				String action = data.getString("ACTION");
				if(action.equals(Intent.ACTION_SCREEN_OFF))
					setBlock();
			}
		}
		return START_STICKY;
	}
	
	private void setBlock()
	{
		if(LockService.getActivityCount() <= 0)
		{
			Intent disableIntent = new Intent(getApplicationContext(), DisableLockerActivity.class);
			disableIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(disableIntent);
		}
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
	
	synchronized public static int getActivityCount() {
		return activityCount;
	}
	
	synchronized public static void setActivityCount(int count) {
		activityCount = count;
	}
	
	

}
