package bongpan.andong.amsterdam.util;

import android.util.Log;

public class LogUtil {
	
	private static final boolean VISIBILITY = true;
	
	public static void e(String tag, String log)
	{
		if(VISIBILITY)
			Log.e(tag,log+" ");
	}
	
	public static void e(String tag, int log)
	{
		if(VISIBILITY)
			Log.e(tag,log+" ");
	}
	
	public static void w(String tag, String log)
	{
		if(VISIBILITY)
			Log.w(tag,log);
	}
	
	
	public static void d(String tag, String log)
	{
		if(VISIBILITY)
			Log.d(tag,log);
	}

}
