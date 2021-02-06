package com.robosoftin.news.data.repo.home

import com.robosoftin.news.data.dto.NewsResponse
import com.robosoftin.news.data.repo.BaseRepo
import com.robosoftin.news.data.repo.RepositoryResult


interface HomeRepo : BaseRepo {
	
	suspend fun fetchTopNews() : RepositoryResult<NewsResponse>
	
	suspend fun fetchPopularNews(pageSize : Int, page : Int) : RepositoryResult<NewsResponse>
}