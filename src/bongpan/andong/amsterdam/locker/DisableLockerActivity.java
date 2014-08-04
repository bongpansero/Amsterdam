package bongpan.andong.amsterdam.locker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import bongpan.andong.amsterdam.R;
import bongpan.andong.amsterdam.service.LockService;
import bongpan.andong.amsterdam.service.ScreenReceiver;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;

public class DisableLockerActivity extends Activity {

	private LockScreenWebView mWebView;
	private FrameLayout layout;
	private SurfaceView mSurfaceView;
	private ProgressBar progressBar;
	private boolean webViewLock;
	private static boolean actioned;
	private SlidingMenu menu;
	private boolean toggleOn;
	private String defaultUrl;
	private LockerDialog blockDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		getWindow().setType(2002);
		Window localWindow = getWindow();
		localWindow.addFlags(4194304);
		setContentView(R.layout.screenlock);
		webViewLock = true;
		toggleOn = false;
		ScreenReceiver.setLockDisable();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		layout = (FrameLayout)findViewById(R.id.layout);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		DisableLockerActivity.setActioned(false);
		defaultUrl = DataManager.getDefaultUrl(this);
//		setWebView();
		setMenu();
		blockDialog = new LockerDialog(this);
		blockDialog.show();
	}
	
	public boolean isToggleOn()
	{
		return toggleOn;
	}
	
	private void setMenu()
	{
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(1.0f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.setting);
        menu.setOnClosedListener(new OnClosedListener() {
			@Override
			public void onClosed() {
				toggleOn = false;
			}
		});
		
        ImageButton mButton = (ImageButton)findViewById(R.id.hamburgerButton);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
//              menu.showMenu();
              menu.toggle();
        	  toggleOn = true;
            	  
            }
        });
        
        EditText editText = (EditText)findViewById(R.id.urlEdit);
        editText.setText(defaultUrl);
        final EditText fEditText = editText;
        Button settingButton = (Button)findViewById(R.id.urlSettingButton);
        settingButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = fEditText.getText().toString();
				DataManager.setDefaultUrl(DisableLockerActivity.this, url);
				Toast.makeText(DisableLockerActivity.this, "Success", Toast.LENGTH_LONG).show();
	            menu.showContent();
        	    toggleOn = false;
        		mWebView.loadUrl(url);
        		
        		InputMethodManager inputManager = 
        		        (InputMethodManager) DisableLockerActivity.this.
        		            getSystemService(Context.INPUT_METHOD_SERVICE); 
        		inputManager.hideSoftInputFromWindow(
        		        DisableLockerActivity.this.getCurrentFocus().getWindowToken(),
        		        InputMethodManager.HIDE_NOT_ALWAYS); 
			}
		});
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void setWebView()
	{
		mWebView = new LockScreenWebView(this);
		WebSettings set = mWebView.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		set.setCacheMode(WebSettings.LOAD_DEFAULT);
		mWebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				if(progress == 100)
					progressBar.setVisibility(ProgressBar.INVISIBLE);
				else
				{
					progressBar.setVisibility(ProgressBar.VISIBLE);
					progressBar.setProgress(progress);
				}
			}
		});
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.loadUrl(defaultUrl);
		layout.addView(mWebView);
	}
	
	private void setSurfaceViewNew()
	{
		try {
			if (mSurfaceView == null) {
				mSurfaceView = new LockScreenSurfaceView(DisableLockerActivity.this); 
				mSurfaceView.setZOrderOnTop(true);
				mSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
			}
			if (layout != null) {
				((FrameLayout) layout).removeView(mSurfaceView);
				layout.addView(mSurfaceView);
			}
		}
		catch (Exception e) {
		}
	}
	
	public void unlock()
	{
		try {
			layout.removeAllViews();
		} catch (Exception e) {
		}
		finish();
	}
	
	public void doAction()
	{
		try {
			layout.removeView(mSurfaceView);
			DisableLockerActivity.setActioned(true);
			webViewLock = false;
		} catch (Exception e) {
		}
	}
	
	public boolean isWebViewLock()
	{
		return webViewLock;
	}
	
	@Override
	protected void onResume() {
		try {
			if(!blockDialog.isShowing())
				blockDialog.show();
			getWindow().addFlags(524288);
			if(!DisableLockerActivity.getActioned())
				setSurfaceViewNew();
		} catch (Exception e) {
		}
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		if(blockDialog != null)
		{
			try {
				blockDialog.dismiss();
			} catch (Exception e) {
				Log.e("blockDialog Dismiss Exception",e.toString());
			}
		}
				
		ScreenReceiver.setLockEnable();
		LockService.setActivityCount(0);
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		ScreenReceiver.setLockEnable();
		LockService.setActivityCount(0);
		super.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		if (menu!=null && menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}
	
	public static void setActioned(boolean actioned)
	{
		DisableLockerActivity.actioned = actioned;
	}
	
	public static boolean getActioned()
	{
		return DisableLockerActivity.actioned;
	}

}
