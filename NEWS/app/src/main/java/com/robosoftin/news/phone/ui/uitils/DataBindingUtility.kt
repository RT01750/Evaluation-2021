package com.robosoftin.news.phone.ui.uitils

import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.il.mcdelivery.phone.utils.ImageUtils


class DataBindingUtility {
	@BindingAdapter("networkSrc")
	fun ImageView.setImageUrl(url : String?) {
		if (URLUtil.isValidUrl(url)) {
			if (url?.contains(".gif", ignoreCase = true)!!) {
				url.let { ImageUtils.loadImageAsGIF(this, it) }
			} else {
				url.let { ImageUtils.loadImage(this, it) }
			}
		}
	}
	
	@BindingAdapter("app:imgSrc")
	fun ImageView.imageRes(resId : Int) {
		if (resId == 0) visibility = View.GONE
		else {
			setImageResource(resId)
			visibility = View.VISIBLE
		}
	}
	
	@BindingAdapter("android:visibility")
	fun setVisibility(view : View, visible : Boolean) {
		view.visibility = if (visible) View.VISIBLE else View.GONE
	}
	
	@BindingAdapter("visibility")
	fun changeVisibility(view : View, visible : Boolean) {
		view.visibility = if (visible) View.VISIBLE else View.GONE
	}
}