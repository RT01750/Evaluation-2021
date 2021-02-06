package com.robosoftin.news.domain.mapper

import com.robosoftin.news.data.dto.Article
import com.robosoftin.news.data.dto.TopNews
import com.robosoftin.news.domain.entity.ArticleEntity

fun Article.toArticle() : ArticleEntity {
	return (ArticleEntity(
			title = title,
			description = description,
			source = "",
			isBookMarked = false,
			isTopNews = true
	))
}

fun TopNews.toSingleNews() : ArticleEntity? {
	return articles?.get(0)?.toArticle()
}

fun TopNews.listOfArticles() : Pair<List<ArticleEntity>, Int> {
	val list = articles?.map {
		it.toArticle()
	} ?: listOf()
	return Pair(list, totalResults)
}