package bongpan.andong.amsterdam.locker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import bongpan.andong.amsterdam.R;

public class SkinResourceLoader {
	public static final int METALLIC = 0;
	public static final int IRONMAN = 1;
	public static final int METALLIC_RED = 2;
	public static final int METALLIC_BLACK = 3;
	public static final int STEEL_GLASS = 4;
	public static final int N_WHITE = 5;
	public static final int STYLISH = 99;
	
	public static int getBasicSkinID() {
		return N_WHITE;
	}
	
	public static boolean isNewSkin(Context context) {
//		int skinId = 0;
		boolean b = true;
		
//		if(skinId >= N_WHITE)
//			b = true;
		
		return b;
	}
	
	public static Bitmap getButtonArrow(Context context) {
		Bitmap arrow = null;
		
		try {
			arrow = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.main_allow);
		} catch(Exception e) {
			
		}
		
		return arrow;
	}
	
	public static Bitmap getUnLockButton(Context context) {
		Bitmap lock = null;
		
		try {
			lock = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.main_lock);
		} catch(Exception e) {
			
		}
		
		return lock;
	}
	
	public static Bitmap getQuickLaunchButton(Context context) {
		Bitmap lock = null;
		
		try {
			lock = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.quick_mainicon);
		} catch(Exception e) {
			
		}
		
		return lock;
	}
	
	public static Bitmap getQuickCloseButton(Context context) {
		Bitmap lock = null;
		
		try {
			lock = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.quick_close);
		} catch(Exception e) {
			
		}
		
		return lock;
	}
	
	public static Bitmap getSelectedUnLockButton(Context context) {
		Bitmap lock = null;
		
		try {
			lock = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.main_circle);
		} catch(Exception e) {
			
		}
		
		return lock;
	}
	
	public static Bitmap getRightSlot(Context context) {
		Bitmap right = null;
		
		try {
			right = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unlock_normal);
		} catch(Exception e) {
			
		}
		
		return right;
	}
	
	public static Bitmap getSmallRightSlot(Context context) {
		Bitmap right = null;
		
		try {
			right = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unlock_small);
		} catch(Exception e) {
			
		}
		
		return right;
	}
	
	public static Bitmap getSelectedRightSlot(Context context) {
		Bitmap right = null;
		
		try {
			right = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unlock_large);
		} catch(Exception e) {
			
		}
		
		return right;
	}
	
	public static Bitmap getLockerBar(Context context) {
		Bitmap bar = null;
		
		try {
			bar = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.locker_bar);
		} catch(Exception e) {
			
		}
		
		return bar;
	}
	
	public static Bitmap getActionSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.action_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallActionSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.action_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedActionSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.action_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	
	
	
	
	
	
	
	
	public static Bitmap getSearchSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.search_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallSearchSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.search_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedSearchSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.search_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	
	
	
	
	
	
	
	
	
	public static Bitmap getAdEventSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.ad_event_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallAdEventSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.ad_event_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedAdEventSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.ad_event_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getCouponSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.coupon_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallCouponSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.coupon_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedCouponSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.coupon_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getInternetSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.internet_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallInternetSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.internet_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedInternetSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.internet_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getLikeSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.like_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallLikeSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.like_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedLikeSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.like_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getMarketSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.market_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallMarketSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.market_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedMarketSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.market_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getPhoneSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.phone_normal);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSmallPhoneSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.phone_small);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getSelectedPhoneSlot(Context context) {
		Bitmap bmp = null;
		
		try {
			bmp = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.phone_large);
		} catch(Exception e) {
			
		}
		
		return bmp;
	}
	
	public static Bitmap getClockBar(Context context) {
		Bitmap bar = null;
		
		try {
			bar = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.time_bar);
		} catch(Exception e) {
			
		}
		
		return bar;
	}
	
	public static Bitmap getCostBar(Context context) {
		Bitmap bar = null;
		
		try {
			bar = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.cost_bar);
		} catch(Exception e) {
			
		}
		
		return bar;
	}
	
	public static int getButtonBarColor(Context context) {
		int skinId = 0;
		int color;
		
		switch(skinId) {
		case METALLIC:
			color = Color.GRAY;
			break;
		case IRONMAN:
			color = Color.argb(50, 237, 28, 36);
			break;
		case STEEL_GLASS:
			color = Color.rgb(128, 130, 133);
			break;
		case STYLISH:
			color = Color.rgb(255, 215, 224);
			break;
		default:
			color = 0;
			break;
		}
		
		return color;
	}
	
	public static int getActionNameColor(Context context) {
		int skinId = 0;
		int color;
		
		switch(skinId) {
		case METALLIC:
			color = Color.rgb(88, 88, 88);
			break;
		case IRONMAN:
			color = Color.rgb(0, 0, 0);
			break;
		default:
			color = Color.WHITE;
			break;
		}
		
		return color;
	}
	
	public static Typeface getTextTypeface(Context context) {
		int skinId = 0;
		Typeface typeface;
		
		switch(skinId) {
		case METALLIC:
			typeface = Typeface.DEFAULT_BOLD;
			break;
		default:
			typeface = Typeface.DEFAULT_BOLD;
			break;
		}
		
		return typeface;
	}
	
	public static int getTextColor(Context context) {
		int skinId = 0;
		int textColor;
		
		switch(skinId) {
		case METALLIC:
			textColor = Color.WHITE;
			break;
			
		case METALLIC_RED:
			textColor = Color.WHITE;
			break;
			
		case STYLISH:
			textColor = Color.WHITE;
			break;
			
		default:
			textColor = Color.WHITE;
			break;
		}
		
		return textColor;
	}
	
	public static Bitmap getAction(Context context) {
		int skinId = 0;
		Bitmap action = null;
		
		switch(skinId)
		{
			case METALLIC:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_action);
				break;
			case IRONMAN:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_action);
				break;
			case METALLIC_RED:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_action);
				break;
			case METALLIC_BLACK:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_action);
				break;
			case STEEL_GLASS:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_action);
				break;
