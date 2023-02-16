package com.example.notesbyadrialrework.ui.addnote

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.textOf
import com.crocodic.core.extension.tos
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseActivity
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.data.Note
import com.example.notesbyadrialrework.databinding.ActivityAddNoteBinding
import com.example.notesbyadrialrework.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@AndroidEntryPoint
class AddNoteActivity : BaseActivity<ActivityAddNoteBinding, AddNoteViewModel>(R.layout.activity_add_note) {

    private var note : Note? = null
    private var oldTitle : String? = null
    private var oldContent : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = intent.getParcelableExtra(Const.NOTE.NOTE)

        binding.data = note

        title = "KotlinApp"
        val textView: TextView = findViewById(R.id.edited_at)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateString = simpleDateFormat.format(note?.updatedAt?: 9897546853323L)
        binding.editedAt.text = String.format("Date: %s", dateString)


        binding.backaddsavebutton.setOnClickListener{

            val title = binding.title.textOf()
            val content = binding.content.textOf()

            viewModel.createNote(title, content)

            openActivity<HomeActivity>()
            finish()

        }

        binding.updatebutton.setOnClickListener{

            val title = binding.title.textOf()
            val content = binding.content.textOf()

            if (note!=null) {
                viewModel.updateNote(note!!.id.toString(), title, content)
            }
            else {
            }

            openActivity<HomeActivity>()
            finish()

        }

        binding.deletebutton.setOnClickListener {
            note?.id?.let { id -> viewModel.deleteNote(id) }

            openActivity<HomeActivity>()
            finish()

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.apiResponse.collect {
                        when (it.status) {
                            ApiStatus.LOADING -> loadingDialog.show("Hang Tight...")
                            ApiStatus.SUCCESS -> {
                                tos(it.message ?: "Note Created")
                                loadingDialog.dismiss()
                            }

                            ApiStatus.ERROR -> {
                                disconnect(it)
                            }

                            ApiStatus.EXPIRED -> {

                            }
                            else -> loadingDialog.setResponse(":-(")
                        }
                    }
                }
            }
        }

    }
}