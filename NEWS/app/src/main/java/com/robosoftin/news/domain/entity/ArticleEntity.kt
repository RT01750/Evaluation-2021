package com.robosoftin.news.domain.entity

class ArticleEntity(
		val title : String?,
		val description : String?,
		val source : String?,
		val isBookMarked : Boolean,
		val isTopNews : Boolean = false,
		val imageUrl : String? = null,
		val detailsUrl : String? = null
) : IHomeSection {
	override fun getViewType() : SectionType {
		return if (isTopNews) SectionType.TOP_NEWS else SectionType.POPULAR_NEWS
	}
}