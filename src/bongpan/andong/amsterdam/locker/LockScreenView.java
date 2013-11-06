package bongpan.andong.amsterdam.locker;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import bongpan.andong.amsterdam.R;
import bongpan.andong.amsterdam.util.LogUtil;

public class LockScreenView extends View{

	public static final int DEFAULT_DELAY = 30;
	public static final int TUTORIAL_DELAY = 5;
	private static final int LINE_LENGTH = 7;
	private static final float LINE_HEIGHT = 0.9f;
	private static final int TEXT_SIZE = 100;
	private int mWidth = -1;
	private int mHeight = -1;
	private Paint bgPaint;
	private Paint txtPaint;
	private long startTime;
	private Runnable mRunnable;
	private Handler mHandler;
    private	LockService blockService;
    private boolean callRemove = false;
    private String message = "";
    private String sender = "";
    private int delay = -1;
    private Bitmap bgBitmap = null;
    private Rect dstRect = null;
    private ArrayList<String> msgList = null;
	public LockScreenView(Context context, LockService blockService, String message, String sender, int delay) {
		super(context);
		this.blockService = blockService;
		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inSampleSize = 5;
		bgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.block_lockscreen, options);
		bgPaint = new Paint();
		txtPaint = new Paint();
		dstRect = new Rect();
		msgList = new ArrayList<String>();
		this.message = message;
		this.sender = sender;
		this.delay = delay;
		bgPaint.setColor(Color.WHITE);
		txtPaint.setColor(Color.BLACK);
		txtPaint.setTextSize(TEXT_SIZE);
		txtPaint.setAntiAlias(true);
		txtPaint.setTextAlign(Align.CENTER);
//		txtPaint.setTypeface(MainApplication.getInstance().getDefaultFont());
		startTime = System.currentTimeMillis();
		setMsgAlign();
		mHandler = new Handler();
		mRunnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
		
	}
	
	@Deprecated
	public LockScreenView(Context context)
	{
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(mWidth == -1 || mHeight == -1)
		{
			mWidth = getWidth();
			mHeight = getHeight();
			dstRect.set(0, 0, mWidth, mHeight);
		}
		
		long currentTime = System.currentTimeMillis();
		long diff = currentTime - startTime;
		long countDown = delay - (diff/1000);
		float lineHeight = 2;
		
		if(countDown > 0)
		{
//			canvas.drawBitmap(bgBitmap,null,dstRect,bgPaint);
			//canvas.drawRect(0, 0, mWidth, mHeight, bgPaint);
//			canvas.drawText(countDown+"", mWidth/2, mHeight/4.2f, txtPaint);
//			canvas.drawText(sender+"", mWidth/2, mHeight/2.3f, txtPaint);
			for(String message:msgList)
			{
//				canvas.drawText(message+"", mWidth/2, mHeight/lineHeight, txtPaint);
				lineHeight *= LINE_HEIGHT;
			}
			mHandler.postDelayed(mRunnable, 1000);
		}
		else if(!callRemove)
		{
			LockService.removeAllView();
			callRemove = true;
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.e("Touch!", event.toString());
		return super.onTouchEvent(event);
	}
	
	private void setMsgAlign()
	{
		int start = 0;
		while(start+LINE_LENGTH < message.length())
		{
			msgList.add(message.substring(start,start+LINE_LENGTH));
			start += LINE_LENGTH;
		}
		String remainStr = "";
		try {
			remainStr = message.substring(start, message.length());
		} catch (Exception e) {
		}
		if(remainStr != "")
			msgList.add(remainStr);
		
	}

}
