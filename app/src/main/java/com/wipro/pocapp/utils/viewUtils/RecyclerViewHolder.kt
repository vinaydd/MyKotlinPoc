package com.doctoremr.utils.viewutils

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder<T : ViewDataBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root)