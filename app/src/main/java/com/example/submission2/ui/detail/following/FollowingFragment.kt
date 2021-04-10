package com.example.submission2.ui.detail.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.submission2.databinding.FragmentFollowingBinding
import com.example.submission2.ui.adapter.UserAdapter

class FollowingFragment : Fragment() {


    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FollowingViewModel
    private lateinit var userAdapter: UserAdapter

    companion object{
        private const val EXTRA_FOLLOWING_FRAGMENT = "username"
        fun newInstance(username: String): FollowingFragment {
            val followingFragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_FOLLOWING_FRAGMENT, username)
            followingFragment.arguments = bundle
            return followingFragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()
        binding.rvFollowing.layoutManager = GridLayoutManager(activity, 2)
        binding.rvFollowing.adapter = userAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java
        )
        val username: String? = arguments?.getString(EXTRA_FOLLOWING_FRAGMENT)

        if (username != null){
            viewModel.getFollowing(username).observe(this, {
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
            binding.progressBar3.visibility = View.VISIBLE
        } else {
            binding.progressBar3.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}