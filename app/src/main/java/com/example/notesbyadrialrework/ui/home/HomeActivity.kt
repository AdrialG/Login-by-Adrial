package com.example.notesbyadrialrework.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.databinding.ActivityHomeBinding
import com.example.notesbyadrialrework.ui.profile.Profile

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.btn_home -> replaceFragment(Home())
                R.id.btn_profile -> replaceFragment(Profile())

                else ->{

                }

            }

            true
        }

    }

    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}
