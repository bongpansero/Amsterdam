package bongpan.andong.amsterdam.locker;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import bongpan.andong.amsterdam.MainApplication;
import bongpan.andong.amsterdam.R;
import bongpan.andong.amsterdam.util.BitmapUtils;
import bongpan.andong.amsterdam.util.Size;

public class SlideSelector 
{
	public static final float DATE_TEXT_SIZE_RATIO = 30/1230.0f;
	public static final float DATE_TEXT_HEIGHT_RATIO = 60/1230.0f;
	public static final float TIME_TEXT_SIZE_RATIO = 109/1230.0f;
	public static final float TIME_TEXT_HEIGHT_RATIO = 170/1230.0f;
	public static final float COSTBAR_Y_RATIO = (1100 + 40)/1230.0f;
	public static final float COSTBAR_HEIGHT_RATIO = 35/1230.0f;
	public static final float LOCKBAR_Y_RATIO = 990/1230.0f;
	public static final float TIMEBAR_Y_RATIO = 20/1230.0f;
	public static final float TIMEBAR_HEIGHT_RATIO = 180/1230.0f;
	public static final float BUTTON_Y_RATIO = 1080.0f/1230.0f;
	public static final float QUICK_BACKGROUND_WIDTH_HEIGHT_RATIO = 2/9.0f;
	public static final float SLIDE_BTN_Y_RATIO = 1080.0f/1230.0f;
	public static final float LEFT_SLOT_X_RATIO = 137.0f / 720.0f;
	public static final float RATIO = 0.87f;
	public static final float MAX_WIDTH = 720.f;
	public static final float MAX_HEIGHT = 1230.f;
	public static final float LOCK_BUTTON_RADIUS_RATIO = 120 / MAX_HEIGHT;
	public static final float LOCK_BUTTON_BG_RADIUS_H_RATIO = 90 / MAX_HEIGHT;
	public static final float LOCK_BUTTON_RADIUS_H_RATIO = 64 / MAX_HEIGHT;
	public static final float BACKGROUND_Y_RATIO = 113.0f / 1230.0f;
	public static final float BACKGROUND_BOTTOM_Y_RATIO = 160.0f / 1230.0f;
	public static final float NAVI_X_RATIO = 124.0f / 720.0f;
	public static final float BACKGROUND_TOUCH = 690/1230.0f;
	public static final float BACKGROUND_BOTTOM_RATIO = 300 / 1230.0f;
	public static final float LOCK_BUTTON_ARROW_W = 64.f / 720.0f;
	public static final float LOCK_BUTTON_ARROW_H = 24.f / 1230.0f;

	private static final String[] DAY_OF_WEEK = {"","일요일","월요일","화요일","수요일","목요일","금요일","토요일"};	
	private static final int STATE_NORMAL = 0;
	private static final int STATE_HIGHLIGHTED = 1;
	private static final int STATE_SELECTED = 2;
	
	float backgroundYRatiod = 0;
	
	private Point pNaviBtn;
	private Point pActionSlot;
	private Point pUnlockSlot;
	private Point pCenter;
	private int radius = 0;
	private int slotBgRadius = 0;
	private int slotRadius = 0;
	private int arrorW = 0;
	private int arrorH = 0;
	private boolean bTouchMoved = false;
	private Paint pnt;
	private Paint txtPnt;
	private Paint tglPnt;
	
	//-- --//
	private Bitmap backgroundBitmap = null;
	private Bitmap[] naviBtnBitmap = new Bitmap[4];
	private Bitmap[] arrowUpBitmap = new Bitmap[4];
	private Bitmap[] arrowDownBitmap = new Bitmap[4];
	private Bitmap[] actionLinkBitmap = new Bitmap[4];
	private Bitmap[] actionDownloadBitmap = new Bitmap[4];
	private Bitmap[] actionPlayBitmap = new Bitmap[4];
	private Bitmap[] actionFacebookBitmap = new Bitmap[4];
	private Bitmap[] actionContentsBitmap = new Bitmap[4];
	private Bitmap[] actionUnlockBitmap = new Bitmap[4];
	private Bitmap slotBgBitmap = null;
	private int alpha = 255;
	private int nTouchAlpha = 255;

