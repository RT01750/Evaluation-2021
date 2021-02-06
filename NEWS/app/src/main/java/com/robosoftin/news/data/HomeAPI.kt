/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.data

import com.robosoftin.news.BuildConfig
import com.robosoftin.news.data.dto.TopNews
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {
	@GET("/v2/top-headlines")
	fun getSingleTopNewsAsync(
			@Query("country") country : String = "in",
			@Query("apiKey") apiKey : String = BuildConfig.API_KEY,
			@Query("page") page : Int = 1,
			@Query("pageSize") pageSize :Int = 20
	) : Deferred<TopNews>
	
	@GET("/v2/everything")
	fun getPopularNewsAsync(
			@Query("q") country : String = "Technology",
			@Query("apiKey") apiKey : String = BuildConfig.API_KEY,
			@Query("page") page : Int = 1,
			@Query("pageSize") pageSize :Int = 20
	) : Deferred<TopNews>}