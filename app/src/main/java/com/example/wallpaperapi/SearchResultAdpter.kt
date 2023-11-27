package com.example.wallpaperapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapi.databinding.SearchResultRowBinding
import com.squareup.picasso.Picasso

class SearchResultAdpter(val context: Context, val arrResuldData : List<PhotoModal>) : RecyclerView.Adapter<SearchResultAdpter.ViewHolder>() {
    class ViewHolder(val binding: SearchResultRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchResultRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrResuldData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(arrResuldData[position].src!!.portrait).into(holder.binding.imgSearchdWall)
    }
}