	class CustomSlot 
	{
		public Bitmap bitmap = null;
		public Point origin = new Point();
	}
	private CustomSlot[] customSlots = null;
	
	
	private Context mContext;
	
	private boolean bQuickLauncherEnable = false;
    private LockScreenSurfaceView surfaceView;    
    private int rootWt = 0;
    private int rootHt = 0;
		
	public SlideSelector(LockScreenSurfaceView surfaceView) {	
		this.surfaceView = surfaceView;
		int wt = surfaceView.getWidth(), ht = surfaceView.getHeight();
				
		rootWt = wt;
		rootHt = ht;
		
		radius = (int)(rootWt * LOCK_BUTTON_RADIUS_RATIO);
		slotRadius = (int)(rootWt * LOCK_BUTTON_RADIUS_H_RATIO); 
		slotBgRadius = (int)(rootWt * LOCK_BUTTON_BG_RADIUS_H_RATIO);
		
		arrorW = (int)(rootWt * LOCK_BUTTON_ARROW_W);
		arrorH = (int)(rootHt * LOCK_BUTTON_ARROW_H);
		
		
		pCenter = new Point(rootWt / 2, (int)(rootHt * BUTTON_Y_RATIO));
		pNaviBtn = new Point(rootWt / 2, (int)(rootHt * BUTTON_Y_RATIO));
		pActionSlot = new Point((int)(rootWt * LEFT_SLOT_X_RATIO), pNaviBtn.y);
		pUnlockSlot = new Point(rootWt - pActionSlot.x, pNaviBtn.y);
		
		backgroundYRatiod = 0.5f + BACKGROUND_Y_RATIO;
		
		//-- --//
		this.pnt = new Paint();
		pnt.setAntiAlias(true);
		pnt.setDither(true);
		
		this.txtPnt = new Paint();
		this.txtPnt.setTypeface(MainApplication.getInstance().getDefaultFont());
		this.txtPnt.setAntiAlias(true);
		this.txtPnt.setColor(Color.WHITE);
		txtPnt.setDither(true);

		tglPnt = new Paint();
//		tglPnt.setAntiAlias(true);
		tglPnt.setDither(true);
		tglPnt.setAlpha(alpha);

		bQuickLauncherEnable = false;
	}
	
	
	
	
	//-- --//
	private Size adSize;
	
	/**
	 * 
	 * @param surfaceSize
	 * @param adSize
	 */
	public void setBaseSize(Size surfaceSize, Size adSize) {
		this.adSize = adSize;
	}
	
	public void arrangeControls() {
		pNaviBtn.x = pCenter.x;
		pNaviBtn.y = pCenter.y;
		
//		LogUtils.i(LOG_TAG, "point=(%d,  %d)", pNaviBtn.x, pNaviBtn.y);
	}
	
	public void rearrangeControls() {
		bTouchMoved = false;
		arrangeControls();
	}
	
	/**
	 * 
	 * @param context
	 */
	public void setBitmap(Context context) {		
		this.mContext = context;		
		loadBitmap(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isQuickLauncherEnable() {
		return bQuickLauncherEnable;
	}
	
	/**
	 * 
	 * @param context
	 */
	public void loadBitmap(Context context) {
		//-- --//		
		backgroundBitmap = BitmapUtils.createScaledBitmap(context.getResources(), 
				R.drawable.renew_bg_navi_gradation, adSize.width, (int)(radius * 2.84));
		
		slotBgBitmap = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_frame, slotBgRadius * 2, slotBgRadius * 2);
		
		// 
		naviBtnBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_selector_n, radius * 2, radius * 2);
		naviBtnBitmap[STATE_HIGHLIGHTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_frame, slotBgRadius * 2, slotBgRadius * 2);
		naviBtnBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_selector_s, slotBgRadius * 2, slotBgRadius * 2);
		
