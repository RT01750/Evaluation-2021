package com.robosoftin.news.domain.home

import com.robosoftin.news.domain.BaseUseCase
import com.robosoftin.news.domain.UseCaseResult
import com.robosoftin.news.domain.entity.ArticleEntity

interface HomeUseCase : BaseUseCase {
	suspend fun fetchHomeContent() : UseCaseResult<ArticleEntity>
	
	suspend fun fetchPopularNews(pageSize : Int, page : Int) : UseCaseResult<Pair<List<ArticleEntity>, Int>>
}