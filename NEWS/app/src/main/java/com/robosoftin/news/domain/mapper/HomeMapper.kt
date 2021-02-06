package com.robosoftin.news.domain.mapper

import com.robosoftin.news.data.dto.Article
import com.robosoftin.news.data.dto.TopNews
import com.robosoftin.news.domain.entity.ArticleEntity

fun Article.toArticle(isTopNews : Boolean) : ArticleEntity {
	return (ArticleEntity(
			title = title,
			description = description,
			source = "",
			imageUrl = urlToImage,
			detailsUrl = url,
			isBookMarked = false,
			isTopNews = isTopNews
	))
}

fun TopNews.toSingleNews() : ArticleEntity? {
	return articles?.get(0)?.toArticle(true)
}

fun TopNews.listOfArticles() : Pair<List<ArticleEntity>, Int> {
	val list = articles?.map {
		it.toArticle(false)
	} ?: listOf()
	return Pair(list, totalResults)
}