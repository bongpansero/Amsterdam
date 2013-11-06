package bongpan.andong.amsterdam;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import bongpan.andong.amsterdam.locker.LockService;
import bongpan.andong.amsterdam.util.LogUtil;

public class MainApplication extends Application{
	private static MainApplication sInstance;
    private Typeface typeface;
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		Intent intent = new Intent(getApplicationContext(),LockService.class);
		startService(intent);
	}
	
	public static MainApplication getInstance()
	{
		return sInstance;
	}

	public Typeface getDefaultFont() {		
		if (typeface == null) {
			try {
				String path = String.format("fonts/%s.ttf", "GodoB");
				path += ".mp3";  // FIXED: froyo 이하에서는 폰트 크기가 2MB 이하로 제한됨, 확장자를 미디어 타입인 mp3로 변경하여 해결
				typeface = Typeface.createFromAsset(getAssets(), path);
			}
			catch (Exception e) {
				LogUtil.e("Font error", e.toString());
			}
		}
		return typeface;
	}

}
