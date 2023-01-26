package com.example.notesbyadrialrework.ui.addnote

import android.os.Bundle
import com.crocodic.core.extension.openActivity
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.databinding.ActivityAddNoteBinding
import com.example.notesbyadrialrework.ui.home.HomeActivity

class AddNoteActivity : BaseActivity<ActivityAddNoteBinding, AddNoteViewModel>(R.layout.activity_add_note) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backaddsavebutton.setOnClickListener{
            openActivity<HomeActivity>()
        }

    }
}