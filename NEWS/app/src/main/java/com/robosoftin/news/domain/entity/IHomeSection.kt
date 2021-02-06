package com.robosoftin.news.domain.entity

interface IHomeSection {
	fun getViewType() : SectionType
}

enum class SectionType(val viewType : Int) {
	HEADER(1),
	TOP_NEWS(2),
	POPULAR_NEWS(3),
	PROGRESS_ITEM(4)
}