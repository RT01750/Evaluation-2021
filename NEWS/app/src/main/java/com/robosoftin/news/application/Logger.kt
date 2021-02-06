package com.robosoftin.news.application

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