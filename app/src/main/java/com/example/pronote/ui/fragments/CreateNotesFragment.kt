package com.example.pronote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.pronote.R
import com.example.pronote.databinding.FragmentCreateNotesBinding
import com.example.pronote.models.Notes
import com.example.pronote.viewmodels.NotesViewModel
import java.util.*

class CreateNotesFragment : Fragment() {

    private var binding: FragmentCreateNotesBinding? = null
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding?.ivPriorityGreen?.setImageResource(R.drawable.ic_done_white)
        binding?.btnSaveNotes?.setOnClickListener {
            createNotes(it)
        }

        binding?.ivPriorityGreen?.setOnClickListener {
            priority = "1"
            binding?.ivPriorityGreen?.setImageResource(R.drawable.ic_done_white)
            binding?.ivPriorityYellow?.setImageResource(0)
            binding?.ivPriorityRed?.setImageResource(0)
        }
        binding?.ivPriorityYellow?.setOnClickListener {
            priority = "2"
            binding?.ivPriorityYellow?.setImageResource(R.drawable.ic_done_white)
            binding?.ivPriorityGreen?.setImageResource(0)
            binding?.ivPriorityRed?.setImageResource(0)
        }
        binding?.ivPriorityRed?.setOnClickListener {
            priority = "3"
            binding?.ivPriorityRed?.setImageResource(R.drawable.ic_done_white)
            binding?.ivPriorityYellow?.setImageResource(0)
            binding?.ivPriorityGreen?.setImageResource(0)
        }

        return binding?.root
    }

    private fun createNotes(it: View?) {
        val title = binding?.etTitle?.text.toString()
        val subTitle = binding?.etSubtitle?.text.toString()
        val note = binding?.etWritenote?.text.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(
            null,
            title,
            subTitle,
            note,
            noteDate.toString(),
            priority
        )
        viewModel.addNotes(data)
        Toast.makeText(context, "Notes Saved Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

}