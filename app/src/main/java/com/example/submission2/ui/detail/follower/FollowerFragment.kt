package com.example.submission2.ui.detail.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submission2.databinding.FragmentFollowerBinding
import com.example.submission2.ui.adapter.UserAdapter


class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowerViewModel
    private lateinit var userAdapter: UserAdapter
    companion object{
        private const val EXTRA_FOLLOWER_FRAGMENT = "username"
        fun newInstance(username: String): FollowerFragment {
            val followerFragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_FOLLOWER_FRAGMENT, username)
            followerFragment.arguments = bundle
            return followerFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()
        binding.rvFollower.layoutManager = GridLayoutManager(activity, 2)
        binding.rvFollower.adapter = userAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowerViewModel::class.java
        )
        val username: String? = arguments?.getString(EXTRA_FOLLOWER_FRAGMENT)

        if (username != null){
            viewModel.getFollowers(username).observe(this, {
                showLoading(true)
                if (it != null) {
                    showLoading(false)
                    userAdapter.setData(it)
                }
            })
        }

    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}