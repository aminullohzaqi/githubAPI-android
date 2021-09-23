package com.example.mygithubuser2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser2.databinding.FragmentFollowesBinding

class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowesBinding
    private lateinit var adapter: ListUserAdapter
    private lateinit var mainViewModel: MainViewModel
    private var list: ArrayList<User> = arrayListOf()

    companion object {
        private const val EXTRA_USERNAME = "username"

        fun newInstance(username: String): FollowersFragment{
            val fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val username = arguments?.getString(EXTRA_USERNAME)

            adapter = ListUserAdapter(list)
            adapter.notifyDataSetChanged()

            binding.recyclerviewFollowers.layoutManager = LinearLayoutManager(activity)
            binding.recyclerviewFollowers.adapter = adapter

            mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

            if (username != null) {
                showLoading(true)
                mainViewModel.setFollowers(username)
            }

            mainViewModel.listFollowers.observe(viewLifecycleOwner, { listFollowers ->
                if (listFollowers != null) {
                    adapter.setData(listFollowers as ArrayList<User>)
                    showLoading(false)
                } else{
                    binding.notFound.visibility = View.VISIBLE
                    showLoading(false)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}