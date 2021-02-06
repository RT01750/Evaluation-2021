package com.robosoftin.news.domain.entity

class SectionHeader(val title : String) : IHomeSection {
	override fun getViewType() : SectionType {
		return SectionType.HEADER
	}
}