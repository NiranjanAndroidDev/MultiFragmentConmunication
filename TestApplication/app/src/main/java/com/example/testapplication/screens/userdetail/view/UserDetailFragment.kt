package com.example.testapplication.screens.userdetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentUserDetailsBinding
import com.example.testapplication.screens.viewmodel.UserViewModel

class UserDetailFragment : Fragment(R.layout.fragment_user_details) {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userDetailsBinding: FragmentUserDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        userDetailsBinding.userViewModel = userViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userDetailsBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        return userDetailsBinding.root
    }

    private fun initViewModel() {
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)
    }
}