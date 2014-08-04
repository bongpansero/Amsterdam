package bongpan.andong.amsterdam.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import bongpan.andong.amsterdam.locker.DisableLockerActivity;
import bongpan.andong.amsterdam.util.LogUtil;
import bongpan.andong.amsterdam.util.TelephonyStatusManager;

public class ScreenReceiver extends BroadcastReceiver 
{
	private static boolean lockEnable = true;
	@Override
	public void onReceive(Context context, Intent intent) {
		boolean runService = false;
		String action = intent.getAction();
		LogUtil.e("Action", action+"!!");
		Bundle dataBundle = new Bundle();
		dataBundle.putString("ACTION", action);
		if (action.equals(Intent.ACTION_SCREEN_ON)) {
		} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			if (TelephonyStatusManager.getCallState(context) == TelephonyManager.CALL_STATE_IDLE && lockEnable) {
				runService = true;
			}
			else if(!lockEnable)
				DisableLockerActivity.setActioned(false);
		} else if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			context.startService(new Intent(context, LockService.class));
		} else if (action.equals(Intent.ACTION_USER_PRESENT)) {
			runService = true;
		} 
		if(runService)
		{
			Intent serviceIntent = new Intent(context, LockService.class);
			serviceIntent.putExtras(dataBundle);
			context.startService(serviceIntent);
		}
	}
	
	public static boolean getLockEnable()
	{
		return lockEnable;
	}
	
	public static void setLockDisable()
	{
		lockEnable = false;
	}
	
	public static void setLockEnable()
	{
		lockEnable = true;
	}
	
}
