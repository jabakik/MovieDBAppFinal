package com.example.moviedbapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.databinding.LayoutSerialItemsBinding
import com.example.moviedbapp.models.TvItem

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private val tvItems = mutableListOf<TvItem>()

    private var clickListener: ((TvItem ) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding =
            LayoutSerialItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }
    fun updateAll(tv:List<TvItem>){
        tvItems.addAll(tv)
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(Listener:(TvItem ) -> Unit){
        this.clickListener = Listener
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvItem = tvItems[position]
        tvItem.apply {
            holder.binding.movTitle.text =holder.binding.root.context.getString(R.string.title,originalName)
            holder.binding.movRelDate.text = holder.binding.root.context.getString(R.string.Release_date,firstAirDate)
            holder.binding.movRating.text = holder.binding.root.context.getString(R.string.Average_rating,voteAverage)
            val posterUrl ="$IMAGE_PATH$posterPath"
            Glide.with(holder.binding.root.context).load(posterUrl)
                .into(holder.binding.movPoster)
            holder.binding.root.setOnClickListener{
                clickListener?.invoke(tvItem)
            }
        }
    }
    companion object {
        const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"

    }


    override fun getItemCount(): Int {
        return tvItems.size
    }

    class TvViewHolder(val binding: LayoutSerialItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}
