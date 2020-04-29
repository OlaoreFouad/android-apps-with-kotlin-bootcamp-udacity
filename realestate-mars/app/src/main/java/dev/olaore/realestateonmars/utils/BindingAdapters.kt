package dev.olaore.realestateonmars.utils

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = Uri.parse(it).buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}