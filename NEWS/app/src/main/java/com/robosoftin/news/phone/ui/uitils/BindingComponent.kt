package com.robosoftin.news.phone.ui.uitils

import androidx.databinding.DataBindingComponent


class BindingComponent : DataBindingComponent {
	override fun getDataBindingUtility() : DataBindingUtility {
		return DataBindingUtility()
	}
}