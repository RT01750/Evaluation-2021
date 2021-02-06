package com.robosoftin.news.phone.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoftin.news.domain.entity.ToolBarConfig
import com.robosoftin.news.databinding.FragmentNewsDetailsBinding
import com.robosoftin.news.phone.presentation.BaseViewModel
import com.robosoftin.news.phone.ui.base.BaseFragmentMVVM

class NewsDetailFragment : BaseFragmentMVVM() {
	
	private lateinit var binder : FragmentNewsDetailsBinding
	private lateinit var contentUrl : String
	override fun getViewModel() : BaseViewModel? {
		return null
	}
	
	override fun observeLiveData() {
//		TODO("Not yet implemented")
	}
	
	override fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View? {
		binder = FragmentNewsDetailsBinding.inflate(inflater, parent, false)
		return binder.root
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		readFromBundle()
		initViews()
	}
	
	private fun initViews() {
		setUpToolbar(
				ToolBarConfig(
						enabled = true,
						title = contentUrl,
						logoEnabled = false,
						backBtnEnabled = true,
						bookMarkEnabled = false,
						searchBtnEnabled = false
				)
		)
		binder.url = contentUrl
	}
	
	/**
	 * Url must be passed, if not throws exception
	 */
	private fun readFromBundle() {
		contentUrl = arguments?.getString(DETAIL_URL)!!
	}
	
	companion object {
		private val TAG = NewsDetailFragment::class.simpleName
		val DETAIL_URL = "DetailURL"
		fun newInstance(detailUrl : String) = NewsDetailFragment().apply {
			arguments = Bundle(1).apply {
				putString(DETAIL_URL, detailUrl)
			}
		}
	}
	
	override fun onDestroy() {
		binder.unbind()
		super.onDestroy()
	}
	
}