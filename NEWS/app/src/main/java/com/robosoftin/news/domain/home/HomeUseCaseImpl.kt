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
		when (val result = repo.fetchTopNews()) {
			is RepositoryResult.Success -> {
				return result.repositoryData.toSingleNews()?.let {
					UseCaseResult.Success(it)
				} ?: UseCaseResult.Error(ErrorModel("", ErrorModel.ErrorStatus.BAD_RESPONSE))
			}
			
			is RepositoryResult.Error -> {
				return UseCaseResult.Error("")
			}
			
		}
	}
	
	override suspend fun fetchPopularNews(pageSize : Int, page : Int) : UseCaseResult<Pair<List<ArticleEntity>, Int>> {
		return when (val result = repo.fetchPopularNews(pageSize, page)) {
			is RepositoryResult.Success -> {
				val entity = result.repositoryData.listOfArticles()
				UseCaseResult.Success(entity)
			}
			is RepositoryResult.Error -> {
				UseCaseResult.Error("")
			}
		}
	}
}