/**  
 * Copyright © 2013 WHTY Tech Co. Ltd. All rights reserved.
 *
 * @Title: DiapalyUtils.java
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月4日 上午10:32:50 
 */
package com.hh.kit.utils;

import android.content.res.Resources;

/**
 * @ClassName: DiapalyUtils
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月4日 上午10:32:50
 */
public class DisplayUtils {
	public DisplayUtils() {
	}

	public static final float DENSITY;

	static {
		DENSITY = Resources.getSystem().getDisplayMetrics().density;
	}

	public static int pixelToDip(int pixel) {
		return (int) (((float) pixel - 0.5F) / DENSITY);
	}

	public static int dipToPixel(int dip) {
		return (int) ((float) dip * DENSITY + 0.5F);
	}

}
