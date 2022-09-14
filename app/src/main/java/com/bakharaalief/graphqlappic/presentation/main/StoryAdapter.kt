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

class StoryAdapter(private val onClickLikeBookmark: (story: StoryModel, position: Int, type: String) -> Unit) :
    ListAdapter<StoryModel, StoryAdapter.MyViewHolder>(StoryDiffUtil) {

    class MyViewHolder(private val itemStoriesBinding: ItemStoriesBinding) :
        RecyclerView.ViewHolder(itemStoriesBinding.root) {

        val likeIcon = itemStoriesBinding.likeIcon
        val bookmarkIcon = itemStoriesBinding.bookmarkIcon

        fun bind(storyModel: StoryModel) {
            itemStoriesBinding.apply {
                titleItem.text = storyModel.title
                publishedItem.text = storyModel.publishedAt.toNowDate()
                likeIcon.setImageResource(
                    if (storyModel.isLiked) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_outline_24
                )
                bookmarkIcon.setImageResource(
                    if (storyModel.isBookmarked) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemStoriesBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.apply {
            bind(data)
            likeIcon.setOnClickListener {
                onClickLikeBookmark(data, position, LIKE)
            }
            bookmarkIcon.setOnClickListener {
                onClickLikeBookmark(data, position, COMMENT)
            }
        }
    }

    fun updateLike(position: Int) {
        val data = getItem(position)
        data.isLiked = data.isLiked.not()
        notifyItemChanged(position)
    }

    fun updateBookmark(position: Int) {
        val data = getItem(position)
        data.isBookmarked = data.isBookmarked.not()
        notifyItemChanged(position)
    }

    companion object {
        const val LIKE = "LIKE"
        const val COMMENT = "COMMENT"
    }
}