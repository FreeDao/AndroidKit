/**  
 * Copyright © 2013 武汉天喻通讯技术有限公司. All rights reserved.
 *
 * @Title: CommonApplication.java
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午1:55:48
 */
package com.hh.kit;

import android.app.Application;
import android.content.Context;

import com.hh.kit.utils.PreferencesUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @ClassName: CommonApplication
 * @Description: TODO
 * @author: Mr.Gdp
 * @date: 2013年11月1日 下午1:55:48
 */
public class CommonApplication extends Application {
	public static CommonApplication mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		PreferencesUtils.init(mInstance);
		initImageLoader();
	}

	public static CommonApplication getInstance() {
		return mInstance;
	}

	public static CommonApplication getApplication(Context context) {
		return (CommonApplication) context.getApplicationContext();
	}

	private void initImageLoader() {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				// .discCache(new
				// FileCountLimitedDiscCache(StorageUtils.getOwnCacheDirectory(getApplicationContext(),
				// "/wicity/images"),1024))
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO) // common
				.build();
		// Initialize ImageLoader with configuration.
		com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(
				config);
	}

}
