package com.example.notesbyadrialrework.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.notesbyadrialrework.databinding.FragmentHomeBinding
import com.example.notesbyadrialrework.databinding.TemplateNoteBinding
import com.example.notesbyadrialrework.ui.addnote.AddNoteActivity
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentHomeActivity : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var keyword: String? = null

    private val viewModel by activityViewModels<FragmentHomeViewModel>()

    private var note = ArrayList<Note?>()
    private var noteAll = ArrayList<Note?>()

    private lateinit var rvNote: View

    private lateinit var selectedNote: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvNote = view.findViewById<RecyclerView>(R.id.note_display)

        getNote()

        binding?.searchHome?.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                val filter = noteAll.filter { it?.title?.contains("$text", true) == true }
//                val filteringData =
//                    noteAll.filter { it?.note?.contains(text.toString(), true) == true }
                Log.d("Filter Check", "Keyword $text Data : $filter")
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
                Log.d("Check All Note", "noteall : $noteAll")
                binding?.noteDisplay?.adapter?.notifyItemInserted(0)
            }
        }

        rvNote.adapter = CoreListAdapter<TemplateNoteBinding, Note>(R.layout.template_note)
            .initItem(note) { position, data ->
                activity?.openActivity<AddNoteActivity> {
                    putExtra(Const.NOTE.NOTE, data)
                }
            }

        lifecycleScope.launch {
            viewModel.note.observe(requireActivity()) { dataNote ->
                if (dataNote.isEmpty()) {
//                    activity?.tos("Nothing Here")
                } else {
                }
                note.clear()
                note.addAll(dataNote)

                binding?.noteDisplay?.adapter?.notifyDataSetChanged()
            }

            viewModel.apiResponse.collect {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getNote() {
        viewModel.getNote()
//        activity?.tos("Note Loaded")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("Keyword", "$newText")
        return false
    }


}