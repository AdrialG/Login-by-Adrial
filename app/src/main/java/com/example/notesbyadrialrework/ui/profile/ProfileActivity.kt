package com.example.notesbyadrialrework.ui.profile

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.databinding.ActivityProfileBinding
import com.example.notesbyadrialrework.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(R.layout.activity_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_profile)

        /*binding.buttonLogout.setOnClickListener {
            viewModel.logout {
                openActivity<LoginActivity>()
                finish()
            }
        }
*/
    }
}