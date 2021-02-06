/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * Last modified : 8/9/20 2:07 PM
 * Author :  Akshay [akshay.prabhu@robosoftin.com]
 */

package com.il.data.utils

import android.util.Log
import com.robosoftin.news.BuildConfig

object Logger {
	private fun isDebug() = BuildConfig.DEBUG
	
	
	fun d(tag : String?, message : String) {
		if (isDebug()) Log.d(tag, message)
	}
	
	fun e(tag : String?, message : String) {
		if (isDebug()) Log.e(tag, message)
	}
	
	fun v(tag : String?, message : String) {
		if (isDebug()) Log.v(tag, message)
	}
	
	fun printStackTrace(`in` : Exception) {
		if (isDebug()) `in`.printStackTrace()
	}
}