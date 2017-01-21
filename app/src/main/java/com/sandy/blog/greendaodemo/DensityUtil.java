package com.sandy.blog.greendaodemo;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Sandy Luo on 2016/6/22.
 * <p/>
 * 工具类 用户dp于px的互相转换
 */

public class DensityUtil {
	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static float dp2px(Resources resources, float dp) {
		final float scale = resources.getDisplayMetrics().density;
		return  dp * scale + 0.5f;
	}

	public static float sp2px(Resources resources, float sp){
		final float scale = resources.getDisplayMetrics().scaledDensity;
		return sp * scale;
	}
}
