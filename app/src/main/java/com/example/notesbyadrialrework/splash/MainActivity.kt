package com.example.notesbyadrialrework.splash

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.databinding.ActivityMainBinding
import com.example.notesbyadrialrework.login.LoginActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.splash {
            openActivity<LoginActivity>()
            finish()
        }
    }
}