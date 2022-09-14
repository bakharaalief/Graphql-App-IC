package com.bakharaalief.graphqlappic.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.graphqlappic.R
import com.bakharaalief.graphqlappic.data.Resource
import com.bakharaalief.graphqlappic.databinding.ActivityMainBinding
import com.bakharaalief.graphqlappic.domain.model.StoryModel
import com.bakharaalief.graphqlappic.presentation.ViewModelFactory
import com.bakharaalief.graphqlappic.presentation.login.LoginActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRv()
        setUpOnClick()
        getUserPref()
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance(dataStore)
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun setUpRv() {
        adapter = StoryAdapter(::onClickLikeBookmark)
        binding.storiesRv.adapter = adapter
        binding.storiesRv.layoutManager = LinearLayoutManager(this)
    }

    private fun onClickLikeBookmark(story: StoryModel, position: Int, type: String) {
        if (type == StoryAdapter.LIKE) likeStory(story, position)
        else bookmarkStory(story, position)
    }

    private fun likeStory(story: StoryModel, position: Int) {
        mainViewModel.likeStory(story.id, story.isLiked).observe(this) { response ->
            when (response) {
                is Resource.Loading -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    adapter.updateLike(position)
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bookmarkStory(story: StoryModel, position: Int) {
        mainViewModel.bookmarkStory(story.id, story.isBookmarked).observe(this) { response ->
            when (response) {
                is Resource.Loading -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    adapter.updateBookmark(position)
                }
                is Resource.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpOnClick() {
        binding.logoutBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.logout_btn -> logOut()
        }
    }

    private fun logOut() {
        mainViewModel.logOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getUserPref() {
        mainViewModel.getUserPref().observe(this) { userModel ->
            getData(userModel.refreshToken)
        }
    }

    private fun getData(token: String) {
        mainViewModel.setToken(token)
        mainViewModel.getStories().observe(this) { response ->
            when (response) {
                is Resource.Loading -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
                is Resource.Success -> {
                    adapter.submitList(response.data)
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    binding.logoutBtn.visibility = View.VISIBLE
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}