package com.robosoftin.news.data.dto

import com.google.gson.annotations.SerializedName

open class BaseResponse {
	@SerializedName("status")
	private val status : String? = null
	
	@SerializedName("totalResults")
	val totalResults : Int = 0
}