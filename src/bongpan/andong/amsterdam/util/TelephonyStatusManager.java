package bongpan.andong.amsterdam.util;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyStatusManager {
	
	private static TelephonyManager tm;
	
	private static void setTelephonyManager(Context context)
	{
		tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	public static int getCallState(Context context)
	{
		int status = -1;
		if(tm == null)
			setTelephonyManager(context);
			
		if(tm == null)
			;//Log.e("TelephonyStatus","Cannot get TelephonyManager");
		else
			status = tm.getCallState();

		return status;
	}

}
