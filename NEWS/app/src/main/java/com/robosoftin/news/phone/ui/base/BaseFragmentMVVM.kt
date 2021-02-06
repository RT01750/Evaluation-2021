package com.robosoftin.news.phone.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.il.data.utils.Logger
import com.il.domain.entity.toolbar.ToolBarConfig
import com.robosoftin.news.phone.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import java.util.*

abstract class BaseFragmentMVVM : BaseFragment(),
		CoroutineScope by MainScope() {
	/**
	 * Observes for Live data
	 */
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		startObservingLiveData()
	}
	
	override fun onDestroy() {
		super.onDestroy()
		cancel()
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rootBinder.fragment = this
		rootBinder.baseViewModel = getViewModel()
		rootBinder.lifecycleOwner = this
		
		toolbarBinder?.let {
			it.fragment = this
			it.lifecycleOwner = this
		}
	}
	
	/**
	 * Abstract implementation :
	 * Observes for common UI Live data changes,
	 * View model will be supplied by the child activity
	 *
	 * also calls abstract method observeLiveData() to be implemented in child fragments
	 */
	private fun startObservingLiveData() {
		val viewModel = getViewModel()
		logViewModel()
		viewModel?.let {
			observeUILiveData(it)
		}
		observeLiveData()
	}
	
	private fun logViewModel() {
		Logger.d("ViewModelInfo", "\t${this::class.simpleName}\t${getViewModel()}")
	}
	
	val toolBarConfig = MutableLiveData<ToolBarConfig>().apply {
		value = ToolBarConfig() //Assign default set of values
	}
	
	val searchText = MutableLiveData<String>()
	
	var timer : Timer = Timer()
	
	private fun oSearchTextChanged(searchText : String?) {
		timer.cancel()
		timer = Timer()
		timer.schedule(object : TimerTask() {
			override fun run() {
				activity?.runOnUiThread {
					searchTextChanged(searchText)
				}
			}
		}, searchTypeDelay())
		
	}
	
	open fun searchTypeDelay() = 300L
	
	open fun searchTextChanged(searchText : String?) {
		/*
		 Will be called when toolbar search text changes
		 Any fragment which requires search callback will implement this method
		 */
	}
	
	/**
	 * Screen tag is used to differentiate different screens
	 * Toolbars of the respective screens should only be changed
	 * in-case of view model shared between many screens
	 */
	protected fun setUpToolbar(toolBarConfig : ToolBarConfig?) {
		toolBarConfig?.screenTag = this.toString()
		this.toolBarConfig.value = toolBarConfig
	}
	
	
	open fun toolbarBackClick() {
		activity?.onBackPressed()
	}
	
	fun onSearchCloseClick() {
		searchText.value = null
	}
	
	open fun toolBarSearchClick() {
		/*todo handle search click in subsequent fragments*/
	}
	
	open fun toolBarBookMarkClicked() {
	
	}
	
	private fun observeUILiveData(viewModel : BaseViewModel) {
		searchText.observe(this, Observer { oSearchTextChanged(it) })
	}
	
	
	/**
	 * get View model which extends Base Live data
	 */
	abstract fun getViewModel() : BaseViewModel?
	
	/**
	 * Observe live data in the child classes
	 */
	abstract fun observeLiveData()
	
	
	companion object {
		private val TAG = BaseFragmentMVVM::class.java.simpleName
	}
}