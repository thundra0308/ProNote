package com.example.pronote.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pronote.R
import com.example.pronote.databinding.ItemNotesBinding
import com.example.pronote.models.Notes
import com.example.pronote.ui.fragments.HomeFragmentDirections

class NotesAdapter(val context: Context, var notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    class notesViewHolder(val binding: ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubtitle.text = data.subTitle.toString()
        holder.binding.notesWrite.text = data.data.toString()
        when(data.priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.p_green)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.p_yellow)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.p_red)
            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}