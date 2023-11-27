package com.example.wallpaperapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapi.databinding.CuratedImgRowBinding
import com.squareup.picasso.Picasso

class CuratedRecyclerAdpter(val context: Context, val arrCuratedData : List<PhotoModal> ): RecyclerView.Adapter<CuratedRecyclerAdpter.ViewHolder>() {
    class ViewHolder (val binding: CuratedImgRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CuratedImgRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
       return arrCuratedData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(arrCuratedData[position].src!!.portrait).into(holder.binding.imgCuratedWall)
    }
}