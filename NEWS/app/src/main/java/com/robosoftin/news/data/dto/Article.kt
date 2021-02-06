package com.robosoftin.news.data.dto

import com.google.gson.annotations.SerializedName

/**
 *  {
"source": {
"id": null,
"name": "BBC News"
},
"author": "https://www.facebook.com/bbcnews",
"title": "Crowd protests against military rule in Myanmar - BBC News",
"description": "Saturday's rally in Yangon is reportedly the biggest since the military seized power on Monday.",
"url": "https://www.bbc.com/news/world-asia-55960284",
"urlToImage": "https://ichef.bbci.co.uk/news/1024/branded_news/EB90/production/_116840306_reumyanmar.jpg",
"publishedAt": "2021-02-06T04:57:00Z",
"content": "Hundreds of people have rallied in Myanmar's main city, Yangon, to protest against this week's military coup. \r\n\"Military dictator, fail, fail; Democracy, win, win,\" chanted the crowd, the largest pr… [+5957 chars]"
}
 */
data class Article(
		@SerializedName("author")
		val author : String,
		@SerializedName("title")
		val title : String,
		@SerializedName("description")
		val description : String,
		@SerializedName("url")
		val url : String,
		@SerializedName("urlToImage")
		val urlToImage : String?,
		@SerializedName("publishedAt")
		val publishedAt : String,
		@SerializedName("content")
		val content : String?
)