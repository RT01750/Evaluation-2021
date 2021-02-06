package com.robosoftin.news.phone.ui.container

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robosoftin.news.phone.presentation.BaseViewModel
import com.robosoftin.news.phone.ui.base.BaseActivityMVVM

class LauncherActivity : BaseActivityMVVM() {

	override fun getViewModel() : BaseViewModel? {
		return null
	}
	
	override fun observeLiveData() {
//		TODO("Not yet implemented")
	}
	
	override fun inflateView(parent : ViewGroup, inflater : LayoutInflater) : View? {
		return null
	}
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		mParentBinder.root.postDelayed({ launchHome() }, 3000L)
	}
	
	private fun launchHome() {
		startActivity(Intent(this, ContainerActivity::class.java))
		finish()
	}
}