//			case STYLISH:
//				action = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.metallic_action);
//				break;
				
			default:
				action = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_action);
				break;
		}
		
		return action;
	}
	
	public static Bitmap getLockButton(Context context) {
		
		int skinId = 0;
		Bitmap lockButton = null;
		
		switch(skinId)
		{
			case METALLIC:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_unlock);
				break;
			case IRONMAN:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_unlock);
				break;
			case METALLIC_RED:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_unlock);
				break;
			case METALLIC_BLACK:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_unlock);
				break;
			case STEEL_GLASS:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_unlock);
				break;
//			case STYLISH:
//				lockButton = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_unlock);
//				break;
			default:
				lockButton = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_unlock);
				break;
		}
		
		return lockButton;
	}
	
	public static Bitmap getCostCoin(Context context) {
		int skinId = 0;
		Bitmap costCoin = null;
		
		switch(skinId)
		{
//			case STYLISH:
//				costCoin = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_cost_coin);
//				break;
			default:
				costCoin = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.cost_coin);
				break;
		}
		return costCoin;
	}
	
	public static Bitmap getLeftArrow(Context context) {
		int skinId = 0;
		Bitmap leftArrow = null;
		
		switch(skinId)
		{
			case METALLIC:
				leftArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.left_arrow);
				break;
			case METALLIC_RED:
				leftArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_left_arrow);
				break;
//			case STYLISH:
//				leftArrow = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_left_arrow);
//				break;
			default:
				leftArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.left_arrow);
				break;
		}
		return leftArrow;
	}
	
	public static Bitmap getRightArrow(Context context) {
		int skinId = 0;
		Bitmap rightArrow = null;
		
		switch(skinId)
		{
			case METALLIC:
				rightArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.right_arrow);
				break;
			case METALLIC_RED:
				rightArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_right_arrow);
				break;
//			case STYLISH:
//				rightArrow = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_right_arrow);
//				break;
			default:
				rightArrow = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.right_arrow);
				break;
		}
		return rightArrow;
	}
	
	public static Bitmap getAdEvent(Context context) {
		int skinId = 0;
		Bitmap adEvent = null;
		
		switch(skinId)
		{
			case METALLIC:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_ad_event);
				break;
			case IRONMAN:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_ad_event);
				break;
			case METALLIC_RED:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_ad_event);
				break;
			case METALLIC_BLACK:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_ad_event);
				break;
			case STEEL_GLASS:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_ad_event);
				break;
