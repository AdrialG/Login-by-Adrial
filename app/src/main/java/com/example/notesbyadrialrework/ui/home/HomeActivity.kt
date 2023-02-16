package com.example.notesbyadrialrework.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.SharedPreference
import com.example.notesbyadrialrework.databinding.ActivityHomeBinding
import com.example.notesbyadrialrework.ui.addnote.AddNoteActivity
import com.example.notesbyadrialrework.ui.home.fragment.FragmentHomeActivity
import com.example.notesbyadrialrework.ui.profile.Profile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private lateinit var sharedPref : SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = SharedPreference(this)
        replaceFragment(FragmentHomeActivity())

        binding.floatingActionButton.setOnClickListener {
            openActivity<AddNoteActivity>()
        }

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.btn_home -> replaceFragment(FragmentHomeActivity())
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
