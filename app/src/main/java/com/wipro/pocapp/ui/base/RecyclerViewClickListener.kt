package com.wipro.pocapp.ui.base

import android.view.View

interface RecyclerViewClickListener<T> {
    fun onRecyclerViewItemClick(view: View, item: T)
}