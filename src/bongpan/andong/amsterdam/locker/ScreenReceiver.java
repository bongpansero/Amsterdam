package bongpan.andong.amsterdam.locker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import bongpan.andong.amsterdam.util.LogUtil;
import bongpan.andong.amsterdam.util.TelephonyStatusManager;

public class ScreenReceiver extends BroadcastReceiver 
{
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		LogUtil.e("Action", action+"!!");
		if (action.equals(Intent.ACTION_SCREEN_OFF)) {
		} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			if (TelephonyStatusManager.getCallState(context) == TelephonyManager.CALL_STATE_IDLE) {
				context.startService(new Intent(context, LockService.class));
			}
		} else if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			context.startService(new Intent(context, LockService.class));
		} else if (action.equals(Intent.ACTION_USER_PRESENT)) {
			if(LockService.getServiceCount() == 0)
				context.startService(new Intent(context, LockService.class));
		} 
	}
}
