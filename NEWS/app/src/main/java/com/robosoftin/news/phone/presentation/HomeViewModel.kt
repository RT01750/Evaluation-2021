package com.robosoftin.news.phone.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.robosoftin.news.application.Logger
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
	
	/**
	 * This list is the fresh news items on pagination which should be appended to the adapter list
	 */
	val itemsToBeAppended = MutableLiveData<List<IHomeSection>>()
	
	private var paginationConfig = HomePaginationConfig(0, true, NEWS_LIST_PAGE_SIZE)
	
	/**
	 * This fetches the top single news and first page of popular news..
	 * This is only called whe the screen is launched
	 */
	fun fetchHomeContent() {
		//clear all states of pagination (useful when screen refresh functionality)
		resetPagination()
		viewModelScope.launch {
			val result = withContext(Dispatchers.IO) {
				homeUseCase.fetchHomeContent()
			}
			when (result) {
				is UseCaseResult.Success -> {
					//After single top news result success, add section header and append the list
					addItems(listOf(SectionHeader("Top News")))
					addItems(listOf(result.data))
				}
				else -> {
					// do nothing
				}
			}
			//irrespective of the top news result, continue with popular news list
			fetchNextPagePopularNews()
		}
	}
	
	fun nextPagePresent() : Boolean {
		return paginationConfig.nextPage
	}
	
	/**
	 * This is called everytime pagination occurs, and the pagination states is held in
	 * [paginationConfig] variable.. which is updated based on the next results
	 */
	fun fetchNextPagePopularNews() {
		Logger.d(TAG, "fetchNextPagePopularNews")
		viewModelScope.launch {
			val newsListResult = withContext(Dispatchers.IO) {
				homeUseCase.fetchPopularNews(
						pageSize = NEWS_LIST_PAGE_SIZE,
						page = paginationConfig.currentPage + 1 //to fetch the next page content
				)
			}
			when (newsListResult) {
				is UseCaseResult.Success -> {
					Logger.d(TAG, "fetchNextPagePopularNews : Success")
					val list = newsListResult.data.first
					// update paginatin config based on reulsts
					paginationConfig.apply {
						if (list.isNotEmpty()) {
							currentPage++
						}
						nextPage = list.size == NEWS_LIST_PAGE_SIZE
					}
					
					if (paginationConfig.currentPage == 1) {
						// If first set of results, add section header
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
	
	private fun resetPagination() {
		paginationConfig = HomePaginationConfig(0, true, NEWS_LIST_PAGE_SIZE)
	}
}