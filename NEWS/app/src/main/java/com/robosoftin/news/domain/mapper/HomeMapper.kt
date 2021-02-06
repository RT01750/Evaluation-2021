package com.robosoftin.news.domain.mapper

import com.robosoftin.news.data.dto.Article
import com.robosoftin.news.data.dto.NewsResponse
import com.robosoftin.news.domain.entity.ArticleEntity

/**
 * Mapper class to convert Response model to UI model
 */
fun Article.toArticle(isTopNews : Boolean) : ArticleEntity {
	return (ArticleEntity(
			title = title,
			description = description,
			source = source?.name,
			imageUrl = urlToImage,
			detailsUrl = url,
			isBookMarked = false,
			isTopNews = isTopNews
	))
}

/**
 * Returns a single news from the top news
 */
fun NewsResponse.toSingleNews() : ArticleEntity? {
	return articles?.get(0)?.toArticle(true)
}

/**
 * @return list of article UI models along with total items in server
 */
fun NewsResponse.listOfArticles() : Pair<List<ArticleEntity>, Int> {
	val list = articles?.map {
		it.toArticle(false)
	} ?: listOf()
	return Pair(list, totalResults)
}