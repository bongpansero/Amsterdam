package bongpan.andong.amsterdam.util;

import java.io.FileInputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public final class BitmapUtils {
	
	public static float ratioX = 0; 
	public static float ratioY = 0;
	
	public enum RECT_TYPE {
		LTWH, LTRB,
	}
	
	public static void setRatio(float ratioX, float ratioY) {
		BitmapUtils.ratioX = ratioX;
		BitmapUtils.ratioY = ratioY;
	}
	
	public static int calcX(int x) { return (int)(x * ratioX); }
	public static int calcY(int y) { return (int)(y * ratioY); }
	
	public static Rect createRect(int x1, int y1, int x2, int y2, RECT_TYPE type) {
		Rect rect = null;
		switch (type) {
		case LTWH:  // left, top, width, height
			rect = new Rect(calcX(x1), calcY(y1), calcX(x1 + x2), calcY(y1 + y2));
			break;
		case LTRB:  // left, top, right, bottom
			rect = new Rect(calcX(x1), calcY(y1), calcX(x2), calcY(y2));
			break;			
		}
		return rect;
	}
	
	//-- --//
	public static Bitmap createScaledBitmap(Bitmap src, int dstWidth, int dstHeight) {
		Bitmap bm = null;
		try {
			bm = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, true);
		}
		catch (Exception e) {}
		return bm;
	}
	
	public static Bitmap createScaledBitmap(Resources res, int id, int dstWidth, int dstHeight) {					
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		opts.inSampleSize = 1;
		opts.inPurgeable = true;
		opts.inInputShareable = true;
//		opts.inTempStorage = new byte[16 * 1024];			
		Bitmap bm = null;
		try {
			Bitmap src = BitmapFactory.decodeResource(res, id, opts);
			bm = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, true);
			src = null;
//			if (src != null
//					&& src != bm)
//				src.recycle();
		}
		catch (Exception e) {}
		return bm;
	}
	
	public static Bitmap createScaledBitmap(String pathName, int dstWidth, int dstHeight) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//		opts.inSampleSize = 1;
//		opts.inPurgeable = true;
//		opts.inInputShareable = true;
//		opts.inTempStorage = new byte[16 * 1024];			
		Bitmap bm = null;
		try {
			FileInputStream fis = new FileInputStream(pathName);
			Bitmap src = BitmapFactory.decodeStream(fis, null, opts); 
			bm = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, true);
			src = null;
//			if (src != null
//					&& src != bm)
//				src.recycle();
		}
		catch (Exception e) {}
		return bm;
	}	
	
	public static Bitmap createBitmap(Resources res, int id) {					
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
//		opts.inSampleSize = 1;
//		opts.inPurgeable = true;
//		opts.inInputShareable = true;
//		opts.inTempStorage = new byte[16 * 1024];			
		Bitmap bm = null;
		try {
			bm = BitmapFactory.decodeResource(res, id, opts);
		}
		catch (Exception e) {}
		return bm;
	}	
}
