/******************************************************************************
 * Copyright (c) 2020.                                                        *
 * Written for McDonalds from Robosoft Technologies Pvt Ltd                   *
 *                                                                            *
 * @Author Mithun M                                                           *
 ******************************************************************************/

package com.il.data.repository.promotion

import com.robosoftin.news.data.api.HomeAPI
import com.robosoftin.news.data.dto.NewsResponse
import com.robosoftin.news.data.dto.toErrorModel
import com.robosoftin.news.data.repo.RepositoryResult
import com.robosoftin.news.data.repo.home.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepoImpl(
		//Should directly have API
		private val api : HomeAPI
) : HomeRepo {
	override suspend fun fetchTopNews() : RepositoryResult<NewsResponse> {
		return try {
			val result = withContext(Dispatchers.IO) {
				api.getSingleTopNewsAsync().await()
			}
			RepositoryResult.Success(result)
		} catch (e : Exception) {
			RepositoryResult.Error(e.toErrorModel())
		}
	}
	
	override suspend fun fetchPopularNews(pageSize : Int, page : Int) : RepositoryResult<NewsResponse> {
		return try {
			val result = withContext(Dispatchers.IO) {
				api.getPopularNewsAsync(pageSize = pageSize, page = page).await()
			}
			RepositoryResult.Success(result)
		} catch (e : Exception) {
			RepositoryResult.Error(e.toErrorModel())
		}
	}
}