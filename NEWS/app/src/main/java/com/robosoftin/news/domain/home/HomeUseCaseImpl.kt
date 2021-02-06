package com.robosoftin.news.domain.home

import com.robosoftin.news.data.dto.ErrorModel
import com.robosoftin.news.data.repo.RepositoryResult
import com.robosoftin.news.data.repo.home.HomeRepo
import com.robosoftin.news.domain.UseCaseResult
import com.robosoftin.news.domain.entity.ArticleEntity
import com.robosoftin.news.domain.mapper.listOfArticles
import com.robosoftin.news.domain.mapper.toSingleNews

class HomeUseCaseImpl(val repo : HomeRepo) : HomeUseCase {
	override suspend fun fetchHomeContent() : UseCaseResult<ArticleEntity> {
		/**
		 * This will returns a single top news
		 */
		return when (val result = repo.fetchTopNews()) {
			is RepositoryResult.Success -> {
				result.repositoryData.toSingleNews()?.let {
					UseCaseResult.Success(it)
				} ?: UseCaseResult.Error(ErrorModel("Could not fetch top news", ErrorModel.ErrorStatus.BAD_RESPONSE))
			}
			
			is RepositoryResult.Error -> {
				UseCaseResult.Error(result.exception)
			}
		}
	}
	
	/**
	 * Will return a list of popular news, and also the total number of results
	 */
	override suspend fun fetchPopularNews(pageSize : Int, page : Int) : UseCaseResult<Pair<List<ArticleEntity>, Int>> {
		return when (val result = repo.fetchPopularNews(pageSize, page)) {
			is RepositoryResult.Success -> {
				val entity = result.repositoryData.listOfArticles()
				UseCaseResult.Success(entity)
			}
			is RepositoryResult.Error -> {
				UseCaseResult.Error(result.exception)
			}
		}
	}
}