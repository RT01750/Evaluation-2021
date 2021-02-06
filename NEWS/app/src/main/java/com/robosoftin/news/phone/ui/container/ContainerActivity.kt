package com.robosoftin.news.phone.ui.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoftin.news.R
import com.robosoftin.news.phone.presentation.BaseViewModel
import com.robosoftin.news.phone.ui.base.BaseActivityMVVM
import com.robosoftin.news.phone.ui.home.HomeFragment

class ContainerActivity : BaseActivityMVVM() {
	override fun getViewModel() : BaseViewModel? {
		return null
	}
	
	override fun observeLiveData() {
	}
	
	override fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View? {
		return null
	}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		loadHomeScreen()
	}
	
	private fun loadHomeScreen() {
		addFragment(R.id.base_container, HomeFragment())
	}
}