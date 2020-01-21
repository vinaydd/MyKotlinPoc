package com.wipro.pocapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.doctoremr.data.network.responses.test.TestListResponse
import com.doctoremr.utils.viewutils.RecyclerViewHolder
import com.wipro.pocapp.R
import com.wipro.pocapp.databinding.AdapterAllergiesListsBinding
import com.wipro.pocapp.ui.base.RecyclerViewClickListener
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.imagepipeline.request.ImageRequest

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log


public class ItemListAdapter (
    private val arrayList: List<TestListResponse>,
    private val recyclerViewClickListener: RecyclerViewClickListener<TestListResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: AdapterAllergiesListsBinding = (holder as RecyclerViewHolder<*>).binding as AdapterAllergiesListsBinding
        binding.allergiesList = arrayList[position]
        if(arrayList[position].imageHref!=null  && arrayList[position].imageHref.length>0){
            val  url = arrayList[position].imageHref.replace("http","https")
            val imageUri = Uri.parse(url)
            val request = ImageRequest.fromUri(imageUri)
            val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(binding.myImageView.getController()).build()
            Log.e(TAG, "ImagePath uri $imageUri")
            binding.myImageView.setController(controller)

           // binding.myImageView.setImageURI(r)
        }
        binding.executePendingBindings()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(
        DataBindingUtil.inflate<AdapterAllergiesListsBinding>(
            LayoutInflater.from(parent.context),
            com.wipro.pocapp.R.layout.adapter_allergies_lists,
            parent,
            false
        )
    )
    override fun getItemCount() = arrayList.size
}