package com.robosoftin.news.phone.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.il.data.utils.Logger
import com.robosoftin.news.domain.UseCaseResult
import com.robosoftin.news.domain.entity.*
import com.robosoftin.news.domain.home.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
		application : Application,
		private val homeUseCase : HomeUseCase
) : BaseViewModel(application) {
	
	companion object {
		const val NEWS_LIST_PAGE_SIZE = 10
		private val TAG = HomeViewModel::class.simpleName
	}
	
	val itemsToBeAppended = MutableLiveData<List<IHomeSection>>()
	
	private var paginationConfig = HomePaginationConfig(0, true, NEWS_LIST_PAGE_SIZE)
	
	fun fetchHomeContent() {
		resetPagination()
		viewModelScope.launch {
			val result = withContext(Dispatchers.IO) {
				homeUseCase.fetchHomeContent()
			}
			when (result) {
				is UseCaseResult.Success -> {
					addItems(listOf(SectionHeader("Top News")))
					addItems(listOf(result.data))
				}
				else -> {
					// do nothing
				}
			}
			fetchNextPagePopularNews()
		}
	}
	
	fun nextPagePresent() : Boolean {
		return paginationConfig.nextPage
	}
	
	fun fetchNextPagePopularNews() {
		Logger.d(TAG, "fetchNextPagePopularNews")
		viewModelScope.launch {
			val newsListResult = withContext(Dispatchers.IO) {
				homeUseCase.fetchPopularNews(
						NEWS_LIST_PAGE_SIZE,
						paginationConfig.currentPage + 1
				)
			}
			when (newsListResult) {
				is UseCaseResult.Success -> {
					Logger.d(TAG, "fetchNextPagePopularNews : Success")
					val list = newsListResult.data.first
					paginationConfig.apply {
						if (list.isNotEmpty()) {
							currentPage++
						}
						nextPage = list.size == NEWS_LIST_PAGE_SIZE
					}
					if (paginationConfig.currentPage == 1) {
						addItems(listOf(SectionHeader("Popular News")))
					}
					addItems(list)
				}
				is UseCaseResult.Error -> {
					Logger.d(TAG, "fetchNextPageNewsList : Error - ${newsListResult.exception}")
					itemsToBeAppended.value = null
				}
			}
		}
	}
	
	private fun addItems(list : List<IHomeSection>) {
		itemsToBeAppended.value = list
	}
	
	fun resetPagination() {
		paginationConfig = HomePaginationConfig(0, true, NEWS_LIST_PAGE_SIZE)
	}
}