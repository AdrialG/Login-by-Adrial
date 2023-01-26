package com.example.notesbyadrialrework.ui.editprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.databinding.ActivityEditProfileBinding
import com.example.notesbyadrialrework.ui.home.HomeActivity
import com.example.notesbyadrialrework.ui.profile.Profile

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editprofilebackbutton.setOnClickListener {
            openActivity<HomeActivity>()
        }

    }
}