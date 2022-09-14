package com.bakharaalief.graphqlappic.domain.model

import androidx.recyclerview.widget.DiffUtil

data class StoryModel(
    val id: String,
    val title: String,
    var isLiked: Boolean,
    var isBookmarked: Boolean,
    val publishedAt: String
)

object StoryDiffUtil : DiffUtil.ItemCallback<StoryModel>() {
    override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
        return oldItem == newItem
    }
}
