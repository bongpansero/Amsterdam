package bongpan.andong.amsterdam.locker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;

public class LockerDialog extends Dialog{
	
	Context mContext;

	public LockerDialog(Context context) {
		super(context);
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);	
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		getWindow().setBackgroundDrawable(new ColorDrawable(0));
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		this.mContext = context;
	}
	
	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		requestWindowFeature(1);
	    setCancelable(false);
	    setCanceledOnTouchOutside(false);
	}
}
