package bongpan.andong.amsterdam.locker;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataManager {
	
	private static String SETTING_NAME = "br_setting";
	private static String SETTING_DEFAULT_URL = "default_url";
	private static String DEFAULT_URL = "http://m.naver.com";
	private static SharedPreferences preference;
	private static Editor editor;
	
	private static void setDataManager(Context context)
	{
		if(preference == null)
			preference = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
		if(editor == null)
			editor = preference.edit();
	}
	
	public static void setDefaultUrl(Context context, String defaultUrl)
	{
		setDataManager(context);
		editor.putString(SETTING_DEFAULT_URL, defaultUrl);
		editor.commit();
	}
	
	public static String getDefaultUrl(Context context)
	{
		setDataManager(context);
		return preference.getString(SETTING_DEFAULT_URL, DEFAULT_URL);
	}

}
