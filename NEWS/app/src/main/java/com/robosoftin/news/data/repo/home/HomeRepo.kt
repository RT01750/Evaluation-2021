/******************************************************************************
 * Copyright (c) 2020.                                                        *
 * Written for McDonalds from Robosoft Technologies Pvt Ltd                   *
 *                                                                            *
 * @Author Mithun M                                                           *
 ******************************************************************************/

package com.robosoftin.news.data.repo.home

import com.robosoftin.news.data.dto.NewsResponse
import com.robosoftin.news.data.repo.BaseRepo
import com.robosoftin.news.data.repo.RepositoryResult


interface HomeRepo : BaseRepo {
	
	suspend fun fetchTopNews() : RepositoryResult<NewsResponse>
	
	suspend fun fetchPopularNews(pageSize : Int, page : Int) : RepositoryResult<NewsResponse>
}