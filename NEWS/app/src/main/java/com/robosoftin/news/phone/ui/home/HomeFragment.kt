package com.robosoftin.news.phone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.il.data.utils.Logger
import com.il.domain.entity.toolbar.ToolBarConfig
import com.robosoftin.news.databinding.FragmentHomeBinding
import com.robosoftin.news.domain.entity.IHomeSection
import com.robosoftin.news.phone.presentation.BaseViewModel
import com.robosoftin.news.phone.presentation.HomeViewModel
import com.robosoftin.news.phone.ui.base.BaseFragmentMVVM
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragmentMVVM(), HomeCommunicator {
	
	private lateinit var binder : FragmentHomeBinding
	private val viewModel : HomeViewModel by inject()
	
	private val homeAdapter = HomeContentAdapter(this)
	private var lytManager : LinearLayoutManager? = null
	
	
	override fun getViewModel() : BaseViewModel? {
		return viewModel
	}
	
	override fun observeLiveData() {
		viewModel.itemsToBeAppended.observe(this, { onNewsListAvailable(it) })
	}
	
	override fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View? {
		binder = FragmentHomeBinding.inflate(inflater, parent, false)
		return binder.root
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initViews()
	}
	
	private fun initViews() {
		setUpToolbar(
				ToolBarConfig(
						enabled = true,
						title = "",
						logoEnabled = true,
						backBtnEnabled = false,
						bookMarkEnabled = true,
						searchBtnEnabled = true
				)
		)
		// Recycler view configuration
		binder.homeContentList.apply {
			lytManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
			layoutManager = lytManager
			binder.homeContentList.adapter = homeAdapter
			addOnScrollListener(scrollListener)
		}
		
	}
	
	private val scrollListener = object : RecyclerView.OnScrollListener() {
		override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
			super.onScrolled(recyclerView, dx, dy)
			paginateOnScrolledToEnd(dy)
		}
	}
	
	private fun paginateOnScrolledToEnd(dy : Int) {
		if (dy > 0
		    && !homeAdapter.isPaginationInProgress
		    && lytManager?.findLastVisibleItemPosition() == homeAdapter.newsItemCount - 1
		    && viewModel.nextPagePresent()
		) {
			fetchNextPageData()
		}
	}
	
	private fun fetchNextPageData() {
		Logger.d(TAG, "fetchNextPageData")
		homeAdapter.showProgressItem()
		viewModel.fetchNextPagePopularNews()
	}
	
	override fun onActivityCreated(savedInstanceState : Bundle?) {
		super.onActivityCreated(savedInstanceState)
		fetchFirstPageList()
	}
	
	/**
	 * Must be called only for the first time when the present list is empty
	 * or while doing refresh
	 */
	private fun fetchFirstPageList() {
		showProgressBar(true)
		homeAdapter.clearStates()
		with(viewModel) {
			fetchHomeContent()
		}
	}
	
	private fun onNewsListAvailable(list : List<IHomeSection>?) {
		showProgressBar(false)
		Logger.d(TAG, "onNewsListAvailable - new list size ${list?.size}")
		homeAdapter.appendNewNewsList(list)
	}
	
	
	companion object {
		private val TAG = HomeFragment::class.simpleName
	}
	
	override fun onHomeItemClick(item : IHomeSection) {
		Logger.d(TAG, "onHomeItemClick")
	}
}