package com.robosoftin.news.data.api

import com.robosoftin.news.BuildConfig
import com.robosoftin.news.data.dto.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {
	@GET("/v2/top-headlines")
	fun getSingleTopNewsAsync(
			@Query("country") country : String = "in",
			@Query("apiKey") apiKey : String = BuildConfig.API_KEY,
			@Query("page") page : Int = 1,
			@Query("pageSize") pageSize : Int = 20
	) : Deferred<NewsResponse>
	
	@GET("/v2/everything")
	fun getPopularNewsAsync(
			@Query("q") country : String = "Technology",
			@Query("apiKey") apiKey : String = BuildConfig.API_KEY,
			@Query("page") page : Int = 1,
			@Query("pageSize") pageSize : Int = 10
	) : Deferred<NewsResponse>
}