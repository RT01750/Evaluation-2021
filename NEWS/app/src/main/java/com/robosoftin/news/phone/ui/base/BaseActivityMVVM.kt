/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.robosoftin.news.phone.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import com.il.data.utils.Logger
import com.robosoftin.news.phone.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseActivityMVVM : BaseActivity(),
		CoroutineScope by MainScope() {
	/**
	 * Observes for Live data
	 */
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		startObservingLiveData()
	}
	
	/**
	 * Abstract implementation :
	 * Observes for common UI Live data changes,
	 * View model will be supplied by the child activity
	 *
	 * also calls abstract method observeLiveData() to be implemented in child activities
	 */
	private fun startObservingLiveData() {
		val viewModel = getViewModel()
		logViewModel()
		viewModel?.let {
			observeUILiveData(it)
		}
		observeLiveData()
		mParentBinder.viewModel = viewModel
	}
	
	
	private fun logViewModel() {
		Logger.d("ViewModelInfo", "\t${this::class.simpleName}\t${getViewModel()}")
	}
	
	
	/**
	 * Observe for all the common UI related live data here and call the UI methods written in
	 * BaseActivity
	 */
	private fun observeUILiveData(viewModel : BaseViewModel) {
	}
	
	/**
	 * get View model which extends Base Live data
	 */
	abstract fun getViewModel() : BaseViewModel?
	
	/**
	 * Observe live data in the child classes
	 */
	abstract fun observeLiveData()
	
	/**
	 * Refresh activity on multiple events lik
	 * Login completed
	 */
	fun redirectToUrl(url : String) {
		if (URLUtil.isValidUrl(url)) {
			val viewIntent = Intent(
					"android.intent.action.VIEW",
					Uri.parse(url)
			)
			startActivity(viewIntent)
		}
	}
}