package com.example.notesbyadrialrework.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.crocodic.core.api.ApiStatus
import com.crocodic.core.base.adapter.CoreListAdapter
import com.crocodic.core.base.adapter.CoreListAdapter.Companion.get
import com.crocodic.core.extension.openActivity
import com.crocodic.core.extension.tos
import com.example.notesbyadrialrework.R
import com.example.notesbyadrialrework.base.BaseFragment
import com.example.notesbyadrialrework.data.Const
import com.example.notesbyadrialrework.data.Note
import com.example.notesbyadrialrework.data.UserDao
import com.example.notesbyadrialrework.databinding.FragmentHomeBinding
import com.example.notesbyadrialrework.databinding.TemplateNoteBinding
import com.example.notesbyadrialrework.ui.addnote.AddNoteActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class FragmentHomeActivity : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

    @Inject
    lateinit var userDao: UserDao

    private val viewModel by activityViewModels<FragmentHomeViewModel>()

    private var note = ArrayList<Note?>()
    private var noteAll = ArrayList<Note?>()


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvNote = view.findViewById<RecyclerView>(R.id.note_display)

        //SearchView Function
        binding?.searchHome?.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                val filter = noteAll.filter { it?.title?.contains("$text", true) == true }
//                val filteringData =
//                    noteAll.filter { it?.note?.contains(text.toString(), true) == true }
                Log.d("CekFilter", "Keyword $text Data : $filter")
                note.clear()
//                note.addAll(filter)
                filter.forEach {
                    note.add(it)
                }
                binding?.noteDisplay?.adapter?.notifyDataSetChanged()
                binding?.noteDisplay?.adapter?.notifyItemInserted(0)

            } else {
                note.clear()
                binding?.noteDisplay?.adapter?.notifyDataSetChanged()
                note.addAll(noteAll)
                Log.d("ceknoteall", "noteall : $noteAll")
                binding?.noteDisplay?.adapter?.notifyItemInserted(0)
            }
        }

        observe()
        getNote()

        //Adapter Recycler View
        rvNote.adapter = CoreListAdapter<TemplateNoteBinding, Note>(R.layout.template_note)
            .initItem(note) { position, data ->
                activity?.tos("tes")
                activity?.openActivity<AddNoteActivity> {
                    putExtra(Const.NOTE.NOTE, data)
                }
            }
    }


    //Get Note Function
    private fun getNote() {
        viewModel.getNote()
        activity?.tos("Note Loaded")
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.note.observe(requireActivity()) {

                note.clear()
                noteAll.clear()

                note.addAll(it)
                noteAll.addAll(it)
                binding?.noteDisplay?.adapter?.notifyDataSetChanged()
                binding?.noteDisplay?.adapter?.notifyItemInserted(0)
            }

            viewModel.apiResponse.collect { it ->
//                    activity.tos("test")
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        binding?.noteDisplay?.adapter?.get()?.removeNull()
                    }
                    else -> {

                    }
                }
            }
        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("Keyword", "$newText")
        return false
    }

}