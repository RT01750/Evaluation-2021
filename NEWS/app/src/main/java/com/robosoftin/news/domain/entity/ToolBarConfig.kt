package com.il.domain.entity.toolbar

class ToolBarConfig(
		var enabled : Boolean = false,
		var backBtnEnabled : Boolean = true,
		var searchBtnEnabled : Boolean = false,
		var title : String? = null,
		var listSearch : Boolean = false,
		var searchBox : Boolean = false,
		var searchHint : String = ""
) {
	var screenTag : String? = null
}