//			case STYLISH:
//				adEvent = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_ad_event);
//				break;
			default:
				adEvent = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_ad_event);
				break;
		}
		return adEvent;
	}
	
	public static Bitmap getCoupon(Context context) {
		int skinId = 0;
		Bitmap coupon = null;
		
		switch(skinId)
		{
			case METALLIC:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_coupon);
				break;
			case IRONMAN:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_coupon);
				break;
			case METALLIC_RED:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_coupon);
				break;
			case METALLIC_BLACK:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_coupon);
				break;
			case STEEL_GLASS:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_coupon);
				break;
//			case STYLISH:
//				coupon = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_coupon);
//				break;
			default:
				coupon = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_coupon);
				break;
		}
		return coupon;
	}
	
	public static Bitmap getInternet(Context context) {
		int skinId = 0;
		Bitmap internet = null;
		
		switch(skinId)
		{
			case METALLIC:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_internet);
				break;
			case IRONMAN:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_internet);
				break;
			case METALLIC_RED:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_internet);
				break;
			case METALLIC_BLACK:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_internet);
				break;
			case STEEL_GLASS:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_internet);
				break;
//			case STYLISH:
//				internet = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_internet);
//				break;
			default:
				internet = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_internet);
				break;
		}
		return internet;
	}
	
	public static Bitmap getMarket(Context context) {
		int skinId = 0;
		Bitmap market = null;
		
		switch(skinId)
		{
			case METALLIC:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_market);
				break;
			case IRONMAN:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_market);
				break;
			case METALLIC_RED:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_market);
				break;
			case METALLIC_BLACK:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_market);
				break;
			case STEEL_GLASS:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_market);
				break;
//			case STYLISH:
//				market = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_market);
//				break;
			default:
				market = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_market);
				break;
		}
		return market;
	}
	
	public static Bitmap getPhone(Context context) {
		int skinId = 0;
		Bitmap phone = null;
		
		switch(skinId)
		{
			case METALLIC:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_phone);
				break;
			case IRONMAN:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_phone);
				break;
			case METALLIC_RED:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_phone);
				break;
			case METALLIC_BLACK:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_phone);
				break;
			case STEEL_GLASS:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_phone);
				break;
//			case STYLISH:
//				phone = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_phone);
//				break;
			default:
				phone = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_phone);
				break;
		}
		return phone;
	}
	
	public static Bitmap getTimeBar(Context context) {
		int skinId = 0;
		Bitmap timeBar = null;
		
		switch(skinId)
		{
			case METALLIC:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_time_bar);
				break;
			case IRONMAN:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_time_bar);
				break;
			case METALLIC_RED:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_time_bar);
				break;
			case METALLIC_BLACK:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_time_bar);
				break;
			case STEEL_GLASS:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_time_bar);
				break;
//			case STYLISH:
//				timeBar = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_time_bar);
//				break;
			default:
				timeBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_time_bar);
				break;
		}
		return timeBar;
	}
	
	public static Bitmap getBackBar(Context context) {
		int skinId = 0;
		Bitmap backBar = null;
		
		switch(skinId)
		{
			case METALLIC:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_gray_bar);
				break;
			case IRONMAN:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_back_bar);
				break;
			case METALLIC_RED:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_back_bar);
				break;
			case METALLIC_BLACK:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_back_bar);
				break;
			case STEEL_GLASS:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_back_bar);
				break;
//			case STYLISH:
//				backBar = BitmapFactory.decodeResource(context.getResources(),
//						R.drawable.stylish_pink_bar);
//				break;
			default:
				backBar = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_gray_bar);
				break;
		}
		return backBar;
	}
	
	public static Bitmap getLike(Context context) {
		int skinId = 0;
		
		Bitmap like = null;
		
		switch(skinId)
		{
			case METALLIC:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_like);
				break;
			case IRONMAN:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.ironman_like);
				break;
			case METALLIC_RED:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_red_like);
				break;
			case METALLIC_BLACK:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metal_black_like);
				break;
			case STEEL_GLASS:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.steel_glass_like);
				break;
			default:
				like = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.metallic_like);
				break;
		}
		return like;
	}
}
