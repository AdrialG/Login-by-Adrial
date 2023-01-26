package com.example.notesbyadrialrework.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.crocodic.core.extension.tos
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseFragment
import com.example.notesbyadrialrework.databinding.ActivityProfileBinding
import com.example.notesbyadrialrework.ui.editprofile.EditProfileActivity
import com.example.notesbyadrialrework.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Profile : BaseFragment<ActivityProfileBinding>(R.layout.activity_profile) {

    private val viewModel by activityViewModels<ProfileFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

   /* override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getUser.observe(requireActivity()) { data ->
                data?.let {
                    binding?.user = it

                    binding?.exampleTextName?.text  = it.name
                    binding?.exampleTextEmail?.text = it.email
                }
            }
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {} }
        }

        binding?.buttonEditProfile?.setOnClickListener {
            val intentedit = Intent(requireContext(),EditProfileActivity::class.java)
            startActivity(intentedit)
        }

        binding?.buttonLogout?.setOnClickListener {
            viewModel.logout {
                val intent = Intent(activity,LoginActivity::class.java)
                startActivity(intent)
            }
        }

    }

}