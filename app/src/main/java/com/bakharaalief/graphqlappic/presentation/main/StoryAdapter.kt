package com.bakharaalief.graphqlappic.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.graphqlappic.R
import com.bakharaalief.graphqlappic.databinding.ItemStoriesBinding
import com.bakharaalief.graphqlappic.domain.model.StoryDiffUtil
import com.bakharaalief.graphqlappic.domain.model.StoryModel
import com.bakharaalief.graphqlappic.util.Helper.toNowDate

class StoryAdapter : ListAdapter<StoryModel, StoryAdapter.MyViewHolder>(StoryDiffUtil) {

    class MyViewHolder(private val itemStoriesBinding: ItemStoriesBinding) :
        RecyclerView.ViewHolder(itemStoriesBinding.root) {

        fun bind(storyModel: StoryModel) {
            itemStoriesBinding.titleItem.text = storyModel.title
            itemStoriesBinding.publishedItem.text = storyModel.publishedAt.toNowDate()
            itemStoriesBinding.likeIcon.setImageResource(
                if (storyModel.isLiked) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_outline_24
            )
            itemStoriesBinding.bookmarkIcon.setImageResource(
                if (storyModel.isBookmarked) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24
            )
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
}