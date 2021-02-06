package com.robosoftin.news.domain.entity

class ToolBarConfig(
		var enabled : Boolean = false,
		var backBtnEnabled : Boolean = true,
		var logoEnabled :Boolean = false,
		var searchBtnEnabled : Boolean = false,
		var title : String? = null,
		var listSearch : Boolean = false,
		var searchBox : Boolean = false,
		var searchHint : String = "",
		var bookMarkEnabled  : Boolean = false
) {
	var screenTag : String? = null
}