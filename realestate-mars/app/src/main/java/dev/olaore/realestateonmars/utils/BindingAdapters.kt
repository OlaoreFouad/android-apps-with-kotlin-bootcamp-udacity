package dev.olaore.realestateonmars.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.olaore.realestateonmars.PhotoGridAdapter
import dev.olaore.realestateonmars.models.MarsProperty
import dev.olaore.realestateonmars.viewmodels.MarsApiStatus

@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = Uri.parse(it).buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun setListData(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    data?.let {
        val adapter = recyclerView.adapter as PhotoGridAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("requestStatus")
fun setRequestStatus(imageView: ImageView, status: MarsApiStatus?) {
    status?.let {
        when (it) {
            MarsApiStatus.DONE -> {
                imageView.visibility = View.GONE
            }
            MarsApiStatus.ERROR -> {
                imageView.visibility = View.VISIBLE
            }
        }
    }
}