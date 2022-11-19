package com.example.pronote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.pronote.R
import com.example.pronote.databinding.FragmentEditNotesBinding
import com.example.pronote.models.Notes
import com.example.pronote.viewmodels.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text
import java.util.*

class EditNotesFragment : Fragment() {

    val notes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.etTitleEdit.setText(notes.data.title)
        binding.etSubtitleEdit.setText(notes.data.subTitle)
        binding.etWritenoteEdit.setText(notes.data.note)
        when(notes.data.priority) {
            "1" -> {
                binding.ivPriorityGreenEdit.setImageResource(R.drawable.ic_done_white)
            }
            "2" -> {
                binding.ivPriorityYellowEdit.setImageResource(R.drawable.ic_done_white)
            }
            "3" -> {
                binding.ivPriorityRedEdit.setImageResource(R.drawable.ic_done_white)
            }
        }

        binding.ivPriorityGreenEdit.setOnClickListener {
            priority = "1"
            binding.ivPriorityGreenEdit.setImageResource(R.drawable.ic_done_white)
            binding.ivPriorityYellowEdit.setImageResource(0)
            binding.ivPriorityRedEdit.setImageResource(0)
        }
        binding.ivPriorityYellowEdit.setOnClickListener {
            priority = "2"
            binding.ivPriorityYellowEdit.setImageResource(R.drawable.ic_done_white)
            binding.ivPriorityGreenEdit.setImageResource(0)
            binding.ivPriorityRedEdit.setImageResource(0)
        }
        binding.ivPriorityRedEdit.setOnClickListener {
            priority = "3"
            binding.ivPriorityRedEdit.setImageResource(R.drawable.ic_done_white)
            binding.ivPriorityYellowEdit.setImageResource(0)
            binding.ivPriorityGreenEdit.setImageResource(0)
        }

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.etTitleEdit.text.toString()
        val subTitle = binding.etSubtitleEdit.text.toString()
        val note = binding.etWritenoteEdit.text.toString()
        val d = Date()
        val noteDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(
            notes.data.id,
            title,
            subTitle,
            note,
            noteDate.toString(),
            priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(context, "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_del) {
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.bottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)
            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(notes.data.id!!)
                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}