/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.il.mcdelivery.phone.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtils {

    companion object {

        fun loadImage(view: ImageView?, url: String?) {
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