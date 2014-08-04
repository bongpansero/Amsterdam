package bongpan.andong.amsterdam.locker;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.webkit.WebView;

@SuppressLint("ViewConstructor")
public class LockScreenWebView extends WebView{

	DisableLockerActivity mActivity;
	
	public LockScreenWebView(DisableLockerActivity context) {
		super(context);
		mActivity = context;
	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(this.canGoBack())
                	this.goBack();
                else if(!mActivity.isWebViewLock())
                    mActivity.unlock();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