		//
		actionLinkBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_link_n, slotRadius * 2, slotRadius * 2);
		actionLinkBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_link_s, slotRadius * 2, slotRadius * 2);
		
		actionDownloadBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_download_n, slotRadius * 2, slotRadius * 2);
		actionDownloadBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_download_s, slotRadius * 2, slotRadius * 2);
		
		actionPlayBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_play_n, slotRadius * 2, slotRadius * 2);
		actionPlayBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_play_s, slotRadius * 2, slotRadius * 2);
		
		actionFacebookBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_facebook_n, slotRadius * 2, slotRadius * 2);
		actionFacebookBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_facebook_s, slotRadius * 2, slotRadius * 2);
		
		actionContentsBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_contents_n, slotRadius * 2, slotRadius * 2);
		actionContentsBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_contents_s, slotRadius * 2, slotRadius * 2);		
		
		actionUnlockBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_unlock_n, slotRadius * 2, slotRadius * 2);
		actionUnlockBitmap[STATE_SELECTED] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.renew_btn_action_unlock_s, slotRadius * 2, slotRadius * 2);		
		
		//-- --//
		arrowUpBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.update_arrows_up, arrorW, arrorH);
		arrowDownBitmap[STATE_NORMAL] = BitmapUtils.createScaledBitmap(context.getResources(),
				R.drawable.update_arrows_down, arrorW, arrorH);
		
	}
	
	public void drawSimple(Canvas canvas) {
		
		if(!((DisableLockerActivity)mContext).isToggleOn())
		{
			drawBackground(canvas);
			drawNaviButton(canvas);
			drawActionSlot(canvas);
			drawUnlockSlot(canvas);
			drawTime(canvas);
			drawDate(canvas);
		}
	}
	
	public void drawBackground(Canvas canvas) {		
		try {
			if (canvas != null 
					&& backgroundBitmap != null) {
				canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
				canvas.drawColor(Color.argb(88, 0, 0, 0));
			}			
		} 
		catch (Exception e) {
		}
	}
	
	public void drawTime(Canvas canvas) {
		int rootWt = canvas.getWidth(), rootHt = canvas.getHeight();
		int wt = 0, ht = 0;
		ht = (int) (rootHt * LockScreenSurfaceView.DEFAULT_AD_RATIO);
		if (surfaceView != null
				&& surfaceView.isScaleEnable() == true) {
			wt = (int) (ht * LockScreenSurfaceView.ASPECT_RATIO);
		}
		else {
			wt = (int) (rootWt * LockScreenSurfaceView.DEFAULT_AD_RATIO);
		}
		
		Calendar calendar = Calendar.getInstance();
				
		int hour = calendar.get(Calendar.HOUR);
			if (hour == 0) hour = 12;
			
		int minute = calendar.get(Calendar.MINUTE);
		String ampm = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM ": "PM ";		
		String time = String.format("%d:%s", hour, padStr(minute, "0", 2));

		Rect bounds = new Rect();
		try { 
			int pos_x = 0, pos_y = 0;		
			if (canvas != null) {
				txtPnt.setColor(Color.WHITE);
				txtPnt.setTextAlign(Align.LEFT);
				txtPnt.setTextSize(80.f / 1230.f * rootHt);
//				txtPnt.setTypeface(MainApplication.futuraStdMedium);
				txtPnt.getTextBounds(time, 0, time.length(), bounds);				
				pos_x = (rootWt - wt) / 2;
				pos_y = mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_time_text_margin_top) + bounds.height();
				canvas.drawText(time, pos_x, pos_y, txtPnt);
				
				if (hour < 10) time = "0:00";
				else time = "00:00";
				txtPnt.getTextBounds(time, 0, time.length(), bounds);
				pos_x += bounds.width() + mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_time_text_padding);
					
				txtPnt.setTextSize(32.f / 1230.f * rootHt);
				txtPnt.setTypeface(MainApplication.getInstance().getDefaultFont());
				txtPnt.getTextBounds(ampm, 0, ampm.length(), bounds);
				pos_y = mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_time_ampm_text_margin_top) + bounds.height();
				canvas.drawText(ampm, pos_x, pos_y, txtPnt);
			}
		}
		catch (Exception e) {}		
	}
	
	public String padStr(int val, String pad, int len) {
		  String str = Integer.toString(val);
		  while (str.length() < len)
		    str = pad + str;
		  return str;
	}
	
	public void drawDate(Canvas canvas) {		
		int rootWt = canvas.getWidth(), rootHt = canvas.getHeight();
		int wt = 0, ht = 0;
		ht = (int) (rootHt * LockScreenSurfaceView.DEFAULT_AD_RATIO);
		if (surfaceView != null
				&& surfaceView.isScaleEnable() == true) {
			wt = (int) (ht * LockScreenSurfaceView.ASPECT_RATIO);
		}
		else {
			wt = (int) (rootWt * LockScreenSurfaceView.DEFAULT_AD_RATIO);
		}
		
		Calendar calendar = Calendar.getInstance();
		
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int weekdays = calendar.get(Calendar.DAY_OF_WEEK);
			
		String date = new StringBuilder()
			.append(month + 1).append(".")
			.append(day).toString();
		String weekday = DAY_OF_WEEK[weekdays];
		
		Rect bounds = new Rect();				
		try {
			int pos_x = 0, pos_y = 0;
			if (canvas != null) {
				txtPnt.setColor(Color.WHITE);
				txtPnt.setTextAlign(Align.LEFT);
				txtPnt.setTextSize(32.f / 1230.f * rootHt);
				txtPnt.setTypeface(MainApplication.getInstance().getDefaultFont());
				txtPnt.getTextBounds(weekday, 0, weekday.length(), bounds);				
				pos_x = (rootWt - wt) / 2 + wt - bounds.width();
				pos_y = mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_time_text_margin_top) + bounds.height();
				canvas.drawText(weekday, pos_x, pos_y, txtPnt);
				
				txtPnt.setTextSize(30.f / 1230.f * rootHt);
//				txtPnt.setTypeface(MainApplication.futuraStdMedium);
				txtPnt.getTextBounds(date, 0, date.length(), bounds);
				pos_x -= mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_date_text_padding) + bounds.width();
				pos_y = mContext.getResources()
						.getDimensionPixelSize(R.dimen.lockscreen_time_ampm_text_margin_top) + bounds.height();
				canvas.drawText(date, pos_x, pos_y, txtPnt);
			}
		}
		catch (Exception e) {}
	}
	
	public void drawNaviButton(Canvas canvas) {	
		try {
			if (canvas != null) {
				if (isTouchMoved() == true) {
					Rect canvasRect = makeRect(pNaviBtn, slotBgRadius);
					if (customSlots != null) {
						for (CustomSlot slot : customSlots) {
							if (hitTest(slot.origin)) {
								pnt.setAlpha(0);
								break;
							}
						}
					}
					if (isActionSlotSelected() == true
							|| isUnlockSlotSelected() == true)
						canvas.drawBitmap(naviBtnBitmap[STATE_SELECTED], null, canvasRect, pnt);
					else 
						canvas.drawBitmap(naviBtnBitmap[STATE_HIGHLIGHTED], null, canvasRect, pnt);
				}
				else {			
					Rect canvasRect = makeRect(pNaviBtn, radius);
					canvas.drawBitmap(naviBtnBitmap[STATE_NORMAL], null, canvasRect, pnt);
				}
				pnt.setAlpha(255);
			}
		}
		catch (Exception e) {}
	}
	
	public void drawCustomSlot(Canvas canvas) {	
		if (isTouchMoved() == true) {
			try {	
				for (int i = 0; i < customSlots.length; i++) {
					Rect canvasRect = makeRect(customSlots[i].origin, slotBgRadius);
					if (canvas != null
							&& customSlots[i].bitmap != null) {
						if (!hitTest(customSlots[i].origin)) {
							tglPnt.setAlpha(nTouchAlpha);
							canvas.drawBitmap(customSlots[i].bitmap, null, canvasRect, tglPnt);
						}
						else { 
							tglPnt.setAlpha(160);
							canvas.drawBitmap(customSlots[i].bitmap, null, canvasRect, tglPnt);
						}
					}
				}
			}
			catch (Exception e) {}
		}
	}
	
	public void drawActionSlot(Canvas canvas) {
		try {
			if (canvas != null) {
				Rect canvasRect;
				if (isTouchMoved() == true
						&& isActionSlotSelected() != true) {
					canvasRect = makeRect(pActionSlot, slotBgRadius);
					if (canvas != null)
						canvas.drawBitmap(slotBgBitmap, null, canvasRect, pnt);
				}
				canvasRect = makeRect(pActionSlot, slotRadius);
				if (isActionSlotSelected() == true)				
					canvas.drawBitmap(actionLinkBitmap[STATE_SELECTED], null, canvasRect, pnt);
				else
					canvas.drawBitmap(actionLinkBitmap[STATE_NORMAL], null, canvasRect, pnt);
			}
		}
		catch (Exception e) {}
	}
	
	public void drawUnlockSlot(Canvas canvas) {
		Rect canvasRect;
		try {			
			if (canvas != null) {
				if (isTouchMoved() == true
						&& isUnlockSlotSelected() != true) {
					canvasRect = makeRect(pUnlockSlot, slotBgRadius);
					canvas.drawBitmap(slotBgBitmap, null, canvasRect, pnt);
				}		
				canvasRect = makeRect(pUnlockSlot, slotRadius);
				if (isUnlockSlotSelected() == true)
					canvas.drawBitmap(actionUnlockBitmap[STATE_SELECTED], null, canvasRect, pnt);
				else
					canvas.drawBitmap(actionUnlockBitmap[STATE_NORMAL], null, canvasRect, pnt);
			}
		}
		catch (Exception e) {}
	}
	
	public boolean isActionSlotSelected() {
		return hitTest(pActionSlot);
	}
	
	public boolean isUnlockSlotSelected() {
		return hitTest(pUnlockSlot);
	}
	
	public void setPressed(boolean isPressed) {
		bTouchMoved = isPressed;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean isTouchMoved() {
		return bTouchMoved;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSelected() {
		boolean result = false;
		if (isActionSlotSelected() == true
				|| isUnlockSlotSelected() == true)
			result = true;
		return result;
	}
	
	public void move(int pos_x, int pos_y) {
		Point current = new Point(pos_x, pos_y);
		int radius = pCenter.x - pActionSlot.x;
		int height = pCenter.y - current.y;
		
		if (isQuickLauncherEnable() && customSlots != null && height > 0) {
			double d = distance(current, pCenter);
			if (d > radius) {
				double theta = Math.asin((double)height / (double)d);
				int x = current.x < pCenter.x ? (int)(pCenter.x - radius * Math.cos(theta))
											  : (int)(pCenter.x + radius * Math.cos(theta));
				current.set(x, (int)(pCenter.y - radius * Math.sin(theta)));
			}

			for (int i = 0; i < customSlots.length; i++) {
				if (hitTest(current, customSlots[i].origin)) {
					current.set(customSlots[i].origin.x, customSlots[i].origin.y);
					break;
				}
			}
			
		} else {
			int x = current.x;
			if (current.x < pCenter.x - radius) {
				x = pCenter.x - radius;
			}
			if (current.x > pCenter.x + radius) {
				x = pCenter.x + radius;
			}
			current.set(x, pCenter.y);
		}

		if (hitTest(current, pActionSlot)) {
			current.set(pActionSlot.x, pActionSlot.y);
		} else if (hitTest(current, pUnlockSlot)) {
			current.set(pUnlockSlot.x, pUnlockSlot.y);		
		}

		pNaviBtn.set(current.x, current.y);
	}
	
	public void returnBack() {
		rearrangeControls();
	}	
	
	public int getCustomSlotId() {
		if (isQuickLauncherEnable() == true
				&& customSlots != null) {
			for (int i = 0; i < customSlots.length; i++) {
				if (hitTest(customSlots[i].origin))
					return i;
			}
		}
		return -1;
	}
	
	public static Rect makeRect(Point center, int width, int height) {
		Rect rect = new Rect(center.x - width / 2, center.y - height / 2,
				center.x + width / 2, center.y + height / 2);
		return rect;
	}
	
	// TODO: 라이브러리로 이동
	public static Rect makeRect(Point center, int radius) {
		Rect rect = new Rect(center.x - radius , center.y - radius,
				center.x + radius, center.y + radius);
		return rect;
	}	
		
	private double distance(Point point1, Point point2) {
		double result = 0;
		if (point1 != null && point2 != null)
			result = Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + 
					(point1.y - point2.y) * (point1.y - point2.y));
		return result;
	}

	private double distanceFromTouch(Point point) {
		return distance(pNaviBtn, point);
	}
	
	public boolean hitTest(Point point1, Point point2) {
		boolean result = false;
		if (distance(point1, point2) < radius) 
			result = true;
		return result;
	}
	
	public boolean hitTest(Point point) {
		boolean result = false;
		if (distanceFromTouch(point) < radius) 
			result = true;
		return result;
	}
}
