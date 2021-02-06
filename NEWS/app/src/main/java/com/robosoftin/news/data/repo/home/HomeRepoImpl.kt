/******************************************************************************
 * Copyright (c) 2020.                                                        *
 * Written for McDonalds from Robosoft Technologies Pvt Ltd                   *
 *                                                                            *
 * @Author Mithun M                                                           *
 ******************************************************************************/

package com.il.data.repository.promotion

import com.robosoftin.news.data.HomeAPI
import com.robosoftin.news.data.dto.ErrorModel
import com.robosoftin.news.data.dto.TopNews
import com.robosoftin.news.data.repo.RepositoryResult
import com.robosoftin.news.data.repo.home.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepoImpl(
		//Should directly have API
		private val api : HomeAPI
) : HomeRepo {
	override suspend fun fetchTopNews() : RepositoryResult<TopNews> {
		return try {
			val result = withContext(Dispatchers.IO) {
				api.getSingleTopNewsAsync().await()
			}
			RepositoryResult.Success(result)
		} catch (e : Exception) {
			RepositoryResult.Error(ErrorModel(e.message, ErrorModel.ErrorStatus.BAD_RESPONSE))
		}
	}
	
	override suspend fun fetchPopularNews(pageSize : Int, page : Int) : RepositoryResult<TopNews> {
		
		return try {
			val result = withContext(Dispatchers.IO) {
				api.getPopularNewsAsync(pageSize, page).await()
			}
			RepositoryResult.Success(result)
		} catch (e : Exception) {
			RepositoryResult.Error(ErrorModel(e.message, ErrorModel.ErrorStatus.BAD_RESPONSE))
		}
	}
}