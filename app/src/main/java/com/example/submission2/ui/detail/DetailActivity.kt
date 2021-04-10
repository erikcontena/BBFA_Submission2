package com.example.submission2.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission2.R
import com.example.submission2.data.model.Users
import com.example.submission2.databinding.ActivityDetailBinding
import com.example.submission2.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER = "users"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val intent = intent.getParcelableExtra<Users>(EXTRA_USER)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java
        )
        val user: Users
        if (intent != null) {
            user = intent
            viewModel.getUser(user.username).observe(this, {
                showData(it)
            })
            viewPagerAdapter = ViewPagerAdapter(this, user.username)
        }



        with(binding){
            viewpager.adapter = viewPagerAdapter
            TabLayoutMediator(tabs, viewpager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
        supportActionBar?.elevation = 0f

    }

    private fun showData(user: Users) {
        with(binding) {

            Glide.with(this@DetailActivity)
                .load(user.avatar)
                .into(circleImageView)
            tvCompany.text = user.company ?: "-"
            tvLocation.text = user.location ?: "-"
            tvRepository.text = user.repository.toString()
            tvName.text = user.name
            toolbar.title = user.username
        }
    }
}