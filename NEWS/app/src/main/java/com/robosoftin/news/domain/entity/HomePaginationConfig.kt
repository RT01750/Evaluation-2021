
package com.robosoftin.news.domain.entity

class HomePaginationConfig(
		var currentPage : Int = 0,
		var nextPage : Boolean = true,
		var pageSize : Int = 0,
		var previousPage : Boolean = false,
		var totalCount : Int = 0,
		var totalPages : Int = 0
)