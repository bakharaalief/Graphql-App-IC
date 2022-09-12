package com.bakharaalief.graphqlappic.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.app.StoriesQuery
import com.bakharaalief.graphqlappic.databinding.ItemStoriesBinding

class StoryAdapter : ListAdapter<StoriesQuery.Edge, StoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val itemStoriesBinding: ItemStoriesBinding) :
        RecyclerView.ViewHolder(itemStoriesBinding.root) {

        fun bind(storiesQuery: StoriesQuery.Edge) {
            itemStoriesBinding.titleItem.text = storiesQuery.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemStoriesBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoriesQuery.Edge>() {
            override fun areItemsTheSame(
                oldItem: StoriesQuery.Edge,
                newItem: StoriesQuery.Edge
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoriesQuery.Edge,
                newItem: StoriesQuery.Edge
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}