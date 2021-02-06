package com.robosoftin.news.phone.ui.base

import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder>() {
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : BaseRecyclerViewHolder {
		// Add generic headder if required
		return this.createViewToHolder(parent, viewType)
	}
	
	override fun getItemCount() : Int {
		return itemCount()
	}
	
	override fun getItemId(position : Int) : Long {
		return position.toLong()
	}
	
	override fun getItemViewType(position : Int) : Int {
		return position
	}
	
	override fun onBindViewHolder(holder : BaseRecyclerViewHolder, position : Int) {
		bindDataToHolder(holder, position)
		setFadeAnimation(holder.getView())
	}
	
	private fun setFadeAnimation(view : View) {
		val anim = AlphaAnimation(0.3f, 1.0f)
		anim.duration = 100
		view.startAnimation(anim)
	}
	
	abstract fun createViewToHolder(parent : ViewGroup, viewType : Int) : BaseRecyclerViewHolder
	
	abstract fun bindDataToHolder(holder : BaseRecyclerViewHolder, position : Int)
	
	abstract fun itemCount() : Int
	
}