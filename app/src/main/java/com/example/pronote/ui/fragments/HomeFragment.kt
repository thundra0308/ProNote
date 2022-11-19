package com.example.pronote.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pronote.R
import com.example.pronote.databinding.FragmentHomeBinding
import com.example.pronote.models.Notes
import com.example.pronote.ui.adapters.NotesAdapter
import com.example.pronote.viewmodels.NotesViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModel.getNotes().observe(viewLifecycleOwner) {
            oldMyNotes = it as ArrayList<Notes>
            binding?.rcvAllNotes?.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(),it)
            binding?.rcvAllNotes?.adapter = adapter
        }
        binding?.btnAddNote?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        binding?.filterHigh?.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                binding?.rcvAllNotes?.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),it)
                binding?.rcvAllNotes?.adapter = adapter
            }
        }
        binding?.filterMedium?.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                binding?.rcvAllNotes?.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),it)
                binding?.rcvAllNotes?.adapter = adapter
            }
        }
        binding?.filterLow?.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                binding?.rcvAllNotes?.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),it)
                binding?.rcvAllNotes?.adapter = adapter
            }
        }
        binding?.filterBtn?.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                binding?.rcvAllNotes?.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),it)
                binding?.rcvAllNotes?.adapter = adapter
            }
        }

        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint="Search Here ..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(query: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNotes) {
            if(i.title?.contains(query!!)!! || i.subTitle?.contains(query!!)!!) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}