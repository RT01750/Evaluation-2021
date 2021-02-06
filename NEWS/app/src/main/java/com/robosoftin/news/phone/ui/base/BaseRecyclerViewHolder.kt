package com.robosoftin.news.phone.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
	private var model : Any? = null
	fun getView() = itemView
	
	open fun setModel(model : Any) {
		this.model = model
		//override when to set the model to the data-binding
	}
	
	fun getModel() = model
}