package com.robosoftin.news.data.dto

import com.google.gson.annotations.SerializedName


data class TopNews(
		@SerializedName("articles")
		val articles : List<Article>? = null
):BaseResponse()

