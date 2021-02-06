/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * Last modified : 24/8/20 9:48 PM
 * Author :  Akshay [akshay.prabhu@robosoftin.com]
 */

package com.robosoftin.news.phone.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.il.data.utils.Logger
import com.robosoftin.news.databinding.ItemPaginationProgressBinding
import com.robosoftin.news.databinding.ItemPopularNewsBinding
import com.robosoftin.news.databinding.ItemSectionHeaderBinding
import com.robosoftin.news.databinding.ItemTopNewsBinding
import com.robosoftin.news.domain.entity.IHomeSection
import com.robosoftin.news.domain.entity.SectionType
import com.robosoftin.news.phone.ui.base.BaseRecyclerViewAdapter
import com.robosoftin.news.phone.ui.base.BaseRecyclerViewHolder

class HomeContentAdapter(private val communicator : HomeCommunicator) : BaseRecyclerViewAdapter() {
	
	companion object {
		val TAG = HomeContentAdapter::class.simpleName
	}
	
	var homeItemList : MutableList<IHomeSection> = mutableListOf()
	
	/**
	 * This item count indicates the total number of items except pagination (which is temporary)
	 */
	var newsItemCount = 0
	
	//If already data is being fetched, No need to fetch data on scrolling to and fro
	// usefull when the network is slow
	var isPaginationInProgress = false
	
	override fun createViewToHolder(parent : ViewGroup, viewType : Int)
			: BaseRecyclerViewHolder {
		
		val from = LayoutInflater.from(parent.context)
		return when (viewType) {
			SectionType.HEADER.viewType -> {
				val binder = ItemSectionHeaderBinding.inflate(from, parent, false)
				SectionHeaderViewHolder(binder)
			}
			SectionType.TOP_NEWS.viewType -> {
				val binder = ItemTopNewsBinding.inflate(from, parent, false)
				TopNewsItemViewHolder(binder)
			}
			SectionType.POPULAR_NEWS.viewType -> {
				val binder = ItemPopularNewsBinding.inflate(from, parent, false)
				PopularNewsItemViewHolder(binder)
			}
			else -> {
				val binder = ItemPaginationProgressBinding.inflate(from, parent, false)
				PaginationProgressItem(binder)
			}
		}
	}
	
	override fun bindDataToHolder(holder : BaseRecyclerViewHolder, position : Int) {
		if (holder is TopNewsItemViewHolder) {
			holder.bindTopNews(homeItemList.get(position))
			holder.addListener(communicator, position)
		}
	}
	
	override fun itemCount() = homeItemList.size
	
	override fun getItemViewType(position : Int) : Int {
		return homeItemList[position].getViewType().viewType
	}
	
	/**
	 * Add order list to the item
	 */
	fun appendNewNewsList(newsList : List<IHomeSection>?) {
		Logger.d(TAG, "appendNewNewsList: Size - ${newsList?.size}")
		removeProgressItem()
		this.newsItemCount += newsList?.filter { it.getViewType() != SectionType.PROGRESS_ITEM }?.size
		                      ?: 0
		newsList?.let { list ->
			val position = this.homeItemList.size
			list.forEach { newsItem ->
				this.homeItemList.add(newsItem)
			}
			Logger.d(TAG, "total list size - ${this.homeItemList.size}")
			notifyItemRangeInserted(position, this.homeItemList.size)
		}
	}
	
	fun showProgressItem() {
		isPaginationInProgress = true
		Logger.d(TAG, "showProgressItem")
		val value = object : IHomeSection {
			override fun getViewType() : SectionType {
				return SectionType.PROGRESS_ITEM
			}
		}
		val position = this.homeItemList.size
		this.homeItemList.add(value)
		notifyItemInserted(position)
	}
	
	private fun removeProgressItem() {
		Logger.d(TAG, "removeProgressItem")
		isPaginationInProgress = false
		val lastItem = this.homeItemList.lastOrNull()
		if (lastItem?.getViewType() == SectionType.PROGRESS_ITEM) {
			Logger.d(TAG, "progress item is present")
			val position = this.homeItemList.size - 1
			this.homeItemList.remove(lastItem)
			notifyItemRemoved(position)
			Logger.d(TAG, "progress item is removed")
		}
	}
	
	fun clearStates() {
		this.homeItemList.clear()
		this.newsItemCount = 0
		this.isPaginationInProgress = false
		notifyDataSetChanged()
	}
}

class TopNewsItemViewHolder(val binder : ItemTopNewsBinding) : BaseRecyclerViewHolder(binder.root) {
	fun bindTopNews(order : IHomeSection?) {
	
	}
	
	fun addListener(communicator : HomeCommunicator, position : Int) {
		TODO("Not yet implemented")
	}
	
}

/**
 */
class PopularNewsItemViewHolder(val binder : ItemPopularNewsBinding) : BaseRecyclerViewHolder(binder.root) {
	fun bindTopNews(order : IHomeSection?) {
	
	}
	
}

class SectionHeaderViewHolder(val binder : ItemSectionHeaderBinding) : BaseRecyclerViewHolder(binder.root) {
	fun setHeader(order : IHomeSection?) {
	
	}
	
}

class PaginationProgressItem(binder : ItemPaginationProgressBinding) :
		BaseRecyclerViewHolder(binder.root) {
	init {
	}
}

interface HomeCommunicator {
	fun onHomeItemClick(item : IHomeSection)
}