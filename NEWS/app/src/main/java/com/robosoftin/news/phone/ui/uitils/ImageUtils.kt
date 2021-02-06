package com.robosoftin.news.phone.ui.uitils

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtils {
	
	companion object {
		
		fun loadImage(view : ImageView?, url : String?) {
			Glide.with(view!!)
					.load(url)
					.into(view)
		}
		
		fun loadImageAsGIF(view : ImageView, url : String) {
			Glide.with(view)
					.asGif()
					.load(url)
					.into(view)
		}
	}
	
}