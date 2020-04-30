package dev.olaore.realestateonmars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.realestateonmars.databinding.PropertyItemBinding
import dev.olaore.realestateonmars.models.MarsProperty

class PhotoGridAdapter()
    : ListAdapter<MarsProperty, PhotoGridAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(PropertyItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    companion object DIFF_CALLBACK : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class PhotoViewHolder(private val binding: PropertyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(property: MarsProperty) {
            binding.property = property
            binding.executePendingBindings()
        }

    }

}