package com.robosoftin.news.domain.entity

class ArticleEntity(
		val title:String?,
		val description:String?,
		val source : String?,
		val isBookMarked : Boolean,
		val isTopNews : Boolean = false
) : IHomeSection {
	override fun getViewType() : SectionType {
		return if(isTopNews)  SectionType.TOP_NEWS else SectionType.POPULAR_NEWS
	